package com.example.reflectdemo.service;


import com.example.reflectdemo.Common.Constants;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Objects;

@Service
public class DemoBeanFactory {
    private Logger log = org.slf4j.LoggerFactory.getLogger(DemoBeanFactory.class);

    public void createClass(String className) throws ClassNotFoundException, IOException {
        String[] nameArray = className.split("\\.");
        log.info("========");
        String fatherClassName = nameArray[nameArray.length - 1];
        String pathName = fatherClassName + "Impl.java";
        String ImplclassName = pathName.replaceAll("\\.java", "");
        log.info("");
        File file = new File(pathName);
        String fileName = file.getName();
        BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));

        Class<?> cl = Class.forName(className);
        //生成类文件信息
        StringBuilder classMessage = new StringBuilder();
        classMessage.append("public class " + ImplclassName + " " + "extends " + fatherClassName + "{\r\n");
        classMessage.append("private Logger log = org.slf4j.LoggerFactory.getLogger(" + ImplclassName + ".classs);\r\n");
        //生成构造方法
        String constructor = printConstructor(cl, ImplclassName, fatherClassName);
        classMessage.append(constructor);
        //生成共有方法的代码
        Method[] methods = cl.getDeclaredMethods();
        if (Objects.nonNull(methods)) {
            for (Method method : methods) {

                String modifier = Modifier.toString(method.getModifiers());
                if (modifier.equals("public")) {
                    String result = printMethod(method);
                    classMessage.append(result);
                }
            }
        }
        classMessage.append("\r\n}");
        System.out.println(classMessage.toString());
        outWriter.write(classMessage.toString());
        outWriter.close();
    }

    //打印构造器
    public String printConstructor(Class<?> cl, String ImpImplclassNamel, String fatherClassName) throws IOException {
        Constructor<?>[] constructors = cl.getConstructors();
        StringBuilder stringBuilder = new StringBuilder();
        if (Objects.nonNull(constructors)) {
            for (Constructor con : constructors) {
                // 获取所有的参数
                String modifier = Modifier.toString(con.getModifiers());
                Parameter[] parameters = con.getParameters();

                stringBuilder.append("\r\n" + modifier + " " + " void " + " " + ImpImplclassNamel + "(");
                StringBuilder bodyBuilder = new StringBuilder();
                bodyBuilder.append("{" + "\r\n" + Constants.FOUR_BLANK);
                bodyBuilder.append("log.info(\"自动生成代码的日志增强\");" + "\r\n" + Constants.FOUR_BLANK + "super."
                        + fatherClassName + "(");
                if (null == parameters || parameters.length == 0) {
                    stringBuilder.append(");");
                    System.out.println(stringBuilder.toString());
                    continue;
                } else {
                    for (int i = 0; i < parameters.length; i++) {
                        String parameterName = null;
                        if (i == parameters.length - 1) {
                            // 获取参数类型和参数名字
                            parameterName = parameters[i].getName();
                            stringBuilder.append(parameters[i].getType().toString().replaceAll("class", "")
                                    + " " + parameterName + ")");
                            bodyBuilder.append(parameterName + ");" + "\r\n" + "}");
                        } else {
                            stringBuilder.append(parameters[i].getType().toString().replaceAll("class", "")
                                    + " " + parameterName + ",");
                            bodyBuilder.append(parameterName + ",");
                        }
                    }
                }
                System.out.println(stringBuilder.toString() + bodyBuilder.toString());
                stringBuilder.append(bodyBuilder.toString());
            }

        }
        return stringBuilder.toString();

    }

    public String printMethod(Method method) throws IOException {
        Parameter[] parameters = method.getParameters();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("{" + "\r\n" + Constants.FOUR_BLANK);
        bodyBuilder.append("log.info(\"自动生成代码的日志增强\");" + "\r\n" + Constants.FOUR_BLANK + "super." + method.getName() + "(");
        // 获取修饰语
        String modifier = Modifier.toString(method.getModifiers());
        stringBuilder.append("\r\n" + modifier + " " + method.getReturnType() + " " + method.getName() + "(");
        for (int i = 0; i < parameters.length; i++) {
            String parameterName = null;
            if (i == parameters.length - 1) {
                parameterName = parameters[i].getName();
                // 获取参数类型和参数名字
                stringBuilder.append(parameters[i].getType().toString().replaceAll("class", "") + " " + parameterName + ")");
                bodyBuilder.append(parameterName + ");" + "\r\n" + "}");
            } else {
                stringBuilder.append(parameters[i].getType().toString().replaceAll("class", "") + " " + parameterName + ",");
                bodyBuilder.append(parameterName + ",");
            }
        }


        System.out.println(stringBuilder.toString() + bodyBuilder.toString());
        return stringBuilder.toString() + bodyBuilder.toString();

      /*  // 获取抛出的异常类型
        method.getExceptionTypes();
        // 判断是否被声明为采用可变数量的参数，是则为true
        method.isVarArgs();
        // 获取参数的数量
        method.getParameterCount();
        // 获取反参类型
        method.getReturnType();*/
    }
}
