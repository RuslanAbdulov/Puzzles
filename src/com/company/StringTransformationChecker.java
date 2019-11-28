package com.company;

public class StringTransformationChecker {

    public static void main(String[] args) {

        System.out.println(compare("aaabb", "aacbb"));
        System.out.println(compare("aabb", "aaabb"));
        System.out.println(compare("aabb", "aacbb"));
    }


    //Swap a and b to make a.length <= b.length
    private static boolean compare(String a, String b) {
        int diff = 0;

        int i = 0, j = 0;
//        if (b.length() != a.length() && a.charAt(i) != b.charAt(j)) {
//            diff++;
//            j++;
//        }
        int extra = b.length() - a.length();
        while(i < a.length() && j < b.length()) {
            if (a.charAt(i) != b.charAt(j)) {
                diff++;
                if (extra > 0) {
                    extra--;
                    j++;
                } else {
                    i++;
                    j++;
                }
            } else {
                i++;
                j++;
            }

        }

        if (b.length() != a.length() && j == i) {
            diff++;
        }
        return !(diff > 1);
    }


}
