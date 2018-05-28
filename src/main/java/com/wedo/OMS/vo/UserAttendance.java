package com.wedo.OMS.vo;

import java.util.Date;

public class UserAttendance {
    private long userId;
    private long taskId;
    private Date date;
    private String content;

    public UserAttendance(){

    }

    public UserAttendance(long userId, long taskId, Date date, String content) {
        this.userId = userId;
        this.taskId = taskId;
        this.date = date;
        this.content = content;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "UserAttendance{" +
                "userId=" + userId +
                ", taskId=" + taskId +
                ", date=" + date +
                ", content='" + content + '\'' +
                '}';
    }
}
