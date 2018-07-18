package com.codecool.queststore.model;


import com.codecool.queststore.DAO.StudentDAO;

public class Student extends User {

    private Integer currentMoney;
    private Integer totalMoney;
    private Integer studentId;
    private String studentClass;
    private String role;

    public Student(Integer userId, String firstName, String lastName, String phoneNumber, String email,
                   String role) {
        super(userId, firstName, lastName, phoneNumber, email, role);
        this.currentMoney = 0;
        this.totalMoney = 0;
        this.studentId = new StudentDAO().getStudentId();
        this.studentClass = "";
    }

    public Student(String firstName, String lastName, String phoneNumber, String email, String role, String studentClass, Integer totalMoney) {
        super(firstName, lastName, phoneNumber, email);
        this.role = role;
        this.currentMoney = 0;
        this.totalMoney = totalMoney;
        this.studentClass = studentClass;
    }

    public Student(Integer userId, String firstName, String lastName, String phoneNumber, String email,
                   String role, Integer currentMoney, Integer totalMoney) throws Exception {
        super(userId, firstName, lastName, phoneNumber, email, role);
        this.currentMoney = currentMoney;
        this.totalMoney = totalMoney;
        this.studentId = new StudentDAO().getStudentId();
        this.studentClass = "";
    }

    public Student(Integer userId, String firstName, String lastName, String phoneNumber, String email,
                   String role, Integer currentMoney, Integer totalMoney, String studentClass) throws Exception {
        super(userId, firstName, lastName, phoneNumber, email, role);
        this.currentMoney = currentMoney;
        this.totalMoney = totalMoney;
        this.studentId = new StudentDAO().getStudentId();
    }

    public Integer getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(Integer currentMoney) {
        this.currentMoney = currentMoney;
    }

    public Integer getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Integer totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

}
