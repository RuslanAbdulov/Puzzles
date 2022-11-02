package com.leetcode.rotate_array;

import java.util.Arrays;

public class Solution {

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) {
            return;
        }

        int[] buffer = Arrays.copyOfRange(nums, nums.length - k, nums.length);

        for (int i = nums.length - 1; i >= k; i--) {
            nums[i] = nums[i - k];
        }
        for (int i = 0; i < buffer.length; i++) {
            nums[i] = buffer[i];
        }
    }

}
