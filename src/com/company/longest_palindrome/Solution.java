package com.company.longest_palindrome;

class Solution {

    public String longestPalindrome(String s) {

        char[] charArray = s.toCharArray();

        String palindrome = dp(charArray, 0, charArray.length - 1, "");

        return palindrome.length() > 0 ? palindrome : "";
    }

    String dp(char[] chars, int from, int to, String common) {
        if (from > to) {
            return common + "" + flip(common);
        }
        if (from == to) {
            return common + chars[from] + flip(common);
        }
        if (chars[from] == chars[to]) {
            return dp(chars, from + 1, to - 1, common + chars[from]);
        }

        String left = dp(chars, from, to - 1, "");
        String right = dp(chars, from + 1, to, "");

        return maxByLength(left, right);
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