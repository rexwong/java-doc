package com.rexwong.beans;

import com.rexwong.beans.domain.SonUser;
import com.rexwong.beans.domain.User;

import java.beans.*;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * java beans 内省模式
 *
 * 结合Spring beans学习
 */
public class JavaBeansDemo {
    public static void main(String[] args) throws IntrospectionException,ClassNotFoundException {
        userBeanDescriptor();
//        sunBeanDescritptor();

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

    private static void userBeanDescriptor() throws IntrospectionException,ClassNotFoundException {
        Class<?> targetClass = Class.forName("com.rexwong.beans.domain.User");
        //多例---》软引用、弱引用成员方便GC
        //多例---》每次获得都内省一次
        BeanInfo beanInfo = Introspector.getBeanInfo(targetClass);

        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();

        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();

        User user = new User();
        Stream.of(propertyDescriptors).forEach(propertyDescriptor -> {
            //类(Class)--无状态
            //实例(bean)--有状态
            String propertyName = propertyDescriptor.getName();
            if("id".equals(propertyName)){
                propertyDescriptor.setPropertyEditorClass(IdProptyEditor.class);
                PropertyEditor propertyEditor = propertyDescriptor.createPropertyEditor(user);
                propertyEditor.addPropertyChangeListener( event-> {
                    //添加事件监听,不然值是不能添加
                    PropertyEditor source = (PropertyEditor)event.getSource();
                    Method setIdMethod = propertyDescriptor.getWriteMethod();
                    try {
                        setIdMethod.invoke(user,source.getValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                //触发事件，通知事件源 propertyEditor
                propertyEditor.setAsText("22");
                System.out.println(user);
            }

        });
    }
}
