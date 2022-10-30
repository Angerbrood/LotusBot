package com.bot.lotus.utils;

import com.bot.lotus.log.LotusLogger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ConfigReader {
    private static final LotusLogger LOG = LotusLogger.of(LoggerFactory.getLogger(ConfigReader.class));
    private ConfigReader() {}

    public static Properties readProperties(final String filePath) {
        LOG.info(String.format("Reading property file from %s", filePath));
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(filePath)) {
            if (input == null) {
                LOG.error(String.format("Sorry, unable to find %s", filePath));
                throw new IllegalArgumentException();
            }
            Properties prop = new Properties();
            prop.load(input);
            LOG.info("Reading has finished");
            return prop;
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            throw new IllegalArgumentException(ex);
        }
    }

    public static List<String> readFile(final String filePath) {
        final InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            LOG.error(String.format("Sorry, unable to find %s", filePath));
            throw new IllegalArgumentException();
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            LOG.info(String.format("Reading text file from %s", filePath));
            final List<String> results = new LinkedList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                results.add(line);
            }
            inputStream.close();
            LOG.info("Reading has finished");
            return results;
        } catch(Exception ex) {
            LOG.error(ex.getMessage(), ex);
            throw new IllegalArgumentException(ex);
        }
    }
}
