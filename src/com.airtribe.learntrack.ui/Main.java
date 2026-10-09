package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Status;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        boolean active= false;
        do{
            System.out.println("Management List: ");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
        }while(active);
        System.out.print("Select the management option: ");
        Scanner inputScanner=new Scanner(System.in);
        int option= inputScanner.nextInt();
        switch(option) {
            case 1:
                StudentService studentService = new StudentService();
                ArrayList<Student> studentList = new ArrayList<>(
                        Arrays.asList(new Student(122, "Priyanka", "Mandapati", "priyankamandapati975@gmail.com", "C23", true),
                                new Student(123, "Renuka", "Mandapati", "Renukamandapati975@gmail.com", "C23", true),
                                new Student(124, "Gokul", "Gumma", "gokulgumma123@gmail.com", "C23", true)));
                do{
                    System.out.println("==================================================");
                    System.out.println("1. Add new student: ");
                    System.out.println("2. View all students");
                    System.out.println("3. Search student by ID");
                    System.out.println("4. Activate/Deactivate a student");
                }while(active);

                System.out.print("Select option in student management: ");
                int studentOption= inputScanner.nextInt();
                switch(studentOption){
                    case 1:
                        Student addStudent = new Student(125, "Rajamma", "Mandapati", "rajammaMandapati190@gmail.com", "C23", true);
                        studentService.addStudent(studentList, addStudent);
                        break;
                    case 2:
                        studentService.listStudents(studentList);
                        break;
                    case 3:
                        System.out.print("Enter student id: ");
                        int studentId= inputScanner.nextInt();
                        try {
                            studentService.getStudentById(studentId, studentList);
                        }catch(EntityNotFoundException ex){
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case 4:
                        System.out.print("Enter student id and active status: ");
                        int stuId= inputScanner.nextInt();
                        inputScanner.nextLine();
                        String activeStatus= inputScanner.nextLine().trim();
                        try {
                            studentService.updateStudentStatus(stuId, Boolean.valueOf(activeStatus), studentList);
                        }catch(EntityNotFoundException ex){
                            System.out.println(ex.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Option not found");
                        break;

                }
                break;
            case 2:
                CourseService courseService=new CourseService();
                ArrayList<Course> courseList=new ArrayList<>(
                        Arrays.asList(new Course(23,"Java","Java Programming Language",36,true),
                            new Course(24,"Python","Python Programming Language",40,true)));
                do{
                    System.out.println("==================================================");
                    System.out.println("1. Add new course: ");
                    System.out.println("2. View all courses");
                    System.out.println("3. Activate/Deactivate a course");
                }while(active);
                System.out.print("Select option in course management: ");
                int courseOption= inputScanner.nextInt();
                switch(courseOption){
                    case 1:
                        Course course= new Course(24,"AI","Agentic AI",40,true);
                        courseService.addCourse(courseList, course);
                        break;
                    case 2:
                        courseService.listAllCourse(courseList);
                    case 3:
                        System.out.print("Enter courseId and course active status: ");
                        int courseId= inputScanner.nextInt();
                        inputScanner.nextLine();
                        String activeStatus= inputScanner.nextLine().trim();
                        try {
                            courseService.updateCourseStatus(courseId, Boolean.valueOf(activeStatus), courseList);
                        }catch(EntityNotFoundException ex){
                            System.out.println(ex.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Option not found");
                        break;

                }
                break;
            case 3:

                EnrollmentService enrollmentService=new EnrollmentService();
                ArrayList<Enrollment> enrollmentList=new ArrayList<>(
                        Arrays.asList(new Enrollment(1, 122,23,new Date(), Status.ACTIVE),
                                new Enrollment(2,122,24,new Date(),Status.CANCELLED)));
                do{
                    System.out.println("==================================================");
                    System.out.println("1. Enroll a student in a course: ");
                    System.out.println("2. View enrollments for a student");
                    System.out.println("3. Mark enrollment as completed/cancelled");
                }while(active);
                System.out.print("Select option in enrollment management: ");
                int enrollmentOption= inputScanner.nextInt();
                switch(enrollmentOption) {
                    case 1:
                        Enrollment enrollment = new Enrollment(3, 123, 24, new Date(), Status.ACTIVE);
                        enrollmentService.enrollStudentInCourse(enrollmentList, enrollment);
                        break;
                    case 2:
                        System.out.print("Enter student id: ");
                        int stuId = inputScanner.nextInt();
                        try {
                            enrollmentService.studentAllEnrollments(enrollmentList, stuId);
                        } catch (EntityNotFoundException ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case 3:
                        System.out.print("Enter enrollment id and enrollment status: ");
                        int enrollId = inputScanner.nextInt();
                        inputScanner.nextLine();
                        String activeStatus = inputScanner.nextLine().trim();
                        Status status = activeStatus.equalsIgnoreCase("Completed") ? Status.COMPLETED :
                                (activeStatus.equalsIgnoreCase("Cancelled") ? Status.CANCELLED : null);
                        try {
                            enrollmentService.updateEnrollmentStatus(enrollId, status, enrollmentList);
                        } catch (EntityNotFoundException ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Option not found");
                        break;
                }
                break;
            default:
                System.out.println("Option not found");
                break;

        }

    }

}
