package com.zhoubin.decorator;

import com.zhoubin.service.Command;

public class TimerDecorator implements Command {

    Command comand;

    public  TimerDecorator(Command command){
        this.comand = command;
    }

    public void excute() {
        System.out.println("计时开始");
        long start = System.currentTimeMillis();
        comand.excute();
        long end = System.currentTimeMillis();
        System.out.println(start - end +"ms");

    }
}
