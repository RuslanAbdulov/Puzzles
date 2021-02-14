package com.company.longest_palindrome;

import java.util.Arrays;

class Solution {

    public String longestPalindrome(String s) {

        char[] charArray = s.toCharArray();

        String pal = fast(charArray);

        return pal.length() > 0 ? pal : "";
    }

    String fast(char[] charArray) {
        for (int size = charArray.length; size > 0; size--) {
            int steps = charArray.length - size + 1;
            for (int j = 0; j < steps; j++) {
                if (isPalindrome(Arrays.copyOfRange(charArray, j, size + j))) {
                    return new String(Arrays.copyOfRange(charArray, j, size + j));
                }
            }

        }
        return "";
    }

    boolean isPalindrome(char[] chars) {
        if (chars.length == 1) {
            return true;
        }
        for (int i = 0; i < chars.length/2 ; i++) {
            if (chars[i] != chars[chars.length - i -1]) {
                return false;
            }
        }
        return true;
    }

}