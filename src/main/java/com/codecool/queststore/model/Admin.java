package com.codecool.queststore.model;

public class Admin extends User {

    public Admin(Integer adminId, String firstName, String lastName, Integer phoneNumber, String email) {
        super(adminId, firstName, lastName, phoneNumber, email);
        Role role = Role.ADMIN;
    }


}
