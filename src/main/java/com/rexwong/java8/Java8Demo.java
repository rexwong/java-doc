package com.rexwong.java8;

public class Java8Demo {
    public static void main(String[] args) {
        FunctionalDemo demo = new FunctionalInterfaceImpl(){
            @Override
            public String functionalMethod(String arg) {
                System.out.println("====functional method");
                return "args= "+arg;
            }
        };
        FunctionalDemo demo2 = (arg -> {
            System.out.println("demo2  functional method");
            return "args= "+arg;
        });
        System.out.println(demo2.functionalMethod("rex test"));

        FunctionalDemo demo3 = (a)->"aa "+a;
        System.out.println(demo3.functionalMethod("rex test"));

        FunctionalDemo demo4 = String :: valueOf ;
        System.out.println(demo4.functionalMethod("rex test"));

    }
}
