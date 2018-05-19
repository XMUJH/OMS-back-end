package com.wedo.OMS.entity;

import com.wedo.OMS.enums.SafetyLevel;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Resource {
    @Id
    @GeneratedValue
    private long id;//资源文件id
    @Column(unique = true)
    private String name;//资源文件名称
    private String type;//资源文件类型
    private String size;//资源文件大小
    @Column(unique = true)
    private String address;//资源文件地址
    private Date commit;//资源文件提交时间
    private String info;//资源文件详情描述
    @Enumerated
    private SafetyLevel safety;//资源文件安全等级

    public Resource() {
    }

    public Resource(String name, String type, String size, String address, Date commit, String info, SafetyLevel safety) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.address = address;
        this.commit = commit;
        this.info = info;
        this.safety = safety;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public SafetyLevel getSafety() {
        return safety;
    }

    public void setSafety(SafetyLevel safety) {
        this.safety = safety;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", address='" + address + '\'' +
                ", commit=" + commit +
                ", info='" + info + '\'' +
                ", safety=" + safety +
                '}';
    }
}
