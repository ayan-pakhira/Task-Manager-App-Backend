package com.example.taskManagerAppBackend.Task.Manager.App.Controller;

import com.example.taskManagerAppBackend.Task.Manager.App.Entity.User;
import com.example.taskManagerAppBackend.Task.Manager.App.Model.UserDTO;
import com.example.taskManagerAppBackend.Task.Manager.App.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin/auth")
public class AdminController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody UserDTO request){
        User createdAdmin = userService.saveAdminEntry(request.getUserName(), request.getPassword());

        return ResponseEntity.ok("Admin has created");
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> all = userService.getAll();

        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
