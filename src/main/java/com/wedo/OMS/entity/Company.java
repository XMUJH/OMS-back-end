package com.wedo.OMS.entity;

import javax.persistence.*;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;//公司id
    @Column(unique = true)
    private String name;//公司名称
    private String info;//公司详情描述
    @Column(unique = true)
    private String email;//公司邮箱
    @Column(unique = true)
    private String site;//公司官网
    @Column(unique = true)
    private String phone;//公司联系电话
    private long grade;//公司评级

    public Company() {
    }

    public Company(String name, String info, String email, String site, String phone, long grade) {
        this.name = name;
        this.info = info;
        this.email = email;
        this.site = site;
        this.phone = phone;
        this.grade = grade;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", email='" + email + '\'' +
                ", site='" + site + '\'' +
                ", phone='" + phone + '\'' +
                ", grade=" + grade +
                '}';
    }
}
