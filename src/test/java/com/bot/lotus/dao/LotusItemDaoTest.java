package com.bot.lotus.dao;

import com.bot.lotus.model.LotusItem;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.hibernate.Session;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        
        // when

        // then
    }
}
