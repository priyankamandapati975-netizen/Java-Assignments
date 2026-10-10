package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Status;

import java.util.ArrayList;

public class EnrollmentService {


    public void enrollStudentInCourse(ArrayList<Enrollment> enrollmentList, Enrollment enrollment) {
        enrollmentList.add(enrollment);
        System.out.println("Student enrolled in a course: "+enrollment);
    }


    public void studentAllEnrollments(ArrayList<Enrollment> enrollmentList, int stuId) {

        boolean success= false;
        for(Enrollment enrollment: enrollmentList){
            if(enrollment.getStudentId()==stuId){
                System.out.println(enrollment);
                success= true;
            }
        }
        if(!success){
            throw new EntityNotFoundException("No enrollments for student");
        }
    }

    public void updateEnrollmentStatus(int enrollId, Status status, ArrayList<Enrollment> enrollmentList) {

        if(status==null){
            throw new EntityNotFoundException("Entered enrollment status is incorrect");
        }
        for(Enrollment enrollment: enrollmentList){
            if(enrollment.getId() == enrollId){
                enrollment.setStatus(status);
                Enrollment enrollment1=new Enrollment(enrollment);
                System.out.println(enrollment1);
                return;
            }
        }

        throw new EntityNotFoundException("Enrollment Id not found");
    }
}
