package com.leetcode.rotate_array;

import java.util.Arrays;
import java.util.stream.Collectors;


public class RotateArray {

    public static void main(String[] args) {
        //case 1
//        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
//        int k = 3;

        //TODO case 2
        int[] nums = new int[]{-1};
        int k = 2;

        //TODO case 3: [1, 2, 3], k=5, result [2, 3, 1]

        new Solution().rotate(nums, k);
        print(nums);
    }

    private static void print(int[] nums) {
        System.out.println(Arrays.stream(nums)
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.joining(",")));
    }


}
