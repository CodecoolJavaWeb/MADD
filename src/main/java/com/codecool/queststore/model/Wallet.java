package com.codecool.queststore.model;

public class Wallet {

    private Integer currentCoinsAmount;
    private Integer totalCoinsAmount;

    public Integer getCurrentCoinsAmount() {
        return currentCoinsAmount;
    }

    public void setCurrentCoinsAmount(Integer currentCoinsAmount) {
        this.currentCoinsAmount = currentCoinsAmount;
    }

    public Integer getTotalCoinsAmount() {
        return totalCoinsAmount;
    }

    public void setTotalCoinsAmount(Integer totalCoinsAmount) {
        this.totalCoinsAmount = totalCoinsAmount;
    }
}
