package com.bot.lotus.service;

import com.bot.lotus.log.LotusLogger;
import com.bot.lotus.model.LotusItem;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CompareService {
    private static final LotusLogger LOG = LotusLogger.of(LoggerFactory.getLogger(CompareService.class));

    public List<String> compare(final List<LotusItem> cachedItems, final List<LotusItem> fetchedItems) {
        LOG.info("Starting to compare saved items with newly fetched items");
        final List<String> results = new LinkedList<>();
        final Map<String, LotusItem> cachedByUrl = cachedItems
                .stream()
                .collect(Collectors.toMap(LotusItem::getUrl, Function.identity()));

        for (final LotusItem currentFetched : fetchedItems) {
            final LotusItem currentSaved = cachedByUrl.get(currentFetched.getUrl());
            if (currentSaved != null && currentFetched.getPrice() < currentSaved.getPrice()) {
               results.add(createNotification(currentSaved, currentFetched));
            }
        }
        LOG.info("Comparing has finished");
        return results;
    }

    private String createNotification(LotusItem currentSaved, LotusItem currentFetched) {
        LOG.info(String.format("Creating email notification for %s", currentFetched.getTitle()));
        final StringBuilder sb = new StringBuilder();
        sb.append("Lotus WebShop price change has been detected.")
                .append("<br>")
                .append("<br>");
        sb.append("The price of watched item: ").append(currentFetched.getTitle())
                .append(" has been changed from ").append(currentSaved.getPrice()).append(" Ft")
                .append(" to ").append(currentFetched.getPrice()). append(" Ft.");
        sb.append("<br>").append("<br>");
        sb.append("Click here to visit: ").append(currentFetched.getUrl());
        return sb.toString();
    }
}
