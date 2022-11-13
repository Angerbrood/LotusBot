package com.bot.lotus.config;

import com.bot.lotus.log.LotusLogger;
import com.bot.lotus.model.LotusItem;
import com.bot.lotus.utils.ConfigReader;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;

public final class HibernateUtil {
    private static final LotusLogger LOG = LotusLogger.of(LoggerFactory.getLogger(HibernateUtil.class));
    private static final String PROPERTY_PATH = "hibernate.properties";
    private static final Class<?>[] ANNOTATED_CLASSES = new Class<?>[] {
            LotusItem.class
    };
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                LOG.info("Creating SessionFactory");
                Configuration configuration = new Configuration();
                configuration.setProperties(getHibernateProperties());
                Arrays.asList(ANNOTATED_CLASSES).forEach(configuration::addAnnotatedClass);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                LOG.info("SessionFactory has been created");
            } catch (Exception ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }
        return sessionFactory;
    }

    private static Properties getHibernateProperties() {
        LOG.info("Starting to read Hibernate properties");
        final Properties externalProperties = ConfigReader.readProperties(PROPERTY_PATH);
        final Properties hibernateProperties = new Properties();
        hibernateProperties.put(Environment.DRIVER, externalProperties.getProperty("db.driver"));
        hibernateProperties.put(Environment.URL, externalProperties.getProperty("db.url"));
        hibernateProperties.put(Environment.USER, externalProperties.getProperty("db.username"));
        hibernateProperties.put(Environment.PASS, externalProperties.getProperty("db.password"));
        hibernateProperties.put(Environment.DIALECT, externalProperties.getProperty("hibernate.dialect"));
        hibernateProperties.put(Environment.SHOW_SQL, externalProperties.getProperty("hibernate.show_sql"));
        hibernateProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,
                externalProperties.getProperty("hibernate.session.context.class"));
        hibernateProperties.put(Environment.HBM2DDL_AUTO, externalProperties.getProperty("hibernate.hbm2ddl.auto"));
        LOG.info("Hibernate properties reading has finished");
        return hibernateProperties;
    }

    private HibernateUtil() {}
}
