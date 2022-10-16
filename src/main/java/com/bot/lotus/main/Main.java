package com.bot.lotus.main;

import com.bot.lotus.log.LotusLogger;
import com.bot.lotus.mail.MailHandler;
import com.bot.lotus.model.LotusItem;
import com.bot.lotus.service.CacheService;
import com.bot.lotus.service.CompareService;
import com.bot.lotus.fetcher.LotusWebsiteDataFetcher;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {
    private static final LotusLogger LOG = LotusLogger.of(LoggerFactory.getLogger(Main.class));

    public static void main(String[] args) {
        LOG.info("Starting LotusBot");
        final CacheService cacheService = new CacheService("cache.db");
        final LotusWebsiteDataFetcher lotusWebsiteDataFetcher = new LotusWebsiteDataFetcher();
        final CompareService compareService = new CompareService();
        final MailHandler mailHandler = new MailHandler();
        LOG.info("LotusBot has been initialized");

        final List<LotusItem> savedLotusItem = cacheService.readItemsFromCache();
        final List<LotusItem> fetchedLotusItem = lotusWebsiteDataFetcher.checkSites();
        cacheService.writeToCache(fetchedLotusItem);
        final List<String> notificationToSend = compareService.compare(savedLotusItem, fetchedLotusItem);
        if (!notificationToSend.isEmpty()) {
            //mailHandler.sendNotifications(notificationToSend);
        	LOG.info("Should be sending mails");
        }
    }
}
