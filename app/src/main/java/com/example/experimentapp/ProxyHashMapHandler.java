package com.example.experimentapp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHashMapHandler implements InvocationHandler {

    private Object object;

    public ProxyHashMapHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return null;
    }
}
