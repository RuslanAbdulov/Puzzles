package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> intList = new ArrayList<>();
        List<? extends Number> numList;

        numList = intList;
//        numList.add(Integer.valueOf(1));
//        numList.add(2.0);


    }

}
