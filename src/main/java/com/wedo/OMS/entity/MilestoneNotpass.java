package com.wedo.OMS.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 里程碑审核记录
 */
public class MilestoneNotpass {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;//审核记录所属里程碑
    private Date createTime;//审核时间
    private String reason;//审核未通过原因

    public MilestoneNotpass() {
    }

    public MilestoneNotpass(Milestone milestone, Date createTime, String reason) {
        this.milestone = milestone;
        this.createTime = createTime;
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "MilestoneNotpass{" +
                "id=" + id +
                ", milestone=" + milestone +
                ", createTime=" + createTime +
                ", reason='" + reason + '\'' +
                '}';
    }
}
