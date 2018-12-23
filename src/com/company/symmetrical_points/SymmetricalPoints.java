package com.company.symmetrical_points;

import java.util.Arrays;

/**
 * 3) дан массив точек. нужно вернуть true или false в зависимости от того существует ли такая вертикаль,
 * которая делит это множество точек пополам таким образом чтобы точки были симметричны относительно этой вертикали
 */
public class SymmetricalPoints {


    public static void main(String[] args) {
        System.out.println(isSymmetrical(new int[]{1,2,4,6,8,9}));
        System.out.println(isSymmetrical(new int[]{1,2,4,5,8,9}));
        System.out.println(isSymmetrical(new int[]{1,2,5,8,9}));
        System.out.println(isSymmetrical(new int[]{1,2,4,6,8}));
        System.out.println(isSymmetrical(new int[]{3,4,5,6}));
        System.out.println(isSymmetrical(new int[]{3,4,5,7}));
    }

    private static boolean isSymmetrical(int[] points) {
        Arrays.sort(points);
        double globalMid = 0;
        for (int i = 0; i < points.length/2; i++) {
            double localMid = (points[i] + points[points.length-1-i])/2.0;
            if (globalMid == 0) {
                globalMid = localMid;
            } else if (localMid != globalMid) {
                return false;
            }
        }

        return true;
    }

}
