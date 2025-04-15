package com.example.taskManagerAppBackend.Task.Manager.App.Service;

import com.example.taskManagerAppBackend.Task.Manager.App.Entity.Task;
import com.example.taskManagerAppBackend.Task.Manager.App.Entity.User;
import com.example.taskManagerAppBackend.Task.Manager.App.Repository.TaskRepository;
import com.example.taskManagerAppBackend.Task.Manager.App.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    //to create the task.
    public Task saveTask(Task task, String userName){
        User user = userRepository.findByUserName(userName);
        Task saveInput = taskRepository.save(task);
        user.getAllTasks().add(saveInput);
        userService.saveEntry(user);

        return null;
    }



}
