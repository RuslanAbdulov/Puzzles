package com.company.longest_palindrome;

import java.util.Arrays;

class Solution {

    public String longestPalindrome(String s) {

        char[] charArray = s.toCharArray();

        String palindrome = dp(charArray, 0, charArray.length - 1);

        return palindrome.length() > 0 ? palindrome : "";
    }

    String dp(char[] chars, int from, int to) {
        if (from > to) {
            return "";
        }

        char[] partial = Arrays.copyOfRange(chars, from, to+1);
        if (isPalindrome(partial)) {
            return new String(partial);
        }

        String left = dp(chars, from, to - 1);
        String right = dp(chars, from + 1, to);

        return maxByLength(left, right);
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

    String maxByLength(String left, String right) {
        if (left.length() >= right.length()) {
            return left;
        } else {
            return right;
        }
    }

    String flip(String str) {
        char[] original = str.toCharArray();
        int length = original.length;
        char[] flipped = new char[length];
        for (int i = length - 1; i >= 0 ; i--) {
            flipped[length - i - 1] =  original[i];
        }
        return new String(flipped);
    }
}