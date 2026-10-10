package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;

import java.util.ArrayList;

public class CourseService {

    public void addCourse(ArrayList<Course> courseList, Course course) {
        courseList.add(course);
        System.out.println("Course added successfully: "+course);
    }


    public void listAllCourse(ArrayList<Course> courseList) {

        for(Course course: courseList){
            System.out.println(course);
        }
    }

    public void updateCourseStatus(int courseId, Boolean status, ArrayList<Course> courseList) {

        for(Course course: courseList){
            if(course.getId()==courseId) {
                course.setActive(status);
                Course courseUpdate= new Course(course);
                System.out.println("Course status updated successfully: "+courseUpdate);
                return;
            }
        }
        throw new EntityNotFoundException("Course is not found");

    }
}
