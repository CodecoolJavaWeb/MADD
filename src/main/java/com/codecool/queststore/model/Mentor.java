package com.codecool.queststore.model;

import java.util.ArrayList;
import java.util.List;

public class Mentor extends User {

    private List<CoolClass> classes;

    public Mentor(Integer mentorId, String firstName, String lastName, String phoneNumber, String email, String role) {
        super(mentorId, firstName, lastName, phoneNumber, email, role);
    }

    public Mentor(String firstName, String lastName, String phoneNumber, String email, String role) {
        super(firstName, lastName, phoneNumber, email, role);
    }

    public List<CoolClass> getClasses() {
        return this.classes;
    }


    public void setClasses(List<CoolClass> mentorsClasses) {
        this.classes = mentorsClasses;
    }


}
