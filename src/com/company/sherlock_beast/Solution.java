package com.company.sherlock_beast;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            in = new Scanner(new FileReader("src/com/company/sherlock_beast/input.txt"));
        } catch (FileNotFoundException e) {
            //use System.in
        }

        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            System.out.println(largestDecentNumber(n));
        }
    }

    private static String largestDecentNumber(int n) {

        if (n / 3 < 1) { // n < 3
            return "-1";
        }

        for (int fiveGroups = n / 3; fiveGroups >= 0; fiveGroups--) {
            int fives = fiveGroups * 3;
            int threes = n - fives;
            if (threes % 5 == 0) {
                return generate(fives, threes);
            }
        }

        return "-1";
    }

    private static String generate(int fives, int threes) {

        return Stream.concat(
                IntStream.range(0, fives).mapToObj(i -> "5"),
                IntStream.range(0, threes).mapToObj(i -> "3"))
                .collect(Collectors.joining(""));
    }
}