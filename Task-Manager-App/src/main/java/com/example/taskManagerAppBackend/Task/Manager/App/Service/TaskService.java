package com.example.taskManagerAppBackend.Task.Manager.App.Service;

import com.example.taskManagerAppBackend.Task.Manager.App.Entity.Task;
import com.example.taskManagerAppBackend.Task.Manager.App.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    public Task saveTask(Task task){
        Task saved = taskRepository.save(task);

        return saved;
    }
}
