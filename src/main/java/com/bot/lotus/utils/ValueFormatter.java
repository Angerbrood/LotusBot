package com.bot.lotus.utils;

public class ValueFormatter {
    private ValueFormatter() {

    }

    public static String formatValue(final String value) {
        final StringBuilder sb = new StringBuilder(value);
        removeSpaceCharsFromBeginAndEnd(sb);
        removeHufAndMiddleSpace(sb);
        return sb.toString();
    }

    private static void removeSpaceCharsFromBeginAndEnd(StringBuilder sb) {
        if(sb.charAt(0) == ' ') {
            sb.deleteCharAt(0);
        }
        if(sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static void removeHufAndMiddleSpace(StringBuilder sb) {
        if (sb.toString().contains("Ft")) {
            int lastIndexOfSpace = sb.lastIndexOf(" ");
            sb.delete(lastIndexOfSpace, sb.length());
            sb.deleteCharAt(sb.lastIndexOf(" "));
        }
    }

}
