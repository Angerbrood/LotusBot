package com.bot.lotus.service;

import com.bot.lotus.log.LotusLogger;
import com.bot.lotus.model.LotusItem;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class CacheService {
    private static final LotusLogger LOG = LotusLogger.of(LoggerFactory.getLogger(CacheService.class));
    private static final String JDBC_URL = "jdbc:sqlite:";
    private final String databaseName;
    public CacheService(final String databaseName) {
        this.databaseName = databaseName;
    }

    public List<LotusItem> readItemsFromCache() {
        LOG.info("Starting to read saved items from cache");

        File file = streamToFile(getClass().getClassLoader().getResourceAsStream(databaseName));
        final String path = file.getAbsolutePath();
        if(StringUtils.isEmpty(path)) {
            throw new IllegalArgumentException("Unable to load db");
        }
        final String databaseUrl = JDBC_URL + path;
        List<LotusItem> results;
        try(JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(databaseUrl)) {
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
        File file = streamToFile(getClass().getClassLoader().getResourceAsStream(databaseName));
        final String path = file.getAbsolutePath();
        if(StringUtils.isEmpty(path)) {
            throw new IllegalArgumentException("Unable to load db");
        }
        final String databaseUrl = JDBC_URL + path;
        try(JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(databaseUrl)) {
            final Dao<LotusItem, Long> lotusItemDao = DaoManager.createDao(connectionSource, LotusItem.class);
            TableUtils.dropTable(lotusItemDao, true);
            TableUtils.createTable(lotusItemDao);
            lotusItemDao.create(lotusItems);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
        LOG.info("Writing has finished");
    }

    private File streamToFile(InputStream in) {
        if (in == null) {
            return null;
        }

        try {
            File f = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
            f.deleteOnExit();

            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];

            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return f;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}
