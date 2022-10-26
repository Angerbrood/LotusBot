package com.bot.lotus.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.bot.lotus.model.LotusItem;

@RunWith(MockitoJUnitRunner.class)
public class CompareServiceTest {

	@Test(expected = NumberFormatException.class) // Then
    public void compareShouldReturnExceptionWhenGivenLotusitemContainsNulls () {
		// Given:
		CompareService compareService = new CompareService();
		LotusItem itemToGive = new LotusItem(null, null, null);
		List<LotusItem> ListOfItemsToGive = new ArrayList<LotusItem>();
		ListOfItemsToGive.add(itemToGive);
		// When:
		compareService.compare(ListOfItemsToGive, ListOfItemsToGive);
	   }
	
	
    public void compareShouldReturnCreateNotificationIfFirstParameterPriceIsHigherThanSecond () {
		// Given:
		CompareService compareService = new CompareService();
		LotusItem itemToGiveInFirstList = new LotusItem("FirstP", "5", "url");
		List<LotusItem> FirstParameterList = new ArrayList<LotusItem>();
		FirstParameterList.add(itemToGiveInFirstList);
		
		LotusItem itemToGiveInSecondList = new LotusItem("SecondP", "1", "url");
		List<LotusItem> SecondParameterList = new ArrayList<LotusItem>();
		SecondParameterList.add(itemToGiveInSecondList);
		
		// When:
		final StringBuilder sb = new StringBuilder();
        sb.append("Lotus WebShop price change has been detected.")
                .append("<br>")
                .append("<br>");
        sb.append("The price of watched item: ").append(itemToGiveInSecondList.getTitle())
                .append(" has been changed from ").append(itemToGiveInFirstList.getPrice()).append(" Ft")
                .append(" to ").append(itemToGiveInSecondList.getPrice()). append(" Ft.");
        sb.append("<br>").append("<br>");
        sb.append("Click here to visit: ").append(itemToGiveInSecondList.getUrl());
        String expected = sb.toString();
		
		var actual = compareService.compare(FirstParameterList, SecondParameterList);
		
		// Then:
		assertEquals(expected, actual);
	   }
    
    public void compareShouldReturnEmptyListIfParametersPricesAreEqual () {
		// Given:
		CompareService compareService = new CompareService();
		LotusItem itemToGiveInFirstList = new LotusItem("FirstP", "1", "url");
		List<LotusItem> FirstParameterList = new ArrayList<LotusItem>();
		FirstParameterList.add(itemToGiveInFirstList);
		
		LotusItem itemToGiveInSecondList = new LotusItem("SecondP", "1", "url");
		List<LotusItem> SecondParameterList = new ArrayList<LotusItem>();
		SecondParameterList.add(itemToGiveInSecondList);
		
		// When:
		var expected = new LinkedList<>();		
		var actual = compareService.compare(FirstParameterList, SecondParameterList);
		
		// Then:
		assertEquals(expected, actual);
	   }
}
