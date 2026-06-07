package com.praneeth.taskmanager.task.service;

import com.praneeth.taskmanager.task.dto.CreateTaskRequest;
import com.praneeth.taskmanager.task.dto.TaskResponse;
import com.praneeth.taskmanager.task.entity.Task;
import com.praneeth.taskmanager.task.entity.TaskPriority;
import com.praneeth.taskmanager.task.entity.TaskStatus;
import com.praneeth.taskmanager.task.repository.TaskRepository;
import com.praneeth.taskmanager.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import com.praneeth.taskmanager.task.dto.UpdateTaskStatusRequest;
import com.praneeth.taskmanager.common.exception.TaskNotFoundException;

import com.praneeth.taskmanager.task.dto.CreateTaskRequest;
import com.praneeth.taskmanager.task.dto.TaskResponse;
import org.springframework.stereotype.Service;
import com.praneeth.taskmanager.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl
        implements TaskService {

    private final TaskRepository taskRepository;
    @Override
    public TaskResponse createTask(
            CreateTaskRequest request
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user =
                (User) authentication.getPrincipal();

        Task task = Task.builder()
                .title(request.title())
                .description(request.description())
                .status(TaskStatus.PENDING)
                .priority(
                        request.priority() != null
                                ? request.priority()
                                : TaskPriority.MEDIUM
                )
                .user(user)
                .build();

        Task savedTask =
                taskRepository.save(task);

        return new TaskResponse(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getStatus(),
                savedTask.getPriority()
        );
    }

    @Override
    public TaskResponse updateTaskStatus(
            Long taskId,
            UpdateTaskStatusRequest request
    ) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user =
                (User) authentication.getPrincipal();

        Task task =
                taskRepository
                        .findByIdAndUser(
                                taskId,
                                user
                        )
                        .orElseThrow(
                                () ->
                                        new TaskNotFoundException(
                                                taskId
                                        )
                        );

        task.setStatus(
                request.status()
        );

        Task updatedTask =
                taskRepository.save(task);

        return new TaskResponse(
                updatedTask.getId(),
                updatedTask.getTitle(),
                updatedTask.getDescription(),
                updatedTask.getStatus(),
                updatedTask.getPriority()
        );
    }

    @Override
    public void deleteTask(
            Long taskId
    ) {
        System.out.println("DELETE SERVICE CALLED");
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user =
                (User) authentication.getPrincipal();

        Task task =
                taskRepository
                        .findByIdAndUser(
                                taskId,
                                user
                        )
                        .orElseThrow(
                                () ->
                                        new TaskNotFoundException(
                                                taskId
                                        )
                        );
        System.out.println("Current User ID: " + user.getId());
        System.out.println("Task User ID: " + task.getUser().getId());
        System.out.println("Deleting task...");
        taskRepository.delete(task);

    }
    @Override
    public List<TaskResponse> getMyTasks() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        User user =
                (User) authentication.getPrincipal();

        List<Task> tasks =
                taskRepository.findByUser(user);

        return tasks.stream()
                .map(task ->
                        new TaskResponse(
                                task.getId(),
                                task.getTitle(),
                                task.getDescription(),
                                task.getStatus(),
                                task.getPriority()
                        )
                )
                .toList();
    }
}