package com.example.proxy.inteceptor;

import com.example.proxy.service.StudentService;
import com.example.proxy.strong.DaoTransaction;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lst
 * @date 2023年11月29日 10:50
 */
public class CglibInterceptor implements MethodInterceptor {
    DaoTransaction transaction;

    public CglibInterceptor(DaoTransaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        transaction.startTransaction();
        Object o = proxy.invokeSuper(obj, args);
        transaction.endTransaction();
        return o;
    }
}
