package com.wedo.OMS.vo;

public class OneString {
    private String str;

    public OneString(){}
    public OneString(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "OneString{" +
                "str='" + str + '\'' +
                '}';
    }
}
