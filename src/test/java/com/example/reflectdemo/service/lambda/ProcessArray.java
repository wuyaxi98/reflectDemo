package com.example.reflectdemo.service.lambda;


import com.example.reflectdemo.service.Command;

public class ProcessArray {
    public void process(int []target, Command cmd){
        cmd.process(target);
    }
}
