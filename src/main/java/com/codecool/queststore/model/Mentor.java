package com.codecool.queststore.model;

import java.util.ArrayList;
import java.util.List;

public class Mentor extends User {

    private List<CoolClass> classes;

    public Mentor(Integer mentorId, String firstName, String lastName, Integer phoneNumber, String email) {
        super(mentorId, firstName, lastName, phoneNumber, email);
        Role role = Role.MENTOR;
        classes = new ArrayList<>();
    }

    public Mentor(String firstName, String lastName, Integer phoneNumber, String email) {
        super(firstName, lastName, phoneNumber, email);
        Role role = Role.MENTOR;
        classes = new ArrayList<>();
    }

    public List<CoolClass> getClasses() {
        return classes;
    }
}
