package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    public void addStudent(List<Student> studentList, Student student){
        studentList.add(student);
        System.out.println("Student added successfully: "+student);
    }

    public void listStudents(List<Student> studentList){

        for(Student student: studentList){
            System.out.println(student);
        }
    }

    public void listStudents(List<Student> studentList,int studentId){

        for(Student student: studentList) {
            if (student.getId() == studentId) {
                System.out.println(student);
                return;
            }
        }
    }

    public void getStudentById(int studentId,List<Student> studentList) {

        for(Student student: studentList) {
            if (student.getId() == studentId) {
                System.out.println(student);
                return;
            }
        }
        throw new EntityNotFoundException("Student is not found");
    }

    public void updateStudentStatus(
            int studentId,
            boolean active,
            ArrayList<Student> studentList) {

        for (Student student : studentList) {
            if (student.getId() == studentId) {
                student.setActive(active);

                Student studentUpdate = new Student(student);
                System.out.println("Student status updated Successfully: "+studentUpdate);
                return;
            }
        }

        throw new EntityNotFoundException("Student is not found");
    }
}
