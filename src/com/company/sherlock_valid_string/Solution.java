package com.company.sherlock_valid_string;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isValid function below.
    static String isValid(String s) {

        //map-reduce {a : 1}
        Map<Character, Integer> firstMap = new HashMap<>();
        for (char ch: s.toCharArray()) {
            firstMap.compute(ch, (k, v) -> v == null? 1 : ++v);
        }

        Map<Integer, Integer> secondMap = new HashMap<>();
        // {1 : 10}, {2: 2}
        firstMap.values().forEach(number -> secondMap.compute(number, (k, v) -> v == null? 1 : ++v));

        //if difference of max - min > 1, then return NO
        Integer min = secondMap.keySet().stream()
                .min(Integer::compareTo).orElse(0);
        Integer max = secondMap.keySet().stream()
                .max(Integer::compareTo).orElse(0);


        if (secondMap.size() > 2) {
            return "NO";
        } else if (secondMap.size() == 1) {
            return "YES";
        }

        if (min == 1 && secondMap.get(min) == 1) {
            return "YES";
        }

        if (max - min == 1 && secondMap.get(max) == 1) {
            return "YES";
        }

        return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
