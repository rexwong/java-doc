package com.rexwong.java8;


public class FunctionalInterfaceImpl implements FunctionalDemo{

    @Override
    public String functionalMethod(String arg) {
        System.out.println("functional method");
        return "args= "+arg;
    }
}
