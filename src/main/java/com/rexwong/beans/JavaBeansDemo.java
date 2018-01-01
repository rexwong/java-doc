package com.rexwong.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.util.stream.Stream;

/**
 * java beans 内省模式
 */
public class JavaBeansDemo {
    public static void main(String[] args) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
            MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
            Stream.of(methodDescriptors).forEach(System.out::println);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
}
