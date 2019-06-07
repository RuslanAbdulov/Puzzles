package com.company;


public class Multiplication {

    public static void main(String[] args) {


        System.out.println(methodName(2, 5));
    }

    private static void print(int n) {
        if (n > 100) {
            return;
        }

        System.out.println(n);
        print(n+1);
    }

    protected static final Integer methodName(Integer x, Integer y) {
        return y > 0 ? x + methodName(x, y - 1) : 0;
    }

}
