package com.wedo.OMS.entity.viewmodel;

import com.wedo.OMS.entity.Company;
import com.wedo.OMS.entity.User;
import com.wedo.OMS.enums.Gender;
import com.wedo.OMS.enums.UserRole;

public class UserViewModel {
    private long id;//用户id
    private String account;//用户账号
    private String phone;//用户手机号
    private String email;//用户邮箱
    private String name;//用户名称
    private Gender gender;//用户性别，男为0，女为1
    private Company company;//用户公司
    private UserRole role;//用户身份，SENDER为0，RECEIVER为1

    public UserViewModel() {
    }

    public UserViewModel(User user) {
        this.id = user.getId();
        this.account = user.getAccount();
        this.email = user.getEmail();
        this.name = user.getName();
        this.gender = user.getGender();
        this.company = user.getCompany();
        this.role = user.getRole();
    }

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}