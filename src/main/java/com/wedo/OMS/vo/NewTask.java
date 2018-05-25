package com.wedo.OMS.vo;

import com.wedo.OMS.entity.Project;
import com.wedo.OMS.enums.SafetyLevel;

import javax.persistence.CascadeType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

public class NewTask {
    private String name;//任务名称
    private Date createTime;//任务创建时间
    private Date beginTime;//任务开始时间
    private Date endTime;//任务结束时间
    private String safety;//任务安全等级
    private String info;//任务详情描述
    private String contractUrl;//合同信息URL
    private String agreementUrl;//保密协议URL
    private long completion;//里程碑完成个数
    private long total;//里程碑总个数
    private long changeCount;//里程碑修改次数
    private long projectId;//任务所属项目

    public NewTask(){}
    public NewTask(String name, Date createTime, Date beginTime, Date endTime, String safety, String info, String contractUrl, String agreementUrl, long completion, long total, long changeCount, long projectId) {
        this.name = name;
        this.createTime = createTime;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.safety = safety;
        this.info = info;
        this.contractUrl = contractUrl;
        this.agreementUrl = agreementUrl;
        this.completion = completion;
        this.total = total;
        this.changeCount = changeCount;
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSafety() {
        return safety;
    }

    public void setSafety(String safety) {
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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getChangeCount() {
        return changeCount;
    }

    public void setChangeCount(long changeCount) {
        this.changeCount = changeCount;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "NewTask{" +
                "name='" + name + '\'' +
                ", createTime=" + createTime +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", safety='" + safety + '\'' +
                ", info='" + info + '\'' +
                ", contractUrl='" + contractUrl + '\'' +
                ", agreementUrl='" + agreementUrl + '\'' +
                ", completion=" + completion +
                ", total=" + total +
                ", changeCount=" + changeCount +
                ", projectId=" + projectId +
                '}';
    }
}
