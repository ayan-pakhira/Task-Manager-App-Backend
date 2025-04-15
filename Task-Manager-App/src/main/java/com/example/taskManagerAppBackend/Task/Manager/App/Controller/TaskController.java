package com.example.taskManagerAppBackend.Task.Manager.App.Controller;

import com.example.taskManagerAppBackend.Task.Manager.App.Entity.Task;
import com.example.taskManagerAppBackend.Task.Manager.App.Repository.UserRepository;
import com.example.taskManagerAppBackend.Task.Manager.App.Service.TaskService;
import com.example.taskManagerAppBackend.Task.Manager.App.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/task/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/create-task/{userName}")
    public ResponseEntity<?> createTaskEntry(@RequestBody Task task, @PathVariable String userName){
        Task saved = taskService.saveTask(task, userName);

        if(saved != null){
            return ResponseEntity.ok("task has created");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
