package com.company.is_fibo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;


public class Solution {

    private static Set<Long> fibNumbers = new HashSet<>();

    public static void main(String[] args) {

        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        precompute();

        int numberOfCases;
        try {
            numberOfCases = Integer.parseInt(in.readLine());
            for (int i = 0; i < numberOfCases; i++) {
                System.out.println(isFibo(Long.parseLong(in.readLine())));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void precompute() {
        long f = 0;
        long l = 1;
        long n = f + l;
        while (n <= 10000000000L) {
            fibNumbers.add(n);
            f = l;
            l = n;
            n = f + l;
        }
    }


    private static String isFibo(Long num) {

        return fibNumbers.contains(num)?"IsFibo" : "IsNotFibo";
    }

}
