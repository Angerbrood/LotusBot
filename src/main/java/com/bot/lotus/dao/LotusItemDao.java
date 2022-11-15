package com.bot.lotus.dao;

import com.bot.lotus.log.LotusLogger;
import com.bot.lotus.model.LotusItem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class LotusItemDao {
    private static final LotusLogger LOG = LotusLogger.of(LoggerFactory.getLogger(LotusItemDao.class));
    private final SessionFactory sessionFactory;
    public LotusItemDao(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<LotusItem> findAll() {
        LOG.info("Starting to read saved items from database");
        List<LotusItem> results;
        try (final Session session = sessionFactory.openSession()) {
            results = session.createQuery("from LotusItem", LotusItem.class).list();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            results = new LinkedList<>();
        }
        LOG.info("Reading has finished");
        return results;
    }

    public void update(final List<LotusItem> lotusItems) {
        LOG.info("Starting to write to cache");
        clear();
        Transaction transaction = null;
        try (final Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            lotusItems.forEach(session::persist);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
               transaction.rollback();
            }
            LOG.error(ex.getMessage(), ex);
        }
        LOG.info("Writing has finished");
    }

    private void clear() {
        Transaction transaction = null;
        final List<LotusItem> lotusItems = findAll();
        try (final Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            lotusItems.forEach(session::remove);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(ex.getMessage(), ex);
        }
    }
}
