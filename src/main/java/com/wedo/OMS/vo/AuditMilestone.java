package com.wedo.OMS.vo;

import java.util.Date;

public class AuditMilestone {
    private String status;
    private String reason;
    private Date date;

    public AuditMilestone(){
    }

    public AuditMilestone(String status, String reason, Date date) {
        this.status = status;
        this.reason = reason;
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AuditMilestone{" +
                "status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", date=" + date +
                '}';
    }
}
