package com.company.arrays_intersection;

import java.util.*;

/**
 * даны 2 массива 1,2,2,4,3,6 и 6,7,1,2,2
 * нужно вернуть массив который содержит пересечение двух других массивов. 1,2,2,6
 */
public class ArraysIntersection {

    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(1,2,2,4,3,6);
        List<Integer> list2 = Arrays.asList(6,7,1,2,2);
        List<Integer> result = new ArrayList<>();

        list1.sort(null);
        list2.sort(null);

        int i = 0, j = 0;

        while (i < list1.size() && j < list2.size()) {

            int comp = list1.get(i).compareTo(list2.get(j));
            if (comp > 0) {
                j++;
            } else if (comp < 0) {
                i++;
            } else {
                result.add(list1.get(i));
                i++;
                j++;
            }
        }

        System.out.println(result);

    }
}
