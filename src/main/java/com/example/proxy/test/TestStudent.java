package com.example.proxy.test;

import com.example.proxy.entity.Student;
import com.example.proxy.handler.TransactionHandler;
import com.example.proxy.myproxy.ProxyStudent;
import com.example.proxy.service.StudentService;
import com.example.proxy.service.impl.StudentServiceImpl;
import com.example.proxy.strong.DaoTransaction;

import java.lang.reflect.Proxy;

/**
 * @author lst
 * @date 2023年11月29日 9:38
 */
public class TestStudent {
    public static void main(String[] args) {
//        testQuery(1);
        testQueryObject(1);
    }

    private static void testQueryObject(int id) {
        DaoTransaction transaction=new DaoTransaction();
        StudentServiceImpl studentService=new StudentServiceImpl();
        TransactionHandler transactionHandler=new TransactionHandler(studentService,transaction);
        StudentService proxyInstance=(StudentService)Proxy.newProxyInstance(StudentServiceImpl.class.getClassLoader(),StudentServiceImpl.class.getInterfaces(),transactionHandler);
        Student student=proxyInstance.query(id);
        System.out.println("id:"+student.getId()+",name:"+student.getName());
    }

    private static void testQuery(int id) {
        DaoTransaction transaction=new DaoTransaction();
        StudentServiceImpl studentService=new StudentServiceImpl();
        ProxyStudent proxyStudent=new ProxyStudent(studentService,transaction);
        Student student=proxyStudent.query(id);
        System.out.println("id:"+student.getId()+",name:"+student.getName());
    }
}
