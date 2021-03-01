package com.example.reflectdemo;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Objects;

public class DemoMethodTest {
    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * 获取字节码有三种方式：类.class/对象.getClass()/Class.forName()
         */
        Class<?> cl = Class.forName("demo.otherdemo.reflect.Person");
        /**
         * 演示：分析方法
         */
        // 获取该类中所有申明的方法,包括私有方法
        Method[] declaredMethods = cl.getDeclaredMethods();
        if (Objects.nonNull(declaredMethods)) {
            for (Method method : declaredMethods) {
                printConstructor(method);
            }
        }
        System.out.println("--------------------公有方法--------------------------");
        // 获取所有的公有方法，包括获取父类的
        Method[] methods = cl.getMethods();
        if (Objects.nonNull(methods)) {
            for (Method method : methods) {
                printConstructor(method);
            }
        }
    }

    public static void printConstructor(Method method) {
        //


        Parameter[] parameters = method.getParameters();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for (int i = 0; i < parameters.length; i++) {
            if (i == parameters.length - 1) {
                // 获取参数类型和参数名字
                stringBuilder.append(parameters[i].getType() + " " + parameters[i].getName());
            } else {
                stringBuilder.append(parameters[i].getType() + " " + parameters[i].getName() + ",");
            }
        }
        stringBuilder.append(")");
        // 获取修饰语
        String modifier = Modifier.toString(method.getModifiers());
        System.out.println(modifier + " " + method.getReturnType() + " " + method.getName() + stringBuilder.toString());

        // 获取抛出的异常类型
        method.getExceptionTypes();
        // 判断是否被声明为采用可变数量的参数，是则为true
        method.isVarArgs();
        // 获取参数的数量
        method.getParameterCount();
        // 获取反参类型
        method.getReturnType();
    }

}
