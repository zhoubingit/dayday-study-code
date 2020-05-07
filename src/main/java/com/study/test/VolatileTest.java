package com.study.test;

import java.util.concurrent.TimeUnit;

public class VolatileTest {

    public static void main(String[] args) {

        Resource resource = new Resource();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            resource.setStatus(true);
        }, "thread name is").start();

        while(!resource.getStatus()){

        }
        System.out.println("主线程结束");

    }


    static class Resource{

        volatile  private boolean status;

        public void setStatus(boolean status){
            this.status = status;
        }

        public boolean getStatus(){
            return status;
        }
    }

}
