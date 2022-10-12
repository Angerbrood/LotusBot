package com.bot.lotus.service;

import com.bot.lotus.log.LotusLogger;
import com.bot.lotus.model.LotusItem;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class CacheService {
    private static final LotusLogger LOG = LotusLogger.of(LoggerFactory.getLogger(CacheService.class));
    private static final String DB_URL = "jdbc:sqlite:cache.db";

    private final String databaseUrl;
    public CacheService(final String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public List<LotusItem> readItemsFromCache() {
        LOG.info("Starting to read saved items from cache");
        List<LotusItem> results;
        try(JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(DB_URL)) {
            final Dao<LotusItem, Long> lotusItemDao = DaoManager.createDao(connectionSource, LotusItem.class);
            if(lotusItemDao.isTableExists()) {
                results = lotusItemDao.queryForAll();
            } else {
                LOG.info("Unable to read items from cache, returning empty list");
                results = new LinkedList<>();
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            results = new LinkedList<>();
        }
        LOG.info("Reading has finished");
        return results;
    }

    public void writeToCache(final List<LotusItem> lotusItems) {
        LOG.info("Starting to write to cache");
        try(JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(DB_URL)) {
            final Dao<LotusItem, Long> lotusItemDao = DaoManager.createDao(connectionSource, LotusItem.class);
            TableUtils.dropTable(lotusItemDao, true);
            TableUtils.createTable(lotusItemDao);
            lotusItemDao.create(lotusItems);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
        LOG.info("Writing has finished");
    }
}
