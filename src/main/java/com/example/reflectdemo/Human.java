package com.example.reflectdemo;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Human {
    public String sex = "default man";
    private Logger log = org.slf4j.LoggerFactory.getLogger(Human.class);
    public Human(){}

    public Human(String sex){
        this.sex = sex;
    }

    private void priavteMethod(){
        System.out.println("这是Human的private方法");
    }

    public void publicMethod(java.lang.String name){
        System.out.println("这是Human的public方法");
    }

    public class superInnerClass{
        private String property = "super property";
        private void getProperty(){
            System.out.println("super内部类的财产是："+property);
        }
    }
}
