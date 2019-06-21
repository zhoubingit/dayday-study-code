package com.zhoubin.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(6);

        List<String> lst = new ArrayList<>();
        lst.add("a");
        lst.add("b");
        lst.add("c");
        lst.add("a");
        lst.add("a");
        lst.add("b");
        lst.add("c");

        System.out.println("未去重前集合:" + lst);

        for (int i = 0; i < lst.size(); i++) {
            for (int j = i+1; j < lst.size();) {
                if(lst.get(i).equals(lst.get(j))){
                    lst.remove(j);
                }else{
                    j++;
                }
            }
        }

        System.out.println("去重后集合:" + lst);
    }
}
