package com.zhoubin.test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ReadWriteLockTest {


    public static void main(String[] args) {

        Resource resource = new Resource();

        for (int i = 0; i <= 10; i++) {
            final int num = i;
            new Thread(() -> {
                resource.add(String.valueOf(num));
            }, String.valueOf(i)).start();
        }
    }

    static class Resource{
        
        List<String> list = new CopyOnWriteArrayList<>();
        
        public void add(String val){
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName()+"线程进行添加,值为:"+val);
            list.add(val);
            System.out.println(thread.getName()+"线程添加完成");
        }

        public List<? extends Object> get(){
            Thread thread = Thread.currentThread();
            try{
                return list;
            }finally {
                System.out.println(thread.getName()+"线程获取值");
            }
        }
        
    }
}
