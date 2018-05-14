package com.wedo.OMS.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;//签到记录id

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;//签到用户

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="task_id")
    private Task task;//签到任务

    private Date beginTime;//签到时间

    private Date endTime;//签退时间

    private Date workingTime;//工作时长

    private String violation;//违规情况

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) { this.beginTime = beginTime; }

    public Date getEndTime() { return endTime; }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Date workingTime) {
        this.workingTime = workingTime;
    }

    public String getViolation() {
        return violation;
    }

    public void setViolation(String violation) {
        this.violation = violation;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", user=" + user +
                ", task=" + task +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", workingTime=" + workingTime +
                ", violation='" + violation + '\'' +
                '}';
    }
}
