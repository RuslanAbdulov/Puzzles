package com.company.cherry_pickup;

class Solution {

    public int cherryPickup(int[][] grid) {

        return 0;
    }
}

/*
1 0 -1 1
0 1 -1 1
0 0  0 0
0 0  0 0

Path (0, 0) -> (n - 1, n - 1) always takes 2(n-1) steps
In total (n-1)^2 paths
O(n^3) memory - fast solution
*/
