package com.airtribe.learntrack.entity;

public class Trainer extends Person{


    public Trainer(int id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email);
    }

    public Trainer(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    @Override
    public String getDisplayName() {
        return this.firstName+" "+this.lastName;
    }
}
