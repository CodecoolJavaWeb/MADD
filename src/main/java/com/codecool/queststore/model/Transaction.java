package com.codecool.queststore.model;

public class Transaction {

    private String artifact_Name;
    private String category;
    private String description;

    public Transaction(String artifact_Name, String category, String description) {

        this.artifact_Name = artifact_Name;
        this.category = category;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArtifact_Name() {
        return artifact_Name;
    }

    public void setArtifact_Name(String artifact_Name) {
        this.artifact_Name = artifact_Name;
    }
}
