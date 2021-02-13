package com.company.cherry_pickup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {

    public static final Character RIGHT = 'R';
    public static final Character EMPTY = '0';

    public int cherryPickup(int[][] grid) {
        int N = grid.length;
        //int permutations = 2(N-1)!/((N-1)!*(N-1)!)
        //Number of steps 2(N-1)
        //0.. 2(N-1) - (N-1) = 0..N-1

        List<Character[]> permutations = permutations(new Character[0], RIGHT, N-1, 2*(N-1));

//        System.out.println("grid = " + Arrays.deepToString(grid));
        print(permutations);


//        for (int i = 0; i < N-1; i++) {
//            for (int j = 0; j < N; j++) {
//
//            }
//        }

        return 0;
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

    void print(List<Character[]> permutations) {
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
