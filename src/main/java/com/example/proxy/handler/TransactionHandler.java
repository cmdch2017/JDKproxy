package com.example.proxy.handler;

import com.example.proxy.strong.DaoTransaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lst
 * @date 2023年11月29日 9:45
 */
public class TransactionHandler implements InvocationHandler {
    private DaoTransaction daoTransaction;
    private Object object;

    public TransactionHandler(Object object, DaoTransaction daoTransaction) {
        this.object = object;
        this.daoTransaction = daoTransaction;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(object,args);
    }
}
