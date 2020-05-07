package com.study.test;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {

    public static void main(String[] args) {
        AtomicReference<Thread> atomicReference = new AtomicReference<>();
        System.out.println(atomicReference.get());


    }

    class Resource{

        AtomicReference<Thread> atomicReference = new AtomicReference<>();

    }

}
