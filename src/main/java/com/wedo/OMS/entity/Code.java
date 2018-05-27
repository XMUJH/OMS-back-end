package com.wedo.OMS.entity;

import com.wedo.OMS.enums.CodeStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;//激活码id
    @Column(unique = true)
    private String code;//激活码
    private CodeStatus status;//激活码状态，无效为0，有效为1
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "task_id")
    private Task task;//相应任务
    private Date time;//激活码过期时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public CodeStatus getStatus() {
        return status;
    }

    public void setStatus(CodeStatus status) {
        this.status = status;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Code() {
    }

    public Code(String code, CodeStatus status, Task task, Date time) {
        this.code = code;
        this.status = status;
        this.task = task;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", code=" + code +
                ", status=" + status +
                ", task=" + task +
                ", time=" + time +
                '}';
    }
}
