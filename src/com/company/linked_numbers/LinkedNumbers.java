package com.company.linked_numbers;

import java.util.LinkedList;
import java.util.ListIterator;

//Given two numbers represented by two linked lists, write a function that returns sum list.
//[5 -> 6 -> 3] + [8 -> 4 -> 2] = [1 -> 4 -> 0 -> 5]
public class LinkedNumbers {

    public static void main(String[] args) {

        LinkedList<Integer> num1 = linkedNumber(563);
        LinkedList<Integer> num2 = linkedNumber(842);

        LinkedList<Integer> sum = linkedSum(num1, num2);

        //1 -> 4 -> 0 -> 5
        System.out.println(sum);
    }

    private static LinkedList<Integer> linkedSum(LinkedList<Integer> num1, LinkedList<Integer> num2) {
        LinkedList<Integer> sum = new LinkedList<>();

        ListIterator<Integer> it1 = num1.listIterator(num1.size());
        ListIterator<Integer> it2 = num2.listIterator(num2.size());

        int remainder = 0;
        while (it1.hasPrevious() || it2.hasPrevious()) {
            int digit1 = it1.hasPrevious()? it1.previous() : 0;
            int digit2 = it2.hasPrevious()? it2.previous() : 0;

            int posValue = digit1 + digit2 + remainder;
            sum.push(posValue % 10);
            remainder = posValue / 10;
        }

        if (remainder > 0) {
            sum.push(remainder);
        }

        return sum;
    }

    private static LinkedList<Integer> linkedNumber(int num) {
        LinkedList<Integer> linkedNum = new LinkedList<>();
        int remainder = num;
        while (remainder > 0) {
            linkedNum.push(remainder % 10);
            remainder = remainder / 10;
        }

        return linkedNum;
    }
}
