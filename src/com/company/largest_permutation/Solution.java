package com.company.largest_permutation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class Solution {

    static void swap(int[] arr, int f, int s) {
        int buf = arr[f];
        arr[f] = arr[s];
        arr[s] = buf;
    }

    static int[] largestPermutation(int k, int[] arr, int n) {
        // Complete this function
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != n-i) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] == n-i) {
                        swap(arr, i, j);
                        if (--k == 0) {
                            return arr;
                        }
                    }
                }
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            in = new Scanner(new FileReader("src/com/company/largest_permutation/input.txt"));
        } catch (FileNotFoundException e) {
            //use System.in
        }
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for(int arr_i = 0; arr_i < n; arr_i++){
            arr[arr_i] = in.nextInt();
        }
        int[] result = largestPermutation(k, arr, n);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
        }
        System.out.println("");


        in.close();
    }
}