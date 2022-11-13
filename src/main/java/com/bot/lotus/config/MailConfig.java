package com.bot.lotus.config;

import com.bot.lotus.log.LotusLogger;
import com.bot.lotus.utils.ConfigReader;
import org.slf4j.LoggerFactory;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class MailConfig {
    private static final String PROPERTY_PATH = "mail.properties";
    private static final LotusLogger LOG = LotusLogger.of(LoggerFactory.getLogger(MailConfig.class));

    private boolean sendMails;

    public Session initConfig() {
        final Properties properties = ConfigReader.readProperties(PROPERTY_PATH);
        sendMails = Boolean.parseBoolean(properties.getProperty("debug.sendMails"));
        if(properties == null) {
            LOG.error("Unable to create mail session object");
            return null;
        }
        final String username = properties.getProperty("mail.smtp.username");
        final String password = properties.getProperty("mail.smtp.password");
        return createSession(username, password, properties);
    }

    private Session createSession(final String username, final String password, final Properties prop) {
        return Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public boolean needToSendMails() {
        return sendMails;
    }
}
