package com.wedo.OMS.vo;

import com.wedo.OMS.entity.Project;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class NewProject {
    private String name;//项目名称
    private String password;//项目密码
    private long completion;//里程碑完成个数
    private long total;//里程碑总个数
    private long belongProjectId;//所属项目id

    public NewProject(){}
    public NewProject(String name, String password, long completion, long total, long belongProjectId) {
        this.name = name;
        this.password = password;
        this.completion = completion;
        this.total = total;
        this.belongProjectId = belongProjectId;
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

    public long getBelongProjectId() {
        return belongProjectId;
    }

    public void setBelongProjectId(long belongProjectId) {
        this.belongProjectId = belongProjectId;
    }

    @Override
    public String toString() {
        return "NewProject{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", completion=" + completion +
                ", total=" + total +
                ", belongProjectId=" + belongProjectId +
                '}';
    }
}
