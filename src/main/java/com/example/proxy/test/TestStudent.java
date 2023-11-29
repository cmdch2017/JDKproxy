package com.example.proxy.test;

import com.example.proxy.entity.Student;
import com.example.proxy.handler.TransactionHandler;
import com.example.proxy.inteceptor.CglibInterceptor;
import com.example.proxy.myproxy.ProxyStudent;
import com.example.proxy.service.StudentService;
import com.example.proxy.service.impl.StudentServiceImpl;
import com.example.proxy.strong.DaoTransaction;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * @author lst
 * @date 2023年11月29日 9:38
 */
public class TestStudent {
    public static void main(String[] args) {
//        testStaticProxyQuery(1);
//        testJDKQueryObject(1);
        testCglibTransaction(1);
    }

    private static void testCglibTransaction(int id) {
        CglibInterceptor interceptor = new CglibInterceptor(new DaoTransaction());
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(StudentServiceImpl.class);
        enhancer.setCallback(interceptor);
        StudentService service = (StudentService) enhancer.create();
        Student student=service.query(id);
        System.out.println("id:" + student.getId() + ",name:" + student.getName());
    }

    private static void testJDKQueryObject(int id) {
        DaoTransaction transaction = new DaoTransaction();
        StudentServiceImpl studentService = new StudentServiceImpl();
        TransactionHandler transactionHandler = new TransactionHandler(studentService, transaction);
        StudentService proxyInstance = (StudentService) Proxy.newProxyInstance(StudentServiceImpl.class.getClassLoader(), StudentServiceImpl.class.getInterfaces(), transactionHandler);
        Student student = proxyInstance.query(id);
        System.out.println("id:" + student.getId() + ",name:" + student.getName());
    }

    private static void testStaticProxyQuery(int id) {
        DaoTransaction transaction = new DaoTransaction();
        StudentServiceImpl studentService = new StudentServiceImpl();
        ProxyStudent proxyStudent = new ProxyStudent(studentService, transaction);
        Student student = proxyStudent.query(id);
        System.out.println("id:" + student.getId() + ",name:" + student.getName());
    }
}
