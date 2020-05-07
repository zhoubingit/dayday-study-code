package com.study.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(6);
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire(5);
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "线程执行结束");
                    semaphore.release(4);
                }
            }, i+"").start();
        }
    }
}
