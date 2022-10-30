package com.bot.lotus.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.bot.lotus.model.LotusItem;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CompareServiceTest {

	private CompareService compareService;

	@Before
	public void before() {
		compareService = new CompareService();
	}

	@Test
	public void compareShouldReturnCreateNotificationIfFirstParameterPriceIsHigherThanSecond () {
		// Given
		LotusItem lotusItem_1 = new LotusItem("FirstP", "5", "url");
		List<LotusItem> cachedItems = new ArrayList<>();
		cachedItems.add(lotusItem_1);

		LotusItem lotusItem_2 = new LotusItem("SecondP", "1", "url");
		final List<LotusItem> fetchItem = new ArrayList<>();
		fetchItem.add(lotusItem_2);

		// When
		final StringBuilder sb = new StringBuilder();
		sb.append("Lotus WebShop price change has been detected.")
				.append("<br>")
				.append("<br>");
		sb.append("The price of watched item: ").append(lotusItem_2.getTitle())
				.append(" has been changed from ").append(lotusItem_1.getPrice()).append(" Ft")
				.append(" to ").append(lotusItem_2.getPrice()). append(" Ft.");
		sb.append("<br>").append("<br>");
		sb.append("Click here to visit: ").append(lotusItem_2.getUrl());
		String expected = sb.toString();

		final List<String> actual = compareService.compare(cachedItems, fetchItem);

		// Then
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertEquals(1, actual.size());
		assertEquals(expected, actual.iterator().next());
	}

	@Test
	public void compareShouldReturnEmptyListIfParametersPricesAreEqual () {
		// Given
		final LotusItem lotusItem_1 = new LotusItem("FirstP", "1", "url");
		final List<LotusItem> cachedItems = new ArrayList<>();
		cachedItems.add(lotusItem_1);

		final LotusItem lotusItem_2 = new LotusItem("SecondP", "1", "url");
		final List<LotusItem> fetchedItems = new ArrayList<>();
		fetchedItems.add(lotusItem_2);

		// When
		final List<String> actual = compareService.compare(cachedItems, fetchedItems);

		// Then
		assertNotNull(actual);
		assertTrue(actual.isEmpty());
	}
}