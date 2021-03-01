package com.example.reflectdemo;

public class Person extends Human {
    // 私有成员变量
    private String username = "默认名字";

    private int userAge = 18;

    public String IdCardNum;

    // 无参构造方法
    public Person(){
        System.out.println("Person的public无参构造器");
    }

    // 公有有参构造方法
    public Person(String idCardNum) {
        IdCardNum = idCardNum;
    }

    // 私有有参构造方法
    private Person(String username, String idCardNum) {
        this.username = username;
        IdCardNum = idCardNum;
    }

    // 私有方法
    private String privateString(String str){
        System.out.println("priavte Hello:"+str);
        return str;
    }

    // 公有方法
    public String publicString(String str){
        System.out.println("public Hello:"+str);

        return str;
    }

    // 公有内部类
    public class publicInnerClass{
        private String property = "priavte property";
        private void getProperty(){
            System.out.println("public内部类的财产是："+property);
        }
    }

    // 私有内部类
    private class privateInnerClass{
        private void getUsername(){
            System.out.println("private内部类私有姓名："+username);
        }
    }
}
