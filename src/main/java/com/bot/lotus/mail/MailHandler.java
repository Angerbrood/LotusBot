package com.bot.lotus.mail;

import com.bot.lotus.config.MailConfig;
import com.bot.lotus.log.LotusLogger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.util.List;

public class MailHandler {
    private static final LotusLogger LOG = LotusLogger.of(LoggerFactory.getLogger(MailHandler.class));
    private static final String FROM_MAIL = "petzdavid@gmail.com";
    private final Recipients recipients;
    private final MailConfig mailConfig;

    public MailHandler() {
        this.mailConfig = new MailConfig();
        this.recipients = new Recipients();
    }

    public void sendNotifications(final List<String> notifications) {
        final Session session = mailConfig.initConfig();
        notifications.forEach(current -> sendMailToRecipients(current, session));
    }

    private void sendMailToRecipients(final String body, final Session session) {
        try {
            for (final String currentAddress : recipients.getList()) {
                sendMail(currentAddress, body, session);
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    private void sendMail(final String to, final String body, final Session session) throws Exception {
        LOG.info("Sending mail");
        final Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_MAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Discount on watched item");

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}
