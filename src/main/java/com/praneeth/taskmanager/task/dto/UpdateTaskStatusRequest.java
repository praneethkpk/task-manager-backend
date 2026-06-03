package com.praneeth.taskmanager.task.dto;

import com.praneeth.taskmanager.task.entity.TaskStatus;

public record UpdateTaskStatusRequest(

        TaskStatus status

) {
}