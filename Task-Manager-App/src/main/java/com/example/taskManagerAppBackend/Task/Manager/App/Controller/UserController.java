package com.example.taskManagerAppBackend.Task.Manager.App.Controller;

import com.example.taskManagerAppBackend.Task.Manager.App.Entity.User;
import com.example.taskManagerAppBackend.Task.Manager.App.Repository.UserRepository;
import com.example.taskManagerAppBackend.Task.Manager.App.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public ResponseEntity<?> createUsers(@RequestBody User user){
        User createdUsers = userService.saveEntry(user);

        return ResponseEntity.ok("users created");
    }

    //to update the user
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/api/update/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userInDb = userRepository.findByUserName(userName);

        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(encoder.encode(user.getPassword()));
            userService.saveEntry(userInDb);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/delete/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable String userName){
        userService.deleteByUserName(userName);

        return ResponseEntity.ok("User has Deleted");
    }
}
