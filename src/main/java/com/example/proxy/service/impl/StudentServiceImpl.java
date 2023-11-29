package com.example.proxy.service.impl;

import com.example.proxy.entity.Student;
import com.example.proxy.service.StudentService;

/**
 * @author lst
 * @date 2023年11月29日 9:30
 */
public class StudentServiceImpl implements StudentService {

    @Override
    public Student query(int id) {
        System.out.println("执行查询");
        Student student=new Student();
        student.setId(id);
        student.setName("lst");
        return student;
    }
}
