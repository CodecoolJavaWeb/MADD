package com.codecool.queststore.model;

public class StudentArtifact {
    private int idArtifact;
    private int idStudent;
    private int quantity;
    private String artifact_name;
    private String description;
    private String category;

    public StudentArtifact(int idArtifact, int idStudent, int quantity, String artifact_name, String description, String category) {
        this.idArtifact = idArtifact;
        this.idStudent = idStudent;
        this.quantity = quantity;
        this.artifact_name = artifact_name;
        this.description = description;
        this.category = category;
    }

//    public StudentArtifact(int idArtifact, int idStudent, int quanity) {
//        this.idArtifact = idArtifact;
//        this.idStudent = idStudent;
//        this.quanity = quanity;
//    }

    public String getArtifact_name() {
        return artifact_name;
    }

    public void setArtifact_name(String artifact_name) {
        this.artifact_name = artifact_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIdArtifact() {
        return idArtifact;
    }

    public void setIdArtifact(int idArtifact) {
        this.idArtifact = idArtifact;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quanity) {
        this.quantity = quanity;
    }
}
