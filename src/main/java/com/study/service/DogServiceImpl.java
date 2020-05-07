package com.study.service;

public class DogServiceImpl implements Command {
    public void excute() {
        System.out.println("进入狗的实现类");
    }

    public static void main(String[] args) {
        Integer i = 1000;
        add(i);
        System.out.println(i);
    }
    public static void add(Integer i){
        i++;
        i = 100;
    }

}

