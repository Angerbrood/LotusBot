package com.bot.lotus.fetcher;

import com.bot.lotus.log.LotusLogger;
import com.bot.lotus.model.LotusItem;
import com.bot.lotus.utils.ConfigReader;
import com.bot.lotus.utils.ValueFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LotusWebsiteDataFetcher {
    private static final LotusLogger LOG = LotusLogger.of(LoggerFactory.getLogger(LotusWebsiteDataFetcher.class));
    private static final String PAGES = "pages.txt";
    private static final String PRICE_CSS_CLASS = "price";
    private static final String TITLE_CSS_CLASS = "title";
    private static final String HEADING_TAG = "h3";
    private static final String P_TAG = "p";
    private final List<String> sitesToCheck;

    public LotusWebsiteDataFetcher() {
        this.sitesToCheck = ConfigReader.readFile(PAGES);
    }

    public List<LotusItem> checkSites() {
        return sitesToCheck
                .stream()
                .map(this::checkSite)
                .collect(Collectors.toList());
    }

    private LotusItem checkSite(String site) {
        try {
            LOG.info(String.format("Starting to check site: %s", site));
            Document doc = Jsoup.connect(site)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .timeout(12000)
                    .get();
            final String title = Optional.ofNullable(getElementByClassAndTag(doc, TITLE_CSS_CLASS, HEADING_TAG))
                    .map(this::readValueFromElement)
                    .orElseThrow(IllegalArgumentException::new);

            final String price = Optional.ofNullable(getElementByClassAndTag(doc, PRICE_CSS_CLASS, P_TAG))
                    .map(this::readValueFromElement)
                    .orElseThrow(IllegalArgumentException::new);

            LOG.info("Reading has finished");
            return new LotusItem(title, price, site);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return null;
        }
    }

    private String readValueFromElement(final Element element) {
        final String value = element.childNode(0).outerHtml();
        return ValueFormatter.formatPriceValue(value);
    }

    private Element getElementByClassAndTag(final Document document, final String cssClass, final String tag) {
        final Elements priceElement = document.getElementsByClass(cssClass);
        for(final Element current : priceElement) {
            if(current.tag().getName().equalsIgnoreCase(tag)) {
                return current;
            }
        }
        return null;
    }
}
