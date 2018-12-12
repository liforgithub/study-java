package com.lxy.designmodal.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface House2 {
    void maifang();
}

class Buyer2 implements House2 {

    @Override
    public void maifang() {
        System.out.println("我买房");
    }
}

class Proxy2 implements InvocationHandler {

    private Object obj;
    Proxy2(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start");
        Object invoke = method.invoke(obj, args);
        System.out.println("end");

        return invoke;
    }
}

public class JdkProxy {

    public static void main(String[] args) {
        Buyer2 buyer2 = new Buyer2();
        Proxy2 proxy2 = new Proxy2(buyer2);
        House2 house = (House2) Proxy.newProxyInstance(buyer2.getClass().getClassLoader(), buyer2.getClass().getInterfaces(), proxy2);
        house.maifang();
    }
}
