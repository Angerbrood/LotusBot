package com.bot.lotus.utils;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class ConfigReaderTest {
	
	@Test(expected = NullPointerException.class) // Then
    public void readFileShouldReturnExceptionIfFilePathDoesNotExist () {
		// Given:
        String nonExistentFilePath = "doesntExist.txt";
        
        // When:
        ConfigReader.readFile(nonExistentFilePath);
	   }
	
	@Test
    public void readFileShouldReturnEmptyListIfFileIsNotTxt () {
		// Given:
		String filePath = "test.zip";
		File file = new File(filePath);
		var expected = Collections.emptyList();
		
		try {
			if (file.createNewFile()) {
				// When: 
				var actual = ConfigReader.readFile(filePath);
				
				// Then: 
			    assertEquals(expected, actual);
			    file.delete();
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}}
	
	@Test
    public void readFileShouldReturnEmptyListIfFileIsAnEmptyTxt () {
		// Given:
		String emptyTxtFilePath = "test.zip";
		File file = new File(emptyTxtFilePath);
		var expected = Collections.emptyList();
		
		
		try {
			if (file.createNewFile()) {
				// When:
				var actual = ConfigReader.readFile(emptyTxtFilePath);
				
				// Then:
			    assertEquals(expected, actual);
			    file.delete();
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
    }
	
	@Test(expected = NullPointerException.class) // Then
    public void readPropertiesShouldReturnExceptionIfFilePathDoesNotExist() {
		// Given:
		String nonExistentFilePath = "doesntexist.txt";
		
		// When:
		ConfigReader.readProperties(nonExistentFilePath);
	    }
	
	@Test
    public void readPropertiesShouldReturnEmptyListIfFileIsNotProperties() {
		// Given:
		String filePath = "test.zip";
		File file = new File(filePath);
		var expected = Collections.emptyList();
		
		try {
			if (file.createNewFile()) {
				// When:
			    var actual =  ConfigReader.readProperties(filePath);
			    // Then:
			    assertEquals(expected, actual);
			    file.delete();
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
    }
	
}


