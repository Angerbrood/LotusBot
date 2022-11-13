package com.bot.lotus.main;

import com.bot.lotus.config.HibernateUtil;
import com.bot.lotus.log.LotusLogger;
import com.bot.lotus.mail.MailHandler;
import com.bot.lotus.model.LotusItem;
import com.bot.lotus.dao.LotusItemDao;
import com.bot.lotus.service.CompareService;
import com.bot.lotus.fetcher.LotusWebsiteDataFetcher;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {
    private static final LotusLogger LOG = LotusLogger.of(LoggerFactory.getLogger(Main.class));

    public static void main(String[] args) {
        LOG.info("Starting LotusBot");
        final LotusItemDao lotusDao = new LotusItemDao(HibernateUtil.getSessionFactory());
        final List<LotusItem> savedLotusItem = lotusDao.findAll();
        final LotusWebsiteDataFetcher lotusWebsiteDataFetcher = new LotusWebsiteDataFetcher();
        final List<LotusItem> fetchedLotusItem = lotusWebsiteDataFetcher.checkSites();
        lotusDao.update(fetchedLotusItem);
        final CompareService compareService = new CompareService();
        final List<String> notificationToSend = compareService.compare(savedLotusItem, fetchedLotusItem);
        if (!notificationToSend.isEmpty()) {
            final MailHandler mailHandler = new MailHandler();
            mailHandler.sendNotifications(notificationToSend);
        }
    }
}
