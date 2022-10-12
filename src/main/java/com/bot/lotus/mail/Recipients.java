package com.bot.lotus.mail;

import com.bot.lotus.log.LotusLogger;
import com.bot.lotus.utils.ConfigReader;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Recipients {
    private static final LotusLogger LOG = LotusLogger.of(LoggerFactory.getLogger(Recipients.class));
    private static final String PROPERTY_PATH = "recipients.properties";
    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(\\S+)$";
    private static final String DELIMITER = ";";
    private final List<String> recipients;

    public Recipients() {
        LOG.info("Loading recipients from disk");
        recipients = new LinkedList<>();
        final Properties properties = ConfigReader.readProperties(PROPERTY_PATH);
        if (properties == null) {
           LOG.error("Unable to read recipient list, falling back to default");
           recipients.add("petzb73@gmail.com");
        }
        final String[] splitted = properties.getProperty("sendTo").split(DELIMITER);
        for(final String current : splitted) {
            if (current.matches(EMAIL_REGEX_PATTERN)) {
                recipients.add(current);
            } else {
                LOG.error(String.format("Given address: %s not a valid email address", current));
            }
        }
        LOG.info("Recipients loading was finished");
    }

    public List<String> getList() {
        return Collections.unmodifiableList(this.recipients);
    }
}
