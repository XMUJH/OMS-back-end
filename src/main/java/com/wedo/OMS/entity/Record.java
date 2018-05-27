package com.wedo.OMS.entity;

import javax.persistence.*;

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;//日志id
    private String content;//日志内容
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;//签到记录

    public Record() {
    }

    public Record(String content, Attendance attendance) {
        this.content = content;
        this.attendance = attendance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", attendance=" + attendance +
                '}';
    }
}
