package com.itsc.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LabExamApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        StudentRepo student_repo = context.getBean("stu", StudentRepo.class);
        student_repo.createDBAndTable();
        
        Student student = new Student();
        student.setName("Abel");
        student.setEmail("abelseyoum.swe@gmail.com");
        
        student_repo.insertIntoTable(student);
    }
}