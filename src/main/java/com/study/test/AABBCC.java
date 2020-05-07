package com.study.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AABBCC {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = null;
        try {
            // 创建一个最大长度为3的线程池
            threadPoolExecutor = new ThreadPoolExecutor(3, 3, 0,
                    TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
            Resource resource = new Resource();
            // 将要执行的任务插入到集合中
            List<Runnable> list = new ArrayList<>();
//            list.add(()->{try { resource.printAA("BB"); } catch (InterruptedException e) { e.printStackTrace(); }});
//            list.add(()->{try { resource.printBB("CC"); } catch (InterruptedException e) { e.printStackTrace(); }});
//            list.add(()->{try { resource.printCC();} catch (InterruptedException e) {e.printStackTrace();}});
            list.add(()->{try { resource.printStr("AA", "BB", 3); } catch (InterruptedException e) { e.printStackTrace(); }});
            list.add(()->{try { resource.printStr("BB", "CC", 3); } catch (InterruptedException e) { e.printStackTrace(); }});
            list.add(()->{try { resource.printStr("CC", "", 3);} catch (InterruptedException e) {e.printStackTrace();}});
            list.add(()->{resource.lockPrintStr("AA", "BB",  resource.aCondition,resource.bCondition, 3);});
            list.add(()->{resource.lockPrintStr("BB", "CC",  resource.bCondition, resource.cCondition,3);});
            list.add(()->{resource.lockPrintStr("CC", "", resource.cCondition, null,3);});
            for (int i = 0; i < list.size(); i++) {
                threadPoolExecutor.execute(list.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            if (threadPoolExecutor != null) {
                threadPoolExecutor.shutdown();
            }
        }
    }
}


class Resource {

    String currentPrint = "AA";

    /**
     * 打印AA
     *
     * @param next 下一个要打印的字符串
     * @throws InterruptedException
     */
    public synchronized void printAA(String next) throws InterruptedException {
        while (!Objects.equals(currentPrint, "AA")) {
            wait();
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + ": AA");
        }
        currentPrint = next;
        notify();
    }

    /**
     * 打印BB
     *
     * @param next 下一个要打印的字符串
     * @throws InterruptedException
     */
    public synchronized void printBB(String next) throws InterruptedException {
        while (!Objects.equals(currentPrint, "BB")) {
            wait();
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + ": BB");
        }
        currentPrint = next;
        notify();
    }

    /**
     * 打印CC
     *
     * @throws InterruptedException
     */
    public synchronized void printCC() throws InterruptedException {
        while (!Objects.equals(currentPrint, "CC")) {
            wait();
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + ": CC");
        }
    }

    /**
     * 打印字符串
     *
     * @param currentPrintParam 当前
     * @param next              下一个
     * @param times             打印次数
     * @throws InterruptedException
     */
    public synchronized void printStr(String currentPrintParam, String next, int times) throws InterruptedException {
        while (!Objects.equals(currentPrint, currentPrintParam)) {
            wait();
        }
        for (int i = 0; i < times; i++) {
            System.out.println(Thread.currentThread().getName() + ": "+ currentPrint);
        }
        currentPrint = next;
        notify();
    }

    Lock lock = new ReentrantLock(); // 并发锁
    Condition aCondition = lock.newCondition();
    Condition bCondition = lock.newCondition();
    Condition cCondition = lock.newCondition();

    /**
     * 打印字符串
     * @param currentPrintParam 当前线程打印
     * @param nextPrint 下一个线程打印
     * @param currentCondition 当前线程
     * @param nextCondition 下一个线程
     * @param times 打印次数
     * @throws InterruptedException
     */
    public void lockPrintStr(String currentPrintParam, String nextPrint, Condition currentCondition, Condition nextCondition, int times){
        lock.lock();
        try {
            // 不是要打印的内容 阻塞线程
            while (!Objects.equals(currentPrint, currentPrintParam)) {
                currentCondition.await();
            }
            for (int i = 0; i < times; i++) {
                System.out.println(Thread.currentThread().getName() + ": "+ currentPrint);
            }
            currentPrint = nextPrint;
            // 唤醒下一个线程
            if (!Objects.isNull(nextCondition)) {
                nextCondition.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            if (lock != null) {
                lock.unlock();
            }
        }
    }
}