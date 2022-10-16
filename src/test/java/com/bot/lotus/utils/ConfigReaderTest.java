package com.bot.lotus.utils;

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
public class ConfigReaderTest {
	String nonExistentFilePath =  "doesntExist.txt";
	String notTxtorPropertiesFile = "test.zip";
	String emptyTxtFile = "test.txt";
	
	public void readFileTestFunction(String fileToGive) {
		File file = new File(fileToGive);
		try {
			if (file.createNewFile()) {
			    assertEquals(Collections.emptyList(), ConfigReader.readFile(fileToGive) );
			    file.delete();
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	
	}
	
	public void readPropertiesTestFunction(String fileToGive) {
		File file = new File(fileToGive);
		try {
			if (file.createNewFile()) {
			    assertEquals(Collections.emptyList(), ConfigReader.readFile(fileToGive) );
			    file.delete();
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	
	}
	@Test
    public void readFileShouldReturnEmptyListIfFilePathDoesNotExist () {
        assertEquals(Collections.emptyList(), ConfigReader.readFile(nonExistentFilePath) );
        
    }
	
	@Test
    public void readFileShouldReturnEmptyListIfFileIsNotTxt () {
		readFileTestFunction(notTxtorPropertiesFile);
		}
	
	@Test
    public void readFileShouldReturnEmptyListIfFileIsAnEmptyTxt () {
		readFileTestFunction(emptyTxtFile);
    }
	
	@Test
    public void readPropertiesShouldReturnEmptyListIfFilePathDoesNotExist() {
				
		assertEquals(Collections.emptyList(), ConfigReader.readProperties(nonExistentFilePath) );
    }
	@Test
    public void readPropertiesShouldReturnEmptyListIfFileIsNotTxt() {
				
		readPropertiesTestFunction(notTxtorPropertiesFile);
    }
	
}


