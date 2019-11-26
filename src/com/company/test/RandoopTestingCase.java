package com.company.test;

public class RandoopTestingCase {

    public Integer doSomething(Integer a, Integer b) {

        return a/b;
    }

    public static void main(String[] args) {
        RandoopTestingCase instance = new RandoopTestingCase();
        System.out.println(instance.doSomething(1,2));
    }
}
