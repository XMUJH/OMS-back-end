package com.wedo.OMS.vo;

public class EnumChoice {
    private String choice;

    public EnumChoice(){}
    public EnumChoice(String choice) {
        this.choice = choice;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    @Override
    public String toString() {
        return "EnumChoice{" +
                "status='" + choice + '\'' +
                '}';
    }
}
