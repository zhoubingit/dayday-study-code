package com.study.test;

public class StaticTest {
    public static void main(String[] args) {
        test();
    }

    static StaticTest st = new StaticTest();

    static {
        System.out.println("1");
    }
    {
        System.out.println("2");
    }

    StaticTest(){
        System.out.println("3");
        System.out.println("a="+a+",b="+b);
    }

    public static  void test(){
        System.out.println("4");
    }
    int a = 10;
    static  int b = 112;
}
