package com.wedo.OMS.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;//项目id
    private String name;//项目名称
    private String password;//项目密码
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project belong;//前一级项目
    private Date createTime;//项目创建的时间
    private Date beginTime;//项目开始时间
    private Date endTime;//项目结束时间
    private long completionRate;//完成度

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Project getBelong() {
        return belong;
    }

    public void setBelong(Project belong) {
        this.belong = belong;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(long completionRate) {
        this.completionRate = completionRate;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", belong=" + belong +
                ", createTime=" + createTime +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", completionRate=" + completionRate +
                '}';
    }
}
