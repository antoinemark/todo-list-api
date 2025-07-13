package com.dio.springframeworkexperience.todo.repository;

import com.dio.springframeworkexperience.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
