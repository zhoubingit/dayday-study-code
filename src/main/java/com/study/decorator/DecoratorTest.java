package com.study.decorator;

import com.study.decorator.LoggerDecorator;
import com.study.decorator.TimerDecorator;
import com.study.service.CatServiceImpl;
import com.study.service.Command;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 装饰者模式
 */
public class DecoratorTest {

    /**
     * 装饰者模式
     * 从外层逐一调用内层方法 类似AOP切面功能
     * 优点:不用在类内部写过多的冗余代码,逐层嵌套就实现了动态处理的功能,从外层执行到内层
     * 缺点: 需要实现一个不相关的Commond类,创建了过多的对象,占用内存
     * @param args
     */
    public static void main(String[] args) {
        Command cmd  = new TimerDecorator(new LoggerDecorator(new CatServiceImpl()));
        cmd.excute();
        cmd  = new LoggerDecorator(new TimerDecorator(new CatServiceImpl()));
        cmd.excute();
    }
}
