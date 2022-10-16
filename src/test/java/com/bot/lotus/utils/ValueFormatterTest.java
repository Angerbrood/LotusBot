package com.bot.lotus.utils;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;


@RunWith(MockitoJUnitRunner.class)
public class ValueFormatterTest {
	String resultShouldBe = "12345";
	
	@Test
    public void shouldReturnWithoutWhitecpacesAndHufIfExpectedValueIsGiven () {
		
        String value = "12345";
        
        String result = ValueFormatter.formatValue(value);
        
        assertEquals(resultShouldBe, result );
        
    }
	
	@Test
    public void shouldReturnWithoutWhitecpacesAndHufIfWhitespaceAtTheBeginAndEnd () {
		
        String value = " 12345 ";
       
        String result = ValueFormatter.formatValue(value);
        
        assertEquals(resultShouldBe, result );
        
    }
	
	@Test
    public void shouldReturnWithoutWhitecpacesAndHufIfWhitespaceInMiddleAndHuf () {
		
        String value = "12345 Ft";

        String result = ValueFormatter.formatValue(value);
        
        assertEquals(resultShouldBe, result );
        
    }
	
	@Test
    public void shouldReturnWithoutWhitecpacesAndHufIfWhitespaceInMiddleAndHufAndWhitespaceAtBeginAndEnd () {
		
        String value = " 12345 Ft ";

        String result = ValueFormatter.formatValue(value);
        
        assertEquals(resultShouldBe, result );
        
    }
	
	@Test
    public void shouldReturnWithoutWhitecpacesAndHufIfWhitespaceInMiddleAndHufIsLowercase () {
		
        String value = " 12345 ft ";

        String result = ValueFormatter.formatValue(value);
        
        assertEquals(resultShouldBe, result );
        
    }
	
	@Test
    public void shouldReturnWithoutWhitecpacesAndHufIfRandomWhitespaceInValue () {
		
        String value = "12 345";

        String result = ValueFormatter.formatValue(value);
        
        assertEquals(resultShouldBe, result );
        
    }
	
}
