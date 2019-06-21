package com.zhoubin.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockTest {

    /**
     * 自旋锁:当前线程自己当锁,执行过后放弃锁,其他线程循环获取锁
     * 优点:线程不会阻塞，循环去获取锁，减少线程被唤醒的时间，提升性能
     * 缺点:浪费cpu资源,类似CAS的unsafe.compareandset 一直获取不到锁会一直循环获取 容易造成死锁
     * @param args
     */
    public static void main(String[] args) {
        Resource resource = new Resource();

        new Thread(() -> {
            resource.lock();
            resource.lock(); //自己获取到了锁 再去获取锁造成了死锁
            try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
            resource.unlock();
        }, "T1").start();

        new Thread(() -> {
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            resource.lock();
        }, "T2").start();
    }

    /**
     * 资源类
     */
    static class Resource{

        AtomicReference<Thread> atomicReference = new AtomicReference<>();

        public void lock(){
            Thread thread = Thread.currentThread();
            // 循环去占有锁 锁位置为null 当前线程就当锁
            while(!atomicReference.compareAndSet(null,thread)){
                System.out.println(thread.getName()+"线程尝试获取锁失败");
            }
            System.out.println(thread.getName()+"线程获取到了锁");
        }

        public void unlock(){
            Thread thread = Thread.currentThread();
            atomicReference.compareAndSet(thread,null);
            System.out.println(thread.getName()+"线程释放了锁");
        }

    }
}
