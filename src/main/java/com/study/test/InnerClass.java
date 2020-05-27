package com.study.test;

/**
 * @Classname InnerClass
 * @Description TODO
 * @Date 2020/5/26 15:45
 * @Created by zhoubin
 */
public class InnerClass extends Parent {

    public static void main(String[] args) throws ClassNotFoundException {
        InnerClass in = new InnerClass();
        A a = in.new A();
        a.t();
        ClassLoader classLoader = in.getClass().getClassLoader();
        System.out.println("classLoader = " + classLoader);
        ClassLoader parent = classLoader.getParent();
        System.out.println("parent = " + parent);
        ClassLoader parentParent = parent.getParent();
        System.out.println("parentParent = " + parentParent);
        Class<?> aClass = parent.loadClass("java.lang.String");
        System.out.println("aClass = " + aClass);

        Thread t = new Thread(() ->{

        });
    }

    class Inner {
        public void say() {
            System.out.println("inner");
        }
    }

    class A extends Inner {
        public void t() {
            say();
        }
    }
}

class Parent {
    public void say() {
        System.out.println("parent");
    }
}