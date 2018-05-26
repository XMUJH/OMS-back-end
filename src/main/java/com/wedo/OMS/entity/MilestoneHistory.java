package com.wedo.OMS.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 里程碑审核记录
 */
@Entity
public class MilestoneHistory {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;//审核记录所属里程碑
    private Date createTime;//审核时间
    private long status;//该条历史记录的状态（第几次审核、未通过、通过）
    private String reason;//审核未通过原因

    public MilestoneHistory() {
    }

    public MilestoneHistory(Milestone milestone, Date createTime, long status, String reason) {
        this.milestone = milestone;
        this.createTime = createTime;
        this.status = status;
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

    public long getStatus(){ return status; }

    public void setStatus(long status){ this.status = status; }

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
                ", status=" + status +
                ", reason='" + reason + '\'' +
                '}';
    }
}
