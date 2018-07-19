package com.codecool.queststore.model;

public class Artifact {
    private int id_artifact;
    private String artifact_name;
    private int price;

    public Artifact(int id_artifact, String artifact_name, int price, String category, String description) {
        this.id_artifact = id_artifact;
        this.artifact_name = artifact_name;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    private String category;

    public int getId_artifact() {
        return id_artifact;
    }

    public void setId_artifact(int id_artifact) {
        this.id_artifact = id_artifact;
    }

    public String getArtifact_name() {
        return artifact_name;
    }

    public void setArtifact_name(String artifact_name) {
        this.artifact_name = artifact_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    private String description;
}
