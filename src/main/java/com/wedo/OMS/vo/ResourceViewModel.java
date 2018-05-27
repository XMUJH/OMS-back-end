package com.wedo.OMS.vo;

import com.wedo.OMS.entity.Task;
import com.wedo.OMS.enums.SafetyLevel;

import java.util.List;

public class ResourceViewModel {
    private String resourceName;
    private List<Task> belong;
    private SafetyLevel safetyLevel;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public List<Task> getBelong() {
        return belong;
    }

    public void setBelong(List<Task> belong) {
        this.belong = belong;
    }

    public SafetyLevel getSafetyLevel() {
        return safetyLevel;
    }

    public void setSafetyLevel(SafetyLevel safetyLevel) {
        this.safetyLevel = safetyLevel;
    }

    @Override
    public String toString() {
        return "ResourceViewModel{" +
                "resourceName='" + resourceName + '\'' +
                ", belong=" + belong +
                ", safetyLevel=" + safetyLevel +
                '}';
    }
}
