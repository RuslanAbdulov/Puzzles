package com.company;

public class PascalTriangle {

    public static void main(String[] args) {

        printPascalTriangle(10);
    }


    private static void printPascalTriangle(int n) {
        int[] prevLine = new int[]{1};
        printArray(prevLine);
        for (int i = 1; i < n; i++) {
            int[] line = new int[i+1];
            for (int j = 0; j <= i; j++) {
                line[j] = getPrevValue(prevLine, j-1) + getPrevValue(prevLine, j);
            }
            printArray(line);
            prevLine = line;
        }
    }

    private static int getPrevValue(int[] prev, int index) {
        if (index < 0 || index >= prev.length) {
            return 0;
        }

        return prev[index];
    }

    private static void printArray(int[] array) {
        for (int e :array) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

}
