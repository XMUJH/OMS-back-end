package com.wedo.OMS.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class UserAttendance {
    private long userId;
    private long taskId;
    private Date date;

    public UserAttendance(){

    }

    public UserAttendance(long userId, long taskId, Date date) {
        this.userId = userId;
        this.taskId = taskId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "UserAttendance{" +
                "userId=" + userId +
                ", taskId=" + taskId +
                ", date=" + date +
                '}';
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
}
