package com.example.reflectdemo.service.lambda;

public class CommandTest {
    public static void main(String[] args) {
        ProcessArray pa = new ProcessArray();
        int[] array = {3, -4, 6, 4};
        pa.process(array, (int[] target) -> {
            int sum = 0;
            for (int tmp : target) {
                sum += tmp;
            }
            System.out.println("数据元素的总和是：" + sum);
        });
    }
}
