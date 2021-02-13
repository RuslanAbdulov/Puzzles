package com.company.cherry_pickup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class SolutionTest {

    private Solution solution = new Solution();

    @Test
    public void test1_NoPath() {
        int[][] grid = new int[][]{
                new int[] {1, 1, -1},
                new int[] {1, -1, 1},
                new int[] {-1, 1, 1}};

        int result = solution.cherryPickup(grid);

        assertEquals(0, result);
    }

    @Test
    public void test4() {
        int[][] grid = new int[][]{
                new int[] {0, 1, -1},
                new int[] {1, 0, -1},
                new int[] {1, 1, 1}};

        int result = solution.cherryPickup(grid);

        assertEquals(5, result);
    }

    @Test
    public void test2() {
        int[][] grid = new int[][]{
                new int[] {1, 0, -1, 1},
                new int[] {0, 1, -1, 1},
                new int[] {0, 0, 0, 0},
                new int[] {0, 0, 0, 0}};

        int result = solution.cherryPickup(grid);

        assertEquals(2, result);
    }

    @Test
    public void test5() {
        int[][] grid = new int[][]{
                new int[] {1, 0, -1},
                new int[] {0, 1, -1},
                new int[] {0, 0, 0}};

        int result = solution.cherryPickup(grid);

        assertEquals(2, result);
    }


    @Test
    public void test3() {
        int[][] grid = new int[][]{
                new int[] {0, 0},
                new int[] {1, 0}};

        int result = solution.cherryPickup(grid);

        assertEquals(1, result);
    }

}