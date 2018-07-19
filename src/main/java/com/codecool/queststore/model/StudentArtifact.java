package com.codecool.queststore.model;

public class StudentArtifact {
    private int idArtifact;
    private int idStudent;
    private int quanity;

    public StudentArtifact(int idArtifact, int idStudent, int quanity) {
        this.idArtifact = idArtifact;
        this.idStudent = idStudent;
        this.quanity = quanity;
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

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }
}
