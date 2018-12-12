package com.lxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

interface House {
    void maifang();
}

class Buyer implements House {

    public void maifang() {
        System.out.println("我买房");
    }
}

class Proxy implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("start");
        Object invokeSuper = methodProxy.invokeSuper(o, objects);
        System.out.println("end");
        return invokeSuper;
    }
}

public class CglibProxy {

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Buyer.class);
        enhancer.setCallback(proxy);

        House house = (House) enhancer.create();
        house.maifang();
    }
}
