package com.bot.lotus.dao;



import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.bot.lotus.model.LotusItem;

import org.hibernate.Session;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;


@RunWith(MockitoJUnitRunner.class)
public class LotusItemDaoTest {

    @Mock
    private SessionFactory sessionFactory;

    private LotusItemDao underTest;

    @Before
    public void before() {
        underTest = new LotusItemDao(sessionFactory);
    }

    @Test
    public void findAllShouldReturnListOfLotusItems() {
        // given
        final Session session = Mockito.mock(Session.class);
        final Query query = Mockito.mock(Query.class);
        
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery(any(), any())).thenReturn(query);
        when(query.list()).thenReturn(new ArrayList<>());
        
        var expected = new ArrayList<>();
        
        // when
		var actual = underTest.findAll();
		
        // then
		assertEquals(expected, actual);
		}
    
    @Test
    public void findAllShouldReturnEmptyListIfExceptionHappened() {
        // given
        final Session session = Mockito.mock(Session.class);		
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery(any(), any())).thenThrow(new NullPointerException("Error occurred!"));
        
        var expected = new LinkedList<>();
        
        // when
		var actual = underTest.findAll();
		
        // then
		assertEquals(expected, actual);
		}
    
    @Test(expected=NullPointerException.class)
    public void updateShouldThrowExceptionWhenEmptyListWasGiven() {
        // given
        final Session session = Mockito.mock(Session.class);
        final Transaction transaction = Mockito.mock(Transaction.class);
        
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        
        
        final ArrayList<LotusItem> emptyList = new ArrayList<>();
        
        // when
		underTest.update(emptyList);
		
        // then
		
		}
    @Test(expected=Error.class)
    public void updateShouldThrowExceptionWhenListWithNotLotusItemsWasGiven() {
        // given
        final Session session = Mockito.mock(Session.class);
        final Transaction transaction = Mockito.mock(Transaction.class);
        
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        
        
        final ArrayList<Integer> emptyList = new ArrayList<>();
        
        // when
		underTest.update(emptyList);
		
        // then
		
		}
}