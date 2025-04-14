package com.example.taskManagerAppBackend.Task.Manager.App.Controller;

import com.example.taskManagerAppBackend.Task.Manager.App.Entity.User;
import com.example.taskManagerAppBackend.Task.Manager.App.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUsers(@RequestBody User user){
        User createdUsers = userService.saveEntry(user);

        return ResponseEntity.ok("users created");
    }
}
