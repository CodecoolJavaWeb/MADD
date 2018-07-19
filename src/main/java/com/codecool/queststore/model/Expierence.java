package com.codecool.queststore.model;

public class Expierence {

    public Expierence(int id_level, String level_name, int achieve_money) {
        this.id_level = id_level;
        this.level_name = level_name;
        this.achieve_money = achieve_money;
    }

    private int id_level;
    private String level_name;

    public int getId_level() {
        return id_level;
    }

    public void setId_level(int id_level) {
        this.id_level = id_level;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public int getAchieve_money() {
        return achieve_money;
    }

    public void setAchieve_money(int achieve_money) {
        this.achieve_money = achieve_money;
    }

    private int achieve_money;
}
