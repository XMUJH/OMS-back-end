package com.wedo.OMS.entity;

import com.wedo.OMS.enums.MilestoneStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;//里程碑id
    @Column(unique = true)
    private String name;//里程碑名称
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private Task task;//里程碑所属任务
    private Date beginTime;//里程碑开始时间
    private Date endTime;//里程碑结束时间
    private String info;//里程碑详情描述
    @Enumerated
    private MilestoneStatus status;//里程碑成果审核情况，通过为0，未审核为1,未通过则在Milestone中新增审核记录

    public Milestone() {
    }

    public Milestone(String name, Task task, Date beginTime, Date endTime, String info, MilestoneStatus status) {
        this.name = name;
        this.task = task;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.info = info;
        this.status = status;
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public MilestoneStatus getStatus() {
        return status;
    }

    public void setStatus(MilestoneStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Milestone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", task=" + task +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", info='" + info + '\'' +
                ", status=" + status +
                '}';
    }
}
