package com.zhoubin.test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class TestQueue {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayBlockingQueue<>(3);

        queue.add("a");
        queue.add("b");
        queue.add("c");

        System.out.println(queue.remove());
        queue.add("a");
        System.out.println(queue.remove());
    }
}
