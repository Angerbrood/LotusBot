package com.bot.lotus.log;

import org.slf4j.Logger;

public class LotusLogger extends LotusLoggerDecorator {
    private LotusLogger(final Logger decoratedLogger) {
        super(decoratedLogger);
    }

    public static LotusLogger of(final Logger logger) {
        return new LotusLogger(logger);
    }
}
