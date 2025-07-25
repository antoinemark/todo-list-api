package com.dio.springframeworkexperience.todo.controller;

import com.dio.springframeworkexperience.todo.dto.TaskDTO;
import com.dio.springframeworkexperience.todo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<List<TaskDTO>> create(@RequestBody @Valid List<TaskDTO> taskDTOList){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(taskDTOList));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> read(){
        return ResponseEntity.ok().body(taskService.read());
    }

    @PutMapping
    public ResponseEntity<List<TaskDTO>> update(@RequestBody @Valid List<TaskDTO> taskDTOList){
        return ResponseEntity.ok(taskService.update(taskDTOList));
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody @Valid List<TaskDTO> taskDTOList){
        taskService.delete(taskDTOList);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.findById(id));
    }
}
