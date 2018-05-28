package com.wedo.OMS.vo;

import java.util.List;

public class ProjectViewModel {
    private long id;
    private String label;
    private int type;//0为project,1为任务
    private List<ProjectViewModel> children;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ProjectViewModel> getChildren() {
        return children;
    }

    public void setChildren(List<ProjectViewModel> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ProjectViewModel{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", type=" + type +
                ", children=" + children +
                '}';
    }
}