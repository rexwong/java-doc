package com.rexwong.jvm.classloader;

public class Demo2 {
    public static void main(String[] args) {
        System.out.println(Demo2.class.getClassLoader());
        //out:sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader classLoader = Demo2.class.getClassLoader();
        while (classLoader!=null){
            System.out.println( classLoader);
            classLoader = classLoader.getParent();
        }
        System.out.println("===="+classLoader);
    }
    //sun.misc.Launcher$AppClassLoader@18b4aac2
    //sun.misc.Launcher$AppClassLoader@18b4aac2
    //sun.misc.Launcher$ExtClassLoader@610455d6
    //null  代表Boostrap ClassLoader
}
