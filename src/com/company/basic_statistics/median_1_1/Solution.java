package com.company.basic_statistics.median_1_1;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            in = new Scanner(new FileReader("src/com/company/basic_statistics/median_1_1/input.txt"));
        } catch (FileNotFoundException e) {
            //use System.in
        }
        int n = in.nextInt();
        List<Integer> numbers = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            numbers.add(in.nextInt());
        }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        System.out.println(format(mean(numbers)));
        System.out.println(format(median(numbers)));
        System.out.println(mode(numbers));
    }

    private static double mean(List<Integer> numbers) {
        long sum = numbers.stream().reduce(0, Integer::sum);
        return sum * 1.0 / numbers.size();
    }

    private static double median(List<Integer> numbers) {
        Collections.sort(numbers);
        int middle = numbers.size() / 2;
        if (numbers.size() % 2 == 1) {
            return numbers.get(middle);
        } else {
            return (numbers.get(middle - 1) + numbers.get(middle)) * 1.0 / 2;
        }
    }

    private static int mode(List<Integer> numbers) {
        Map<Integer, Integer> frequencyMap = new HashMap<>(numbers.size());
        numbers.forEach(num -> frequencyMap.compute(num, (k, v) -> (v == null) ? 1 : v + 1));
        return  frequencyMap.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(0);

    }

    private static String format(double value) {
        return new DecimalFormat("#0.0").format(value);
    }
}

