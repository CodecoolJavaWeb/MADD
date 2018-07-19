package com.codecool.queststore.model;

public class Artifact {
    private Integer id_artifact;
    private String artifact_name;
    private Integer price;
    private String category;
    private String description;

    public Artifact(Integer id_artifact, String artifact_name, Integer price, String category, String description) {
        this.id_artifact = id_artifact;
        this.artifact_name = artifact_name;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public Artifact(String artifact_name, String category, String description) {
        this.artifact_name = artifact_name;
        this.category = category;
        this.description = description;
    }

    public Integer getId_artifact() {
        return id_artifact;
    }

    public String getArtifact_name() {
        return artifact_name;
    }

    public void setArtifact_name(String artifact_name) {
        this.artifact_name = artifact_name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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
}
