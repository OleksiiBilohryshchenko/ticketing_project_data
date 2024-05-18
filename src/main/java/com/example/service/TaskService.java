package com.example.service;

import com.example.dto.TaskDTO;
import com.example.dto.UserDTO;
import com.example.enums.Status;

import java.util.List;

public interface TaskService extends CrudService<TaskDTO,Long>{

    List<TaskDTO> findTasksByManager(UserDTO manager);
    List<TaskDTO> findAllTasksByStatusIsNot(Status status);
    List<TaskDTO> findAllTasksByStatus(Status status);
    void updateStatus(TaskDTO task);

}
