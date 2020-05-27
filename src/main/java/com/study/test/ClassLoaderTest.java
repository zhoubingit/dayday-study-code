package com.study.test;

/**
 * @Classname ClassLoaderTest
 * @Description TODO
 * @Date 2020/5/27 9:26
 * @Created by zhoubin
 */
public class ClassLoaderTest {

    public static String str = "s";

    public static void t(){

    }

    public void test() {
        System.out.println("\"aa\" = " + "aa");
    }

    public String eat(String e) {
        return str;
    }
}
