package com.bot.lotus.utils;

import java.util.Optional;

public class ValueFormatter {
    private ValueFormatter() {

    }

    public static String formatPriceValue(final String value) {
        return Optional.ofNullable(value)
                .map(c -> c.replaceAll("\\D+", ""))
                .orElseThrow(IllegalArgumentException::new);
    }
}
