package com.bot.lotus.utils;

public class ValueFormatter {
    private ValueFormatter() {

    }

    public static String formatPriceValue(final String value) {
        return value.replaceAll("\\D+", "");
    }
}
