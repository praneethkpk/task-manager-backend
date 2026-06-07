package com.praneeth.taskmanager.task.controller;

import com.praneeth.taskmanager.task.dto.CreateTaskRequest;
import com.praneeth.taskmanager.task.dto.TaskResponse;
import com.praneeth.taskmanager.task.dto.UpdateTaskStatusRequest;
import com.praneeth.taskmanager.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponse>>
    getMyTasks() {

        return ResponseEntity.ok(
                taskService.getMyTasks()
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse>
    updateTaskStatus(

            @PathVariable
            Long id,

            @RequestBody
            UpdateTaskStatusRequest request

    ) {

        return ResponseEntity.ok(
                taskService.updateTaskStatus(
                        id,
                        request
                )
        );
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<TaskResponse>>
    getMyTasks(

            @RequestParam
            int page,

            @RequestParam
            int size

    ) {

        return ResponseEntity.ok(
                taskService.getMyTasks(
                        page,
                        size
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable
            Long id
    ) {
        System.out.println("DELETE CONTROLLER CALLED");
        taskService.deleteTask(id);

        return ResponseEntity
                .noContent()
                .build();
    }
    @GetMapping("/hello")
    public String hello() {
        System.out.println("HELLO ENDPOINT HIT");
        return "hello";
    }
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid
            @RequestBody
            CreateTaskRequest request
    ) {


        TaskResponse response =
                taskService.createTask(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
