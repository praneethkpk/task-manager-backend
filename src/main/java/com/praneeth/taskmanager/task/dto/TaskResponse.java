package com.praneeth.taskmanager.task.dto;

import com.praneeth.taskmanager.task.entity.TaskPriority;
import com.praneeth.taskmanager.task.entity.TaskStatus;

public record TaskResponse(

        Long id,

        String title,

        String description,

        TaskStatus status,
        TaskPriority priority

) {
}