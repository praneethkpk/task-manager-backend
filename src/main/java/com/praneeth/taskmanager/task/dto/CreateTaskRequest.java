package com.praneeth.taskmanager.task.dto;

import jakarta.validation.constraints.NotBlank;
import com.praneeth.taskmanager.task.entity.TaskPriority;

public record CreateTaskRequest(

        @NotBlank
        String title,

        String description,
        TaskPriority priority

) {
}