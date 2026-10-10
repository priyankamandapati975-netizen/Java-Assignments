package com.airtribe.learntrack.entity;

public class Person {

    protected int id;
    protected  String firstName;
    protected String lastName;
    protected String email;

    public Person(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {

    }

    public String getDisplayName(){
        return firstName+ " "+lastName;
    }
}
