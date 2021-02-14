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

        assertEquals("a" , result);
    }


    @Test
    public void test4() {
        String result = solution.longestPalindrome("aacabdkacaa");

        assertEquals("aca" , result);
    }

    @Test
    public void test5() {
        String result = solution.longestPalindrome("acabdaca");

        assertEquals("aca" , result);
    }

    @Test
    public void test6() {
        String result = solution.longestPalindrome("aaca");

        assertEquals("aca" , result);
    }

    //2^N
    @Test
    public void test8() {
        String result = solution.longestPalindrome("bacabab");

        assertEquals("bacab", result);
    }

    @Test
    public void test9() {
        String result = solution.longestPalindrome("xaabacxcabaaxcabaax");

        assertEquals("xaabacxcabaax", result);
    }

    @Test
    public void test7_VerySlow() {
        String result = solution.longestPalindrome("abbcccbbbcaaccbababcbcabca");

        assertThat(Sets.newHashSet("cbababc", "bbcccbb"), hasItem(result));
    }

    @Test
    public void test10_VeryVerySlow() {
        String result = solution.longestPalindrome("babaddtattarrattatddetartrateedredividerb");

        assertThat(Sets.newHashSet("ddtattarrattatdd"), hasItem(result));
    }


}