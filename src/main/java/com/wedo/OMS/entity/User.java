package com.wedo.OMS.entity;

import com.wedo.OMS.enums.Gender;
import com.wedo.OMS.enums.UserRole;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;//用户id
    private String account;//用户账号
    private String phone;//用户手机号
    private String email;//用户邮箱
    private String name;//用户名称
    private String password;//用户密码
    @Enumerated
    private Gender gender;//用户性别，男为0，女为1

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;//用户公司

    private UserRole role;//用户身份，SENDER为0，RECEIVER为1

    private String photoUrl;//用户头像url

    private String faceUrl;//用户人脸url


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", company=" + company +
                ", role=" + role +
                ", photoUrl='" + photoUrl + '\'' +
                ", faceUrl='" + faceUrl + '\'' +
                '}';
    }
}
