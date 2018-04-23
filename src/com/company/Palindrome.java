package com.company;



import java.util.*;

public class Palindrome {

    // -x^256-x^5+25x+4   x^3+34x^2-3x+5
    public static void main(String[] args) {
//        System.out.println("Enter 1st palindrome like -x^3-2x^2+x+9");
//        Scanner in = new Scanner(System.in);
//        String p1 = in.nextLine();
        String p1 = "-x^256-x^5+25x+4";
        String p2 = "x^3+34x^2-3x+5";

        HashMap<Integer, Integer> pMap = new HashMap<>();

        cutToMap(p1, pMap);
        System.out.println(pMap);

        cutToMap(p2, pMap);
        System.out.println(pMap);
    }

    private static void cutToMap(String str, HashMap<Integer, Integer> pMap) {

        StringBuffer buffer = new StringBuffer();
        boolean isCoeff = true;
        int coeff = 0;
        int power = 0;

        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);

            if (c == '+' || c == '-' || c == '^' || Character.isLetter(c)) {
                if (isCoeff) {
                    coeff = flushBuffer(buffer);
                } else {
                    power = flushBuffer(buffer);
                    pMap.put(coeff, power);
                }

                buffer = new StringBuffer();
                isCoeff = !isCoeff;

            }

            if (c == '+' || c == '-' || Character.isDigit(c)) {
                buffer.append(c);
            } else {

            }

//            String.valueOf(c).matches("+");
        }




        cutToMap(str, pMap);
    }

    private static int flushBuffer(StringBuffer buffer) {
        return 0;
    }
}