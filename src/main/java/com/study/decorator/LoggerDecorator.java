package com.study.decorator;

import com.study.service.Command;

public class LoggerDecorator implements Command {

    Command command;

    public LoggerDecorator(Command command){
        this.command = command;
    }

    public void excute() {
        System.out.println("进入方法");
        command.excute();
        System.out.println("方法结束");
    }
}
