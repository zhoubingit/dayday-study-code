package com.zhoubin.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReenTrantLockTest {

    /**
     *  可重入锁/递归锁
     *  持有锁的方法调用该锁的其他方法可直接执行,不需要重新获取锁,不会造成死锁
     *  ReentrantLock synchronized 都是可重入锁
     */
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(() -> {
            resource.one();
        }, "T1").start();
        new Thread(() -> {
            resource.two();
        }, "T2").start();
        try {
            TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        new Thread(() -> {
            resource.three();
        }, "T3").start();
        new Thread(() -> {
            resource.four();
        }, "T4").start();

    }

    /**
     *  资源类
     */
   static class Resource{

        AtomicReference<Thread> atomicReference = new AtomicReference<>();

        Lock lock = new ReentrantLock();

        public synchronized void one(){
            System.out.println(Thread.currentThread().getName()+"线程进入 one 方法");
            two();
        }
        public synchronized void two(){
            System.out.println(Thread.currentThread().getName()+"线程进入 two 方法");
        }
        public synchronized void three(){
            System.out.println(Thread.currentThread().getName()+"线程进入 three 方法");
            lock.lock();
            try{
                four();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        public synchronized void four(){
            System.out.println(Thread.currentThread().getName()+"线程进入 four 方法");
        }

    }
}


