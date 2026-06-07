package com.praneeth.taskmanager.task.repository;

import com.praneeth.taskmanager.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import com.praneeth.taskmanager.user.entity.User;

import java.util.List;
import java.util.Optional;
import com.praneeth.taskmanager.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskRepository
        extends JpaRepository<Task, Long> {

    Page<Task> findByUser(
            User user,
            Pageable pageable
    );
    List<Task> findByUser(User user);

    Optional<Task> findByIdAndUser(
            Long id,
            User user
    );
}