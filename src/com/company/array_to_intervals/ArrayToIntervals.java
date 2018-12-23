package com.company.array_to_intervals;

import java.util.Arrays;

/**
 * дан массив чисел 2,3,4,5,8,9,11,1,0. нужно вернуть строку в которой будут отрезки из интервалов. 0-5,8-9,11
 */
public class ArrayToIntervals {

    public static void main(String[] args) {

        System.out.println(toIntervals(new int[]{2,3,4,5,8,9,11,1,0}));
    }

    private static String toIntervals(int[] numbers){
        Arrays.sort(numbers);
        StringBuilder sb = new StringBuilder();

        sb.append(numbers[0]);
        for (int i = 1; i < numbers.length ; i++) {

            if (numbers[i] - numbers[i-1] > 1) {
                sb.append("-").append(numbers[i-1]).append(",").append(numbers[i]);
                numbers[i-1] = numbers[i];
            }
        }

        return sb.toString();
    }

}
