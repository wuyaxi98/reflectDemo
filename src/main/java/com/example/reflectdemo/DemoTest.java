package com.example.reflectdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Objects;

public class DemoTest {
    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * 获取字节码有三种方式：类.class/对象.getClass()/Class.forName()
         */
        Class<?> cl = Class.forName("com.example.reflectdemo.Person");
        /**
         * 演示：分析构造方法
         */
        // 获取该类中所有申明的构造方法,包括私有方法
        Constructor<?>[] declaredConstructors = cl.getDeclaredConstructors();
        if(Objects.nonNull(declaredConstructors)){
            for(Constructor con : declaredConstructors){
                printConstructor(con);
            }
        }
        System.out.println("--------------------公有构造器--------------------------");
        // 获取所有的公有构造方法，构造方法不会获取父类的
        Constructor<?>[] constructors = cl.getConstructors();
        if(Objects.nonNull(constructors)){
            for(Constructor con : constructors){
                printConstructor(con);
            }
        }
        System.out.println("--------------------父类构造器--------------------------");
        // 获取父类的构造器，需要先获取父类的字节码对象
        Class<?> superclass = cl.getSuperclass();
        Constructor<?>[] supConstructors = superclass.getConstructors();
        if(Objects.nonNull(supConstructors)){
            for(Constructor con : supConstructors){
                printConstructor(con);
            }
        }

     }

    public static void printConstructor(Constructor con){
        // 获取所有的参数
        Parameter[] parameters = con.getParameters();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for(int i =0;i<parameters.length;i++){
            if(i == parameters.length-1){
                // 获取参数类型和参数名字
                stringBuilder.append(parameters[i].getType()+" "+parameters[i].getName());
            }else{
                stringBuilder.append(parameters[i].getType()+" "+parameters[i].getName()+",");
            }
        }
        stringBuilder.append(")");
        // 获取修饰语
        String modifier = Modifier.toString(con.getModifiers());
        System.out.println(modifier+" "+con.getName()+stringBuilder.toString());
    }
}
