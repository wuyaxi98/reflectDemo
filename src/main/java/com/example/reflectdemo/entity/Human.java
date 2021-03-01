package com.example.reflectdemo.entity;

public class Human {
    public String sex = "default man";

    public Human(){}

    public Human(String sex){
        this.sex = sex;
    }

    private void priavteMethod(){
        System.out.println("这是Human的private方法");
    }

    public void publicMethod(){
        System.out.println("这是Human的public方法");
    }

    public class superInnerClass{
        private String property = "super property";
        private void getProperty(){
            System.out.println("super内部类的财产是："+property);
        }
    }
}
