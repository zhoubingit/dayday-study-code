package com.study.test;

public class NewThreadMethod {

    public static void main(String[] args) {
        Thread t1 = new MyThread();
        t1.setName("t1");
        t1.start();
    }

}

class MyThread extends Thread{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + "线程启动");
    }

}