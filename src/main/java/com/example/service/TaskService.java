package com.example.service;

import com.example.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> listAllTasks();
    void save (TaskDTO dto);
    void update (TaskDTO dto);
    void delete (Long id);
    TaskDTO findById(Long id);


}
