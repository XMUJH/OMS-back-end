package com.wedo.OMS.vo;

public class vue_task {
    private long id;//任务id
    private String name;//任务名称
    private long percentage;//任务完成进度
    private String taskColor;//显示颜色

    public vue_task(){}
    public vue_task(long id, String name, long percentage, String taskColor) {
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

    @Override
    public String toString() {
        return "vue_task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", percentage=" + percentage +
                ", taskColor='" + taskColor + '\'' +
                '}';
    }

    public String percentToColor(long percentage) {
        if (percentage<=20){
            return "#f33232";
        }
        else if (percentage>20&&percentage<=40){
            return "#dac606";
        }
        else if (percentage>40&&percentage<=70){
            return "#3f95ce";
        }
        else return "#2cd64d";
    }
}
