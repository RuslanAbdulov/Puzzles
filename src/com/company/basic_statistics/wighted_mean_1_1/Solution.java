package com.company.basic_statistics.wighted_mean_1_1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            in = new Scanner(new FileReader("src/com/company/basic_statistics/wighted_mean_1_1/input.txt"));
        } catch (FileNotFoundException e) {
            //use System.in
        }
        int n = in.nextInt();
        List<Integer> numbers = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            numbers.add(in.nextInt());
        }
        List<Integer> weights = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            weights.add(in.nextInt());
        }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */


        System.out.println(weightedMean(numbers, weights));
    }


    private static double weightedMean(List<Integer> numbers, List<Integer> weights) {
        long weightedSum = 0;
        long totalWeight = 0;
        for (int i = 0; i < numbers.size(); i++) {
            weightedSum += numbers.get(i) * weights.get(i);
            totalWeight += weights.get(i);
        }

        long sum = numbers.stream().reduce(0, Integer::sum);
        return weightedSum / totalWeight;
    }

    private static String format(double value) {
        return new DecimalFormat("#0.0").format(value);
    }
}

