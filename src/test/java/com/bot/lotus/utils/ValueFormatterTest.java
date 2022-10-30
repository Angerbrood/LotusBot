package com.bot.lotus.utils;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.Test;

import static org.junit.Assert.assertEquals;



@RunWith(MockitoJUnitRunner.class)
public class ValueFormatterTest {

    @Test
    public void shouldReturnWithoutWhitecpacesAndHufIfExpectedValueIsGiven () {
        // Given
        final String valueToFormat = "12345";
        final String expected = "12345";

        // When
        String actual = ValueFormatter.formatPriceValue(valueToFormat);

        // Then
        assertEquals(actual, expected);

    }

    @Test
    public void shouldReturnWithoutWhitecpacesAndHufIfWhitespaceAtTheBeginAndEnd () {
        // Given
        final String valueToFormat = " 12345 ";
        final String expected = "12345";

        // When
        final String actual = ValueFormatter.formatPriceValue(valueToFormat);

        // Then
        assertEquals(expected, actual);

    }

    @Test
    public void shouldReturnWithoutWhitecpacesAndHufIfWhitespaceInMiddleAndHuf () {
        // Given
        final String valueToFormat = "12345 Ft";
        final String expected = "12345";

        // When
        final String actual = ValueFormatter.formatPriceValue(valueToFormat);

        // Then
        assertEquals(expected, actual);

    }

    @Test
    public void shouldReturnWithoutWhitecpacesAndHufIfWhitespaceInMiddleAndHufAndWhitespaceAtBeginAndEnd () {
        // Given
        final String valueToFormat = " 12345 Ft ";
        final String expected = "12345";

        // When
        final String result = ValueFormatter.formatPriceValue(valueToFormat);

        // Then
        assertEquals(expected, result);

    }

    @Test
    public void shouldReturnWithoutWhitecpacesAndHufIfWhitespaceInMiddleAndHufIsLowercase () {
        // Given
        final String valueToFormat = " 12345 ft ";
        final String expected = "12345";

        // When

        final String actual = ValueFormatter.formatPriceValue(valueToFormat);

        // Then
        assertEquals(expected, actual);

    }

    @Test
    public void shouldReturnWithoutWhitecpacesAndHufIfRandomWhitespaceInValue () {
        // Given
        final String valueToFormat = "12 345";
        final String expected = "12345";

        // When
        final String actual = ValueFormatter.formatPriceValue(valueToFormat);

        // Then
        assertEquals(expected, actual);

    }
}
