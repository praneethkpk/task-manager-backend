package com.praneeth.taskmanager.task.service;

import com.praneeth.taskmanager.task.dto.CreateTaskRequest;
import com.praneeth.taskmanager.task.dto.TaskResponse;
import com.praneeth.taskmanager.task.dto.UpdateTaskStatusRequest;

import java.util.List;
import org.springframework.data.domain.Page;


public interface TaskService {

    TaskResponse createTask(
            CreateTaskRequest request

    );
    Page<TaskResponse> getMyTasks(
            int page,
            int size
    );
    List<TaskResponse> getMyTasks();

    TaskResponse updateTaskStatus(
            Long taskId,
            UpdateTaskStatusRequest request
    );
    void deleteTask(Long taskId);
}