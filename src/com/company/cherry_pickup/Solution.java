package com.company.cherry_pickup;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {

    public static final Character FORWARD = 'F';
    public static final Character EMPTY = '0';

    public int cherryPickup(int[][] grid) {
        int N = grid.length;
        //int permutations = 2(N-1)!/((N-1)!*(N-1)!)
        //Number of steps 2(N-1)
        //0.. 2(N-1) - (N-1) = 0..N-1

        List<Character[]> permutations = permutations(new Character[0], FORWARD, N-1, 2*(N-1));
        Map<Character[], Integer> topScores = topScorePermutations(grid, permutations, true);

        int maxScore = 0;
        for (Map.Entry<Character[], Integer> entry: topScores.entrySet()) {
            Map<Character[], Integer> topReverseScores =
                    topScorePermutations(traversedGrid(grid, entry.getKey()), permutations, false);
            int newScore = topReverseScores.values().stream().findFirst().orElse(0) + entry.getValue();
            if (newScore > maxScore) {
                maxScore = newScore;
                print(Collections.singleton(entry.getKey()));
                print(topReverseScores.keySet());
            }
        }

//        print(permutations);

        return maxScore;
    }

    Map<Character[], Integer> topScorePermutations(int[][] grid, List<Character[]> paths, boolean direct) {
        int direction = direct ? 1 : -1;

        int globalMax = 0;
        Map<Character[], Integer> top = new HashMap<>();

        for (Character[] path: paths) {
            int score = 0;
            int curR = direct ? 0 : grid.length-1;
            int curC = direct ? 0 : grid.length-1;
            if (grid[curR][curC] == -1) {
                continue;
            }
            if (grid[curR][curC] == 1) {
                score++;
            }
            for (int i = 0; i < path.length; i++) {
                if (path[i] == FORWARD) {
                    curC += direction;
                } else {
                    curR += direction;
                }
                if (grid[curR][curC] == -1) {
                    score = -1;
                    break;
                }
                if (grid[curR][curC] == 1) {
                    score++;
                }

            }
            if (score == globalMax && score > 0) {
                top.put(path, score);
            }
            if (score > globalMax) {
                top.clear();
                top.put(path, score);
            }
        }

        return top;
    }

    //Only for direct traversal
    int[][] traversedGrid(int[][] grid, Character[] path) {
        int[][] traversedGrid = Arrays.copyOf(grid, grid.length);
        int curR = 0;
        int curC = 0;
        traversedGrid[curR][curC] = 0;
        for (int i = 0; i < path.length; i++) {
            if (path[i] == FORWARD) {
                curC++;
            } else {
                curR++;
            }
            traversedGrid[curR][curC] = 0;
        }
        return traversedGrid;
    }

    List<Character[]> permutations(Character[] prefix, Character direction, int number, int length) {
        if (number > length) {
            throw new IllegalArgumentException();
        }
        if (number == 0) {
            Character[] permutation = extend(prefix, length, EMPTY);
            return Collections.singletonList(permutation);
        }
        if (number == length) {
            Character[] permutation = extend(prefix, length, direction);
            return Collections.singletonList(permutation);
        }

        Character[] newPrefix1 = extend(prefix, 1, direction);
        Character[] newPrefix2 = extend(prefix, 1, EMPTY);

        List<Character[]> permutations1 = permutations(newPrefix1, direction, number - 1, length - 1);
        List<Character[]> permutations2 = permutations(newPrefix2, direction, number, length - 1);

        return combine(permutations1, permutations2);
    }

    <T> List<T> combine(List<T> first, List<T> second) {
        return Stream.concat(first.stream(), second.stream()).collect(Collectors.toList());
    }

    <T> T[] extend(T[] source, int addLength, T filler) {
        T[] target = Arrays.copyOf(source, source.length + addLength);
        Arrays.fill(target, source.length, target.length, filler);
        return target;
    }

    <T> void print(Collection<T[]> permutations) {
        permutations.stream()
                .map(Arrays::deepToString)
                .forEach(System.out::println);
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
