package com.wedo.OMS.vo;

public class VueTask {
    private long id;//任务id
    private String name;//任务名称
    private long percentage;//任务完成进度
    private String taskColor;//显示颜色

    public VueTask() {
    }

    public VueTask(long id, String name, long percentage, String taskColor) {
        this.id = id;
        this.name = name;
        this.percentage = percentage;
        this.taskColor = taskColor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPercentage() {
        return percentage;
    }

    public void setPercentage(long percentage) {
        this.percentage = percentage;
    }

    public String getTaskColor() {
        return taskColor;
    }

    public void setTaskColor(String taskColor) {
        this.taskColor = taskColor;
    }

    public static String percentToColor(long percentage) {
        if (percentage <= 20) {
            return "#f33232";
        } else if (percentage <= 40) {
            return "#dac606";
        } else if (percentage <= 70) {
            return "#3f95ce";
        } else return "#2cd64d";
    }

    @Override
    public String toString() {
        return "VueTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", percentage=" + percentage +
                ", taskColor='" + taskColor + '\'' +
                '}';
    }
}
