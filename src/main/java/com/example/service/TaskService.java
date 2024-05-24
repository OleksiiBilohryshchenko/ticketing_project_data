package com.example.service;

import com.example.dto.ProjectDTO;
import com.example.dto.TaskDTO;
import com.example.enums.Status;

import java.util.List;

public interface TaskService {

    List<TaskDTO> listAllTasks();
    void save (TaskDTO dto);
    void update (TaskDTO dto);
    void delete (Long id);
    TaskDTO findById(Long id);
    int totalNonCompletedTask(String projectCode);
    int totalCompletedTask(String projectCode);
    void deleteByProject(ProjectDTO projectDTO);
    void completeByProject(ProjectDTO projectDTO);

    List<TaskDTO> listAllTasksByStatusIsNot(Status status);
    List<TaskDTO> listAllTasksByStatus(Status status);


}
