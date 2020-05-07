package com.study.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintHundred {

    public static void main(String[] args) {
        Resource resource = new Resource();
        List<Runnable> runList = new ArrayList<>();
        runList.add(() -> {
            resource.printNnm(resource.firstCondition, resource.secondCondition);
        });
        runList.add(() -> {
            resource.printNnm(resource.secondCondition, resource.thridCondition);
        });
        runList.add(() -> {
            resource.printNnm(resource.thridCondition, resource.firstCondition);
        });
        for (int i = 1; i <= runList.size(); i++) {
            new Thread(runList.get(i - 1), "" + i).start();
        }
    }

    static class Resource {

        int num = 0;
        Lock lock = new ReentrantLock();
        Condition firstCondition = lock.newCondition();
        Condition secondCondition = lock.newCondition();
        Condition thridCondition = lock.newCondition();

        public void printNnm(Condition self, Condition next) {
            lock.lock();
            try {
                while (num < 99) {
                    num += 1;
                    System.out.println("线程" + Thread.currentThread().getName() + "打印num当前值" + num);
                    next.signal();
                    self.await();
                }
                next.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

}
