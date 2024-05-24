package com.example.service.impl;

import com.example.dto.TaskDTO;
import com.example.entity.Task;
import com.example.entity.User;
import com.example.enums.Status;
import com.example.mapper.TaskMapper;
import com.example.repository.TaskRepository;
import com.example.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void save(TaskDTO dto) {

        dto.setTaskStatus((Status.OPEN));
        dto.setAssignedDate(LocalDate.now());
        Task task = taskMapper.convertToEntity((dto));
        taskRepository.save(task);
    }

    @Override
    public void update(TaskDTO dto) {

        Optional<Task> task = taskRepository.findById((dto.getId()));
        Task convertedTask = taskMapper.convertToEntity(dto);

        if (task.isPresent()){
            convertedTask.setTaskStatus(task.get().getTaskStatus());
            convertedTask.setAssignedDate(task.get().getAssignedDate());
            taskRepository.save(convertedTask);
        }

    }

    @Override
    public void delete(Long id) {

        Optional<Task> foundTask = taskRepository.findById(id);

        if(foundTask.isPresent()){
            foundTask.get().setIsDeleted(true);
            taskRepository.save(foundTask.get());
        }
    }

    @Override
    public TaskDTO findById(Long id) {

        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()){
            return taskMapper.convertToDTO(task.get());
        }

        return null;
    }
}
