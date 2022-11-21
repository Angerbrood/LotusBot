package com.bot.lotus.dao;

import com.bot.lotus.model.LotusItem;

import jakarta.persistence.Query;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.hibernate.Session;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
    public void dummyTest() {
        // given
        final Session session = Mockito.mock(Session.class);
        final Transaction transaction = Mockito.mock(Transaction.class);
        final Query query = Mockito.mock(Query.class);
        
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.createQuery(any(), any())).thenReturn(query);
        when(query.list()).thenReturn(new ArrayList<>());
        
        
        // when
		underTest.findAll();
		
        // then
		
		}
}