package com.study.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class WaitDemo {

    public static void main(String[] args) {
        Resource resource = new Resource();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20, () -> {
            resource.play();
        });
        for (int i = 0; i < 20; i++) {
            final int num = i;
            new Thread(() -> {
                resource.dowait(cyclicBarrier);
                System.out.println("唤醒线程 ：" + Thread.currentThread().getName());
            }, "" + i).start();
        }
    }

    public static void mainq(String[] args) {
        Resource resource = new Resource();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.gosleep();
        }, "thread name is A").start();
        new Thread(() -> {
            resource.eat();
        }, "thread name is B").start();
        new Thread(() -> {
            resource.dohomework();
        }, "thread name is D").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.play();
        }, "thread name is C").start();
    }

    static class Resource {

        public synchronized void play() {
            System.out.println(Thread.currentThread().getName() + "线程:玩完了,吃饭睡觉!");
            notify();
        }

        public synchronized void gosleep() {
            System.out.println(Thread.currentThread().getName() + "线程:等待睡觉");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "线程:睡觉了");
        }

        public synchronized void eat() {
            System.out.println(Thread.currentThread().getName() + "线程:等待吃");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "线程:吃完了");
        }

        public synchronized void dohomework() {
            System.out.println(Thread.currentThread().getName() + "线程:等待写作业");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "线程:写完了");
        }

        public synchronized void dowait(CyclicBarrier cyclicBarrier){
            System.out.println(Thread.currentThread().getName() + "线程进入阻塞状态");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
