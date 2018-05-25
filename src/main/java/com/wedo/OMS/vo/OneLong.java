package com.wedo.OMS.vo;

public class OneLong {
    private long id;

    public OneLong(){}
    public OneLong(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OneLong{" +
                "id=" + id +
                '}';
    }
}
