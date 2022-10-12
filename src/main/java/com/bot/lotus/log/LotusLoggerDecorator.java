package com.bot.lotus.log;

import org.slf4j.Logger;
import org.slf4j.Marker;

public abstract class LotusLoggerDecorator implements Logger {
    protected final Logger decoratedLogger;

    protected LotusLoggerDecorator(final Logger decoratedLogger) {
        this.decoratedLogger = decoratedLogger;
    }

    @Override
    public String getName() {
        return decoratedLogger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return decoratedLogger.isTraceEnabled();
    }

    @Override
    public void trace(String s) {
        decoratedLogger.trace(s);
    }

    @Override
    public void trace(String s, Object o) {
        decoratedLogger.trace(s, o);
    }

    @Override
    public void trace(String s, Object o, Object o1) {
        decoratedLogger.trace(s, o, o1);
    }

    @Override
    public void trace(String s, Object... objects) {
        decoratedLogger.trace(s, objects);
    }

    @Override
    public void trace(String s, Throwable throwable) {
        decoratedLogger.trace(s, throwable);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return decoratedLogger.isTraceEnabled();
    }

    @Override
    public void trace(Marker marker, String s) {
        decoratedLogger.trace(marker, s);
    }

    @Override
    public void trace(Marker marker, String s, Object o) {
        decoratedLogger.trace(marker, s, o);
    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o1) {
        decoratedLogger.trace(marker, s,o, o1);
    }

    @Override
    public void trace(Marker marker, String s, Object... objects) {
        decoratedLogger.trace(marker, s, objects);
    }

    @Override
    public void trace(Marker marker, String s, Throwable throwable) {
        decoratedLogger.trace(marker, s, throwable);
    }

    @Override
    public boolean isDebugEnabled() {
        return decoratedLogger.isDebugEnabled();
    }

    @Override
    public void debug(String s) {
        decoratedLogger.debug(s);
    }

    @Override
    public void debug(String s, Object o) {
        decoratedLogger.debug(s, o);
    }

    @Override
    public void debug(String s, Object o, Object o1) {
        decoratedLogger.debug(s, o, o1);
    }

    @Override
    public void debug(String s, Object... objects) {
        decoratedLogger.debug(s, objects);
    }

    @Override
    public void debug(String s, Throwable throwable) {
        decoratedLogger.debug(s, throwable);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return decoratedLogger.isTraceEnabled();
    }

    @Override
    public void debug(Marker marker, String s) {
        decoratedLogger.debug(marker, s);
    }

    @Override
    public void debug(Marker marker, String s, Object o) {
        decoratedLogger.debug(marker, s, o);
    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o1) {
        decoratedLogger.debug(marker, s, o, o1);
    }

    @Override
    public void debug(Marker marker, String s, Object... objects) {
        decoratedLogger.debug(marker, s, objects);
    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable) {
        decoratedLogger.debug(marker, s, throwable);
    }

    @Override
    public boolean isInfoEnabled() {
        return decoratedLogger.isInfoEnabled();
    }

    @Override
    public void info(String s) {
        decoratedLogger.info(s);
    }

    @Override
    public void info(String s, Object o) {
        decoratedLogger.info(s, o);
    }

    @Override
    public void info(String s, Object o, Object o1) {
        decoratedLogger.info(s, o, o1);
    }

    @Override
    public void info(String s, Object... objects) {
        decoratedLogger.info(s, objects);
    }

    @Override
    public void info(String s, Throwable throwable) {
        decoratedLogger.info(s, throwable);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return decoratedLogger.isInfoEnabled();
    }

    @Override
    public void info(Marker marker, String s) {
        decoratedLogger.info(marker, s);
    }

    @Override
    public void info(Marker marker, String s, Object o) {
        decoratedLogger.info(marker, s, o);
    }

    @Override
    public void info(Marker marker, String s, Object o, Object o1) {
        decoratedLogger.info(marker, s, o, o1);
    }

    @Override
    public void info(Marker marker, String s, Object... objects) {
        decoratedLogger.info(marker, s, objects);
    }

    @Override
    public void info(Marker marker, String s, Throwable throwable) {
        decoratedLogger.info(marker, s, throwable);
    }

    @Override
    public boolean isWarnEnabled() {
        return decoratedLogger.isWarnEnabled();
    }

    @Override
    public void warn(String s) {
        decoratedLogger.warn(s);
    }

    @Override
    public void warn(String s, Object o) {
        decoratedLogger.warn(s, o);
    }

    @Override
    public void warn(String s, Object... objects) {
        decoratedLogger.warn(s, objects);
    }

    @Override
    public void warn(String s, Object o, Object o1) {
        decoratedLogger.warn(s, o, o1);
    }

    @Override
    public void warn(String s, Throwable throwable) {
        decoratedLogger.warn(s, throwable);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return decoratedLogger.isWarnEnabled();
    }

    @Override
    public void warn(Marker marker, String s) {
        decoratedLogger.warn(marker, s);
    }

    @Override
    public void warn(Marker marker, String s, Object o) {
        decoratedLogger.warn(marker, s, o);
    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o1) {
        decoratedLogger.warn(marker, s, o, o1);
    }

    @Override
    public void warn(Marker marker, String s, Object... objects) {
        decoratedLogger.warn(marker, s, objects);
    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable) {
        decoratedLogger.warn(marker, s, throwable);
    }

    @Override
    public boolean isErrorEnabled() {
        return decoratedLogger.isErrorEnabled();
    }

    @Override
    public void error(String s) {
        decoratedLogger.error(s);
    }

    @Override
    public void error(String s, Object o) {
        decoratedLogger.error(s, o);
    }

    @Override
    public void error(String s, Object o, Object o1) {
        decoratedLogger.error(s, o, o1);
    }

    @Override
    public void error(String s, Object... objects) {
        decoratedLogger.error(s, objects);
    }

    @Override
    public void error(String s, Throwable throwable) {
        decoratedLogger.error(s, throwable);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return decoratedLogger.isErrorEnabled();
    }

    @Override
    public void error(Marker marker, String s) {
        decoratedLogger.error(marker, s);
    }

    @Override
    public void error(Marker marker, String s, Object o) {
        decoratedLogger.error(marker, s, o);
    }

    @Override
    public void error(Marker marker, String s, Object o, Object o1) {
        decoratedLogger.error(marker, s, o, o1);
    }

    @Override
    public void error(Marker marker, String s, Object... objects) {
        decoratedLogger.error(marker, s, objects);
    }

    @Override
    public void error(Marker marker, String s, Throwable throwable) {
        decoratedLogger.error(marker, s, throwable);
    }
}
