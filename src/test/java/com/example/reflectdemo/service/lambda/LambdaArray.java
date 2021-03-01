package com.example.reflectdemo.service.lambda;

import sun.security.krb5.internal.crypto.Aes128;

import java.util.Arrays;

public class LambdaArray {
    public static void main(String []args){
        String[] arr1 = new String[]{"java","fkava","fkit","ios","android"};
        Arrays.parallelSort(arr1,(o1,o2)->o1.length()-o2.length());
        System.out.println(Arrays.toString(arr1));
        int[] arr2=new int[]{3,-4,25,16,30,18};
        Arrays.parallelPrefix(arr2,((left, right) -> left*right));
        System.out.println(Arrays.toString(arr2));
        long[] arr3 = new long[5];
        Arrays.parallelSetAll(arr3,operand ->operand*5);
        System.out.println(Arrays.toString(arr3));
    }
}
