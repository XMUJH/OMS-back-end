package com.wedo.OMS.entity;

import com.wedo.OMS.enums.SafetyLevel;

import javax.persistence.*;
import java.util.Date;

/**
 * @description:一个任务有多个用户参与
 */
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;//任务id
    private String name;//任务名称
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;//任务所属项目
    private Date createTime;//任务创建时间
    private Date beginTime;//任务开始时间
    private Date endTime;//任务结束时间
    @Enumerated
    private SafetyLevel safety;//任务安全等级
    private String info;//任务详情描述
    private String contractUrl;//合同信息URL'
    private String agreementUrl;//保密协议URL
    private long completion;//里程碑完成个数
    private long total;//里程碑总个数
    private long changeCount;//里程碑修改次数

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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

    public SafetyLevel getSafety() {
        return safety;
    }

    public void setSafety(SafetyLevel safety) {
        this.safety = safety;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public String getAgreementUrl() {
        return agreementUrl;
    }

    public void setAgreementUrl(String agreementUrl) {
        this.agreementUrl = agreementUrl;
    }

    public long getCompletion() {
        return completion;
    }

    public void setCompletion(long completion) {
        this.completion = completion;
    }

    public long getChangeCount() {
        return changeCount;
    }

    public void setChangeCount(long changeCount) {
        this.changeCount = changeCount;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", project=" + project +
                ", createTime=" + createTime +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", safety=" + safety +
                ", info='" + info + '\'' +
                ", contractUrl='" + contractUrl + '\'' +
                ", agreementUrl='" + agreementUrl + '\'' +
                ", completion=" + completion +
                ", total=" + total +
                ", changeCount=" + changeCount +
                '}';
    }
}
