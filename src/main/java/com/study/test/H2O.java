package com.study.test;

public class H2O {
    public static void main(String[] args) {

    }

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "H". Do not change or remove this line.
        releaseOxygen.run();
    }

    static class Hydrogen implements Runnable {

        @Override
        public void run() {

        }
    }

    static class Oxygen implements Runnable {

        @Override
        public void run() {

        }
    }
}
