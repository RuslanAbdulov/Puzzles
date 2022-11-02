package com.leetcode.rotate_array;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SolutionTest {

    @Test
    public void testBase() {
        int[] expected = new int[]{5, 6, 7, 1, 2, 3, 4};
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        new Solution().rotate(nums, k);
        assertArrayEquals(expected, nums);
    }

    @Test
    public void testOne() {
        int[] expected = new int[]{-1};
        int[] nums = new int[]{-1};
        int k = 2;

        new Solution().rotate(nums, k);
        assertArrayEquals(expected, nums);
    }

    @Test
    public void testTwoCycles() {
        int[] expected = new int[]{2, 3, 1};
        int[] nums = new int[]{1, 2, 3};
        int k = 5;

        new Solution().rotate(nums, k);
        assertArrayEquals(expected, nums);
    }

}