package com.wedo.OMS.entity;

import javax.persistence.*;

@Entity
public class TaskResource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="task_id")
    private Task task;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="resource_id")
    private Resource resource;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }


    @Override
    public String toString() {
        return "TaskResource{" +
                "id=" + id +
                ", task=" + task +
                ", resource=" + resource +
                '}';
    }
}
