package com.example.reflectdemo.service;

import com.example.reflectdemo.Helloworld;
import com.example.reflectdemo.Human;

import org.junit.jupiter.api.Test;
import org.objectweb.asm.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AsmTest extends ClassLoader implements Opcodes {
    @Test
    public void createClasss() {
        Class className = Human.class;
        String AllFatherClassName = className.getName();
        String SonClassName = null;

        String fatherClassName = className.getSimpleName();
        String ImplclassName = fatherClassName + "Impl";
        SonClassName = AllFatherClassName.replaceAll(fatherClassName, ImplclassName);
        SonClassName = SonClassName.replaceAll("\\.", "\\/");
        String SonClassPath = SonClassName.replaceAll("\\/", "\\.");
        AllFatherClassName = AllFatherClassName.replaceAll("\\.", "\\/");
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_7, ACC_PUBLIC, SonClassName, null, AllFatherClassName, null);
        //生成默认的构造方法
        MethodVisitor mw = cw.visitMethod(ACC_PUBLIC,
                "<init>",
                "()V",
                null,
                null);
        //生成构造方法的字节码指令
        mw.visitVarInsn(ALOAD, 0);
        mw.visitMethodInsn(INVOKESPECIAL, AllFatherClassName, "<init>", "()V");
        mw.visitInsn(RETURN);
        mw.visitMaxs(1, 1);
        mw.visitEnd();
        //生成main方法
        mw = cw.visitMethod(ACC_PUBLIC + ACC_STATIC,
                "main",
                "([Ljava/lang/String;)V",
                null,
                null);

        //生成main方法中的字节码指令
        mw.visitFieldInsn(GETSTATIC,
                "java/lang/System",
                "out",
                "Ljava/io/PrintStream;");

        mw.visitLdcInsn("Hello world!");
        mw.visitMethodInsn(INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Ljava/lang/String;)V");
        mw.visitInsn(RETURN);
        mw.visitMaxs(2, 2);

        //字节码生成完成
        mw.visitEnd();

        // 获取生成的class文件对应的二进制流
        byte[] code = cw.toByteArray();


        //将二进制流写到本地磁盘上
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(ImplclassName + ".class");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(code);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //直接将二进制流加载到内存中
        AsmTest loader = new AsmTest();
        Class<?> exampleClass = loader.defineClass(SonClassPath, code, 0, code.length);

        //通过反射调用main方法
        try {
            exampleClass.getMethods()[0].invoke(null, new Object[]{null});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createSonClasss() {
        Class className = Human.class;
        String AllFatherClassName = className.getName();
        String SonClassName = null;


        String fatherClassName = className.getSimpleName();
        String ImplclassName = fatherClassName + "Impl";

        SonClassName = AllFatherClassName.replaceAll(fatherClassName, ImplclassName);
        SonClassName = SonClassName.replaceAll("\\.", "\\/");
        String SonClassPath = SonClassName.replaceAll("\\/", "\\.");
        AllFatherClassName = AllFatherClassName.replaceAll("\\.", "\\/");
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_8, ACC_PUBLIC, SonClassName, null, AllFatherClassName, null);
        //生成默认的构造方法
        MethodVisitor mw = cw.visitMethod(ACC_PUBLIC,
                "<init>",
                "()V",
                null,
                null);
        //生成构造方法的字节码指令
        mw.visitVarInsn(ALOAD, 0);
        mw.visitMethodInsn(INVOKESPECIAL, AllFatherClassName, "<init>", "()V", false);
        mw.visitVarInsn(ALOAD, 0);
        mw.visitLdcInsn(Type.getType(className));
        mw.visitMethodInsn(INVOKESTATIC, "org/slf4j/LoggerFactory", "getLogger", "(L" + "java/lang/Class" + ";)Lorg/slf4j/Logger;", false);
        mw.visitFieldInsn(PUTFIELD, SonClassName,"log","Lorg.slf4j.Logger;");
        mw.visitInsn(RETURN);
        mw.visitMaxs(2, 1);
        mw.visitEnd();
        //生成Filed目前只有日志的
        FieldVisitor fv = cw.visitField(ACC_PRIVATE, "log", "Lorg/slf4j/Logger;", null, null);
        fv.visitEnd();
        //生成增强方法
        mw = cw.visitMethod(ACC_PUBLIC, "publicMethod", "(Ljava/lang/String;)V", null, null);
        //生成公共方法的字节码
        mw.visitVarInsn(ALOAD, 0);
        mw.visitFieldInsn(GETFIELD,SonClassName,"log","Lorg/slf4j/LoggerFactory;");
        mw.visitLdcInsn("增强日志");
        mw.visitMethodInsn(INVOKEINTERFACE,"org/slf4j/Logger","info","(Ljava/lang/String;)V",true);
        mw.visitVarInsn(ALOAD, 0);
        mw.visitVarInsn(ALOAD,1);
        mw.visitMethodInsn(INVOKESPECIAL, AllFatherClassName, "publicMethod", "(Ljava/lang/String;)V",false);
        mw.visitInsn(RETURN);
        mw.visitMaxs(2, 2);
        mw.visitEnd();
        // 获取生成的class文件对应的二进制流
        byte[] code = cw.toByteArray();


        //将二进制流写到本地磁盘上
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(ImplclassName + ".class");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(code);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //直接将二进制流加载到内存中
        AsmTest loader = new AsmTest();
        Class<?> exampleClass = loader.defineClass("com.example.reflectdemo.HumanImpl", code, 0, code.length);


        //通过反射调用main方法
        try {
            Object human = exampleClass.newInstance();
            Method m1 = exampleClass.getDeclaredMethod("publicMethod");
            //exampleClass.getMethods()[0].invoke(null, new Object[]{null});
            m1.invoke(human);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }


}
