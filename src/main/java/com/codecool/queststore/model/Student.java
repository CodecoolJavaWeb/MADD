package com.codecool.queststore.model;


public class Student extends User {

    Wallet wallet;

    public Student(Integer studentId, String firstName, String lastName, Integer phoneNumber, String email) {
        super(studentId, firstName, lastName, phoneNumber, email);
        Role role = Role.STUDENT;
        this.wallet = new Wallet();
    }

    public Student(String firstName, String lastName, Integer phoneNumber, String email) {
        super(firstName, lastName, phoneNumber, email);
        Role role = Role.STUDENT;
        this.wallet = new Wallet();
    }
}
