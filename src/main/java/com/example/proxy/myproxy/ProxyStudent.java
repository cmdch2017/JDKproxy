package com.example.proxy.myproxy;

import com.example.proxy.entity.Student;
import com.example.proxy.service.StudentService;
import com.example.proxy.service.impl.StudentServiceImpl;
import com.example.proxy.strong.DaoTransaction;

/**
 * @author lst
 * @date 2023年11月29日 9:35
 */
public class ProxyStudent implements StudentService {
    private StudentServiceImpl studentService;
    private DaoTransaction daoTransaction;

    public ProxyStudent(StudentServiceImpl studentService, DaoTransaction daoTransaction) {
        this.studentService = studentService;
        this.daoTransaction = daoTransaction;
    }

    @Override
    public Student query(int id) {
        daoTransaction.startTransaction();
        Student student=studentService.query(id);
        daoTransaction.endTransaction();
        return student;
    }
}
