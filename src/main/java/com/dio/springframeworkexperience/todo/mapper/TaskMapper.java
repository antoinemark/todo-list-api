package com.dio.springframeworkexperience.todo.mapper;

import com.dio.springframeworkexperience.todo.dto.TaskDTO;
import com.dio.springframeworkexperience.todo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {

    @Autowired
    private ModelMapper mapper;

    public Task toTask(TaskDTO taskDTO){
         return mapper.map(taskDTO, Task.class);
    }

    public TaskDTO toTaskDTO(Task task){
        TaskDTO taskDTO = mapper.map(task,TaskDTO.class);
        System.out.println("TaskDTO gerado: " + taskDTO);
        return taskDTO;
    }

    public List<Task> toTaskList(List<TaskDTO> taskDTOList){
        return taskDTOList.stream().map(this::toTask).toList();
    }

    public List<TaskDTO> toTaskDTOList(List<Task> taskList){
        return taskList.stream().map(this::toTaskDTO).toList();
    }

}
