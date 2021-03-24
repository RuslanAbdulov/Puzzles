package com.company.reverse_linked_list;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ODListTest {

    private ODList<String> list = new ODList<String>().add("1").add("2").add("3");

    @Test
    public void testToString() {
        assertEquals("ODList[1, 2, 3]", list.toString());
    }

    @Test
    public void testReverse() {
        assertEquals("ODList[3, 2, 1]", list.reverse().toString());
    }

    @Test
    public void testNaiveReverse() {
        list.naiveReverse();
    }
}