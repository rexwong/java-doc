package com.rexwong.beans;

import com.rexwong.beans.domain.SonUser;
import com.rexwong.beans.domain.User;

import java.beans.*;
import java.util.stream.Stream;

/**
 * java beans 内省模式
 */
public class JavaBeansDemo {
    public static void main(String[] args) throws IntrospectionException {
//        userBeanDescriptor();
        sunBeanDescritptor();

    }
    private static void sunBeanDescritptor() throws IntrospectionException{
        BeanInfo beanInfo = Introspector.getBeanInfo(SonUser.class,Object.class);
        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        System.out.println(beanDescriptor);

        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        Stream.of(methodDescriptors).forEach(System.out::println);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        Stream.of(propertyDescriptors).forEach(System.out::println);
    }
    private static void userBeanDescriptor() throws IntrospectionException {
        //多例---》软引用、弱引用成员方便GC
        //多例---》每次获得都内省一次
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);

        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        System.out.println(beanDescriptor);

        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        Stream.of(methodDescriptors).forEach(System.out::println);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        Stream.of(propertyDescriptors).forEach(System.out::println);
    }
}
