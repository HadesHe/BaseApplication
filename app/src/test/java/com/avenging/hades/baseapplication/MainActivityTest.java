package com.avenging.hades.baseapplication;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Hades on 2017/6/5.
 */
public class MainActivityTest {




    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testWhenThenReturn() throws Exception {


    }

    @Test
    public void testMoreThanOneReturnValue() throws Exception {
        Iterator<String> iterator = mock(Iterator.class);

        when(iterator.next()).thenReturn("Mockito").thenReturn("rocks");
        String result=iterator.next()+" "+iterator.next();
        assertEquals(result,"Mockito rocks");

    }

    @Test
    public void testDifferentMethodParameter() throws Exception {
        Comparable<Integer> comparable=mock(Comparable.class);

        when(comparable.compareTo(1)).thenReturn(1);
        when(comparable.compareTo(2)).thenReturn(2);

        assertEquals(comparable.compareTo(1),1);

        List list=mock(ArrayList.class);
        doAnswer(returnsFirstArg()).when(list).add(anyString());


    }
}