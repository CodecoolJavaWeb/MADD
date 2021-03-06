package com.codecool.queststore.model;

import java.util.ArrayList;
import java.util.List;

public class Mentor extends User {

    private List<CoolClass> classes;
    private String role;

    public Mentor(Integer mentorId, String firstName, String lastName, String phoneNumber, String email, String role) {
        super(mentorId, firstName, lastName, phoneNumber, email, role);
    }

    public Mentor(String firstName, String lastName, String phoneNumber, String email, String role) {
        super(firstName, lastName, phoneNumber, email);
        this.role = role;
        this.classes = new ArrayList<>();
    }

    public List<CoolClass> getClasses() {
        return this.classes;
    }


    public void setClasses(List<CoolClass> mentorsClasses) {
        this.classes = mentorsClasses;
    }


}
