package com.wedo.OMS.service;

import com.wedo.OMS.exception.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    @Before
    public void setUp() {

    }

    @Test
    public void listTasksByUserId() throws UserNotFoundException {
        //TODO Should use assert
        System.out.println(taskService.listTasksByUserId(new Long(0)));

    }

    @Test
    public void getTaskByTaskId() {
    }

    @Test
    public void getTasksByTaskname() {
    }

    @Test
    public void addTask() {
    }

    @Test
    public void updateTaskByTaskId() {
    }

    @Test
    public void addTaskUser() {
    }

    @Test
    public void auditTaskUserById() {
    }

    @Test
    public void deleteTaskUserById() {
    }
}