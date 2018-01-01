package com.rexwong.beans;

import java.beans.*;
import java.util.stream.Stream;

/**
 * java beans 内省模式
 */
public class JavaBeansDemo {
    public static void main(String[] args) throws IntrospectionException {

        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);

        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        System.out.println(beanDescriptor);

        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        Stream.of(methodDescriptors).forEach(System.out::println);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        Stream.of(propertyDescriptors).forEach(System.out::println);
    }
}
