package com.example.experimentapp;

import org.junit.Test;

import static org.junit.Assert.*;

import kotlinx.coroutines.internal.ConcurrentLinkedListNode;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void string_reversal(){
        Utility sut = new Utility();
        String result = sut.stringReversal("shine");
        assertEquals("enihs",result);
    }


}