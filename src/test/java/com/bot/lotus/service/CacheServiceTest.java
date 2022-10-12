package com.bot.lotus.service;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CacheServiceTest {
    private static final String DB_PATH = "lotus.db";
    private CacheService underTest;

    @Before
    public void before() {
        final String dbPath = CacheServiceTest.class.getClassLoader().getResource(DB_PATH).getPath();
        if(StringUtils.isEmpty(dbPath)) {
            throw new IllegalArgumentException("Unable to load testing db");
        }
        underTest = new CacheService(dbPath);
    }

    @Test
    public void test() {

    }

}
