package com.bot.lotus.utils;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;


@RunWith(MockitoJUnitRunner.class)
public class ValueFormatterTest {
	
	@Test
    public void shouldReturnWithoutWhitecpacesAndHufIfExpectedValueIsGiven () {
		// Given:
        String valueToFormat = "12345";
        String expected = "12345";
        // When:
        String actual = ValueFormatter.formatValue(valueToFormat);
        // Then:
        assertEquals(actual, expected);
        
    }
	
	@Test
    public void shouldReturnWithoutWhitecpacesAndHufIfWhitespaceAtTheBeginAndEnd () {
		// Given:
        String valueToFormat = " 12345 ";
        String expected = "12345";
        // When:
        String result = ValueFormatter.formatValue(valueToFormat);
        // Then:
        assertEquals(expected, result);
        
    }
	
	@Test
    public void shouldReturnWithoutWhitecpacesAndHufIfWhitespaceInMiddleAndHuf () {
		// Given:
        String valueToFormat = "12345 Ft";
        String expected = "12345";
        // When:
        String result = ValueFormatter.formatValue(valueToFormat);
        // Then:
        assertEquals(expected, result );
        
    }
	
	@Test
    public void shouldReturnWithoutWhitecpacesAndHufIfWhitespaceInMiddleAndHufAndWhitespaceAtBeginAndEnd () {
		// Given:
        String valueToFormat = " 12345 Ft ";
        String expected = "12345";
        // When:
        String result = ValueFormatter.formatValue(valueToFormat);
        // Then:
        assertEquals(expected, result );
        
    }
	
	@Test
    public void shouldReturnWithoutWhitecpacesAndHufIfWhitespaceInMiddleAndHufIsLowercase () {
		// Given:
        String valueToFormat = " 12345 ft ";
        String expected = "12345";
        // When:
        String result = ValueFormatter.formatValue(valueToFormat);
        // Then:
        assertEquals(expected, result );
        
    }
	
	@Test
    public void shouldReturnWithoutWhitecpacesAndHufIfRandomWhitespaceInValue () {
		// Given:
        String valueToFormat = "12 345";
        String expected = "12345";
        // When:
        String result = ValueFormatter.formatValue(valueToFormat);
        // Then:
        assertEquals(expected, result );
        
    }
	
}
