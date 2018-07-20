package com.codecool.queststore.model;

public class Quest {

    private Integer questId;
    private String questName;
    private String questDescription;
    private Integer questValue;
    private String questCategory;

    public Quest(Integer id, String name, String description, Integer value, String category) {
        this.questId = id;
        this.questName = name;
        this.questDescription = description;
        this.questValue = value;
        this.questCategory = category;
    }

    public Quest(String name, String description, Integer value, String category) {
        this.questName = name;
        this.questDescription = description;
        this.questValue = value;
        this.questCategory = category;
    }

    public Integer getQuestId() {
        return questId;
    }

    public void setQuestId(Integer questId) {
        this.questId = questId;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public void setQuestDescription(String questDescription) {
        this.questDescription = questDescription;
    }

    public String getQuestCategory() {
        return questCategory;
    }

    public void setQuestCategory(String questCategory) {
        this.questCategory = questCategory;
    }

    public Integer getQuestValue() {
        return questValue;
    }

    public void setQuestValue(Integer questValue) {
        this.questValue = questValue;
    }
}
