package com.example.reflectdemo.service;

import org.assertj.core.util.Lists;

import javax.jws.soap.SOAPBinding;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamDemoTest {
    public static void main(String[] args) {
        List<User> users = Lists.newArrayList();
        users.add(new User(15, "A"));
        users.add(new User(25, "B"));
        users.add(new User(21, "C"));
        /*users.stream()
                .sorted(Comparator.comparing(User::getAge).reversed())
                .limit(1)
                .forEach(user -> System.out.println(user.getName()));*/
        /*users.stream()
                .sorted(Comparator.comparing(User::getAge).reversed())
                .skip(users.size() - 1)
                .forEach(user -> System.out.println(user.getName()));*/
      /*  users.stream()
            .map(user -> "欢迎:"+user.getName())
            .forEach(System.out::println);*/
        List<String> nameList = users.stream().map(User::getName).collect(Collectors.toList());
        Set<String> nameSet = users.stream().map(User::getName).collect(Collectors.toSet());
        Map<String ,User> userMap = users.stream()
                .collect(Collectors.toMap(User::getName, Function.identity(),(k1,k2)->k2));
    }
}


class User {
    int age;
    String name;
    List<String> money;

    public User(int age, String name) {
        this.age = age;
        this.name = name;

    }

    public User(int age, String name, List<String> money) {
        this.age = age;
        this.name = name;
        this.money = money;
    }

    public List<String> getMoney() {
        return money;
    }

    public void setMoney(List<String> money) {
        this.money = money;
    }

    public User() {

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}