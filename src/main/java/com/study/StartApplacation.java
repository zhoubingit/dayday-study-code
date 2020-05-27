package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class StartApplacation {
    public static void main(String[] args) {
        SpringApplication.run(StartApplacation.class, args);
    }

    @RequestMapping("test")
    public void test(){

    }
}

class A {
    static {
        B b = new B();
        b.t();
        B.t();
    }
}

class B {
    public static void t(){

    }
}
