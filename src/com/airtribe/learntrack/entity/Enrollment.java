package com.airtribe.learntrack.entity;

import java.util.Date;

public class Enrollment {

    private int id;
    private int studentId;
    private int courseId;
    private Date enrollmentDate;
    private Status status;

    public Enrollment(int id, int studentId, int courseId, Date enrollmentDate, Status status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", enrollmentDate=" + enrollmentDate +
                ", status=" + status +
                '}';
    }

    public Enrollment(Enrollment enrollment) {
        this.id = enrollment.getId();
        this.studentId = enrollment.getStudentId();
        this.courseId = enrollment.getCourseId();
        this.enrollmentDate = enrollment.getEnrollmentDate();
        this.status = enrollment.getStatus();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
