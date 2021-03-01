package com.example.reflectdemo.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;



public class DemoBeanFactoryTest {
    @Autowired
    private DemoBeanFactory demoBeanFactory;
    @Test
    public void createClass() throws ClassNotFoundException, IOException {
        DemoBeanFactory demoBeanFactory = new DemoBeanFactory();
        demoBeanFactory.createClass("com.example.reflectdemo.Human");

    }
}