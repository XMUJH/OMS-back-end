package com.wedo.OMS.entity;

import com.wedo.OMS.enums.UserTaskRole;
import com.wedo.OMS.enums.VerifyStatus;

import javax.persistence.*;

@Entity
public class UserTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="task_id")
    private Task task;

    @Enumerated
    private UserTaskRole userTaskRole;//负责人为0，参与人员为1

    private String job;//用户职责

    private VerifyStatus status;//审核状态，NOTPASSED为0，PASSED为1

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public UserTaskRole getUserTaskRole() {
        return userTaskRole;
    }

    public void setUserTaskRole(UserTaskRole userTaskRole) {
        this.userTaskRole = userTaskRole;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public VerifyStatus getStatus() {
        return status;
    }

    public void setStatus(VerifyStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserTask{" +
                "id=" + id +
                ", user=" + user +
                ", task=" + task +
                ", userTaskRole=" + userTaskRole +
                ", job='" + job + '\'' +
                ", status=" + status +
                '}';
    }
}
