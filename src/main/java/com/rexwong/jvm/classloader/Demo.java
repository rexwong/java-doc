package com.rexwong.jvm.classloader;

public class Demo {
    public static int tmp=1;//step1  tmp=0
    static {
        tmp=2;//step2 tmp=1
        System.out.println(tmp);
    }

    public static void main(String[] args) {
        tmp=3;
        System.out.println(tmp);
    }
}
