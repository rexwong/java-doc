package com.rexwong.jvm;

import java.io.File;

public class HelloworldDemo {

    //常量、静态变量--方法区
    private final int i = 0;
    private static int k = 0;

    //成员变量--堆
    private Object obj = new Object();
    private int sss=0;

    /*//运行时多态
    @Autowired
    private Service service;//符号  字面量
    ServiceA;//实现类A
    ServiceB;//实现类B*/

    //局部变量--栈帧
    public void methedOne(int i) {

        /*Serivce.do();//ServiceA */

        int j = 0;

        int sum = i + j;

        Object abc = obj;

        long start = System.currentTimeMillis();

        //TODO do something

        return;
        //正常返回
        //异常返回 Exception
    }

    public void methedTwo(int arg) {

        File file = new File("path");

        methedOne(arg);
    }

    public void methodThree(){
        methodThree();
    }

    public static void main(String[] args) {


    }
}
