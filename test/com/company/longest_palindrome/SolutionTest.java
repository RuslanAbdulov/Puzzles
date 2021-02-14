package com.company.longest_palindrome;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class SolutionTest {
    private Solution solution = new Solution();

    @Test
    public void test0() {
        String result = solution.longestPalindrome("a");

        assertEquals("a", result);
    }

    @Test
    public void test1() {
        String result = solution.longestPalindrome("babad");

        assertThat(Sets.newHashSet("bab", "aba"), hasItem(result));
    }

    @Test
    public void test2_Full_odd() {
        String result = solution.longestPalindrome("cabac");

        assertEquals("cabac" , result);
    }

    @Test
    public void test2_Full_even() {
        String result = solution.longestPalindrome("cabbac");

        assertEquals("cabbac" , result);
    }

    @Test
    public void test3_Empty() {
        String result = solution.longestPalindrome("abcde");

        assertEquals("" , result);
    }


    @Test
    public void test4() {
        String result = solution.longestPalindrome("aacabdkacaa");

        assertEquals("aca" , result);
    }
}