package com.lxy.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClazzLoadDemo {

    public static void main(String[] args) throws Exception {

        //通过反射获取Person对象
        Class<?> clazzPerson = Class.forName("com.lxy.demo.Person");
        //默认通过无参构造函数生成对象(无参构造函数必须为public)
        Person person = (Person) clazzPerson.newInstance();
        System.out.println(person);

        //通过有参构造函数生成对象
        Constructor<?> constructor = clazzPerson.getConstructor(String.class, Integer.class);
        Person p = (Person) constructor.newInstance("小命", 2);
        System.out.println(p);

        //获取到对象里面的所有属性
        Field[] fields = clazzPerson.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        //获取对象里面的所有方法
        Method[] methods = clazzPerson.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        //给对象里面的私有变量赋值
        Object newInstance = clazzPerson.newInstance();
        Field fieldUserName = clazzPerson.getDeclaredField("userName");
        //允许反射操作私有变量
        fieldUserName.setAccessible(true);
        fieldUserName.set(newInstance, "小红");
        Field fieldAge = clazzPerson.getDeclaredField("age");
        //允许反射操作私有变量
        fieldAge.setAccessible(true);
        fieldAge.set(newInstance, 12);

        Person p2 = (Person) newInstance;
        System.out.println(p2);
    }
}
