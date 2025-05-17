package com.example.taskManagerAppBackend.Task.Manager.App.Controller;

import com.example.taskManagerAppBackend.Task.Manager.App.Entity.User;
import com.example.taskManagerAppBackend.Task.Manager.App.Repository.UserRepository;
import com.example.taskManagerAppBackend.Task.Manager.App.Service.CustomUserDetailsService;
import com.example.taskManagerAppBackend.Task.Manager.App.Service.UserService;
import com.example.taskManagerAppBackend.Task.Manager.App.Utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user/auth")
@Slf4j
public class UserController {

    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user){
        User createdUsers = userService.saveEntry(user);

        return ResponseEntity.ok("users created");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user){

        try{
            auth.authenticate(new UsernamePasswordAuthenticationToken
                    (user.getUserName(), user.getPassword()));

            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("incorrect username or password", HttpStatus.BAD_REQUEST);
        }
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

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/delete/deleteAll")
    public ResponseEntity<?> deleteAllUsers(){
        userService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
