package com.wedo.OMS.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;//成果文件id
    private String name;//成果文件名称
    private String size;//成果文件大小
    private String address;//成果文件地址
    private Date commit;//成果文件提交时间
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;//成果文件所属里程碑

    public Result() {
    }

    public Result(String name, String size, String address, Date commit, Milestone milestone) {
        this.name = name;
        this.size = size;
        this.address = address;
        this.commit = commit;
        this.milestone = milestone;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCommit() {
        return commit;
    }

    public void setCommit(Date commit) {
        this.commit = commit;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", address='" + address + '\'' +
                ", commit=" + commit +
                ", milestone=" + milestone +
                '}';
    }
}
