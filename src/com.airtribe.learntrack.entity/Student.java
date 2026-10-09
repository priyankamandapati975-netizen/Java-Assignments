package com.airtribe.learntrack.entity;

public class Student extends Person{

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String batch;
    private boolean active;

    public Student(int id, String firstName, String lastName, String email, String batch, boolean active) {
        super(id,firstName,lastName,email);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.batch = batch;
        this.active = active;
    }

    public Student(int id, String firstName, String lastName, String batch, boolean active) {
        super(id,firstName,lastName);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.batch = batch;
        this.active = active;
    }

    public Student() {
        super();
    }
    public Student(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.batch = student.getBatch();
        this.active = student.isActive();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", batch='" + batch + '\'' +
                ", active=" + active +
                '}';
    }

    @Override
    public String getDisplayName() {
        return this.firstName+" "+this.lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
