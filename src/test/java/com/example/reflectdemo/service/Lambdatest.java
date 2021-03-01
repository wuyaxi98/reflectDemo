package com.example.reflectdemo.service;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.IntStream;

public class Lambdatest {
    @Test
    public void CollectionTest() {
       /* Collection books = new HashSet();
        books.add("123");0
        books.add("1234");
        books.add("12345");
        books.forEach(obj -> System.out.println("元素："+obj));
        //Iterable it= (Iterable) books.iterator();
        //it.forEach(obj->System.out.println("ite元素"+obj));
        books.removeIf(ele ->((String)ele).length()<4);
        System.out.println(books);*/
        IntStream is = IntStream.builder().add(20).add(13).add(-2).add(18).build();
        /*System.out.println("最大值：" + is.max().getAsInt());*/
      /*  System.out.println("平方》20" + is.allMatch(ele -> ele * ele > 20));*/
        IntStream newIs = is.map(ele ->ele*2+1);
        /*newIs.forEach(System.out::println);*/



    }


}
