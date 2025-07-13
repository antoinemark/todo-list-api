package com.dio.springframeworkexperience.todo.service;

import com.dio.springframeworkexperience.todo.dto.TaskDTO;
import com.dio.springframeworkexperience.todo.mapper.TaskMapper;
import com.dio.springframeworkexperience.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskRepository repository;

    public List<TaskDTO> create(List<TaskDTO> taskDTOList){
        return taskMapper.toTaskDTOList(repository.saveAll(taskMapper.toTaskList(taskDTOList)));
    }

    public List<TaskDTO> read(){
        return taskMapper.toTaskDTOList(repository.findAll());
    }

    public List<TaskDTO> update(List<TaskDTO> taskDTOList){
        taskDTOList.forEach((p) -> findById(p.getId()));
        return taskMapper.toTaskDTOList(repository.saveAll(taskMapper.toTaskList(taskDTOList)));
    }

    public void delete(List<TaskDTO> taskDTOList){
        taskDTOList.forEach((p) -> findById(p.getId()));
        repository.deleteAll(taskMapper.toTaskList(taskDTOList));
    }

    public TaskDTO findById(Long id){
        return taskMapper.toTaskDTO(repository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Task with id" + id + " not found.")));
    }

}
