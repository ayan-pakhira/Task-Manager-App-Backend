package com.example.taskManagerAppBackend.Task.Manager.App.Service;

import com.example.taskManagerAppBackend.Task.Manager.App.Entity.User;
import com.example.taskManagerAppBackend.Task.Manager.App.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authManager;

    //private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    //to register the new user.
    public User saveEntry(User user){
        User user1 = userRepository.findByUserName(user.getUserName());

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        return userRepository.save(user);
    }


    //to register the user as an admin.
    public User saveAdminEntry(String userName, String password){

        User admin = new User();

        admin.setPassword(encoder.encode(password));
        admin.setUserName(userName);
        admin.setRoles(Arrays.asList("ADMIN", "USER"));
        return userRepository.save(admin);
    }


    //TO fetch all the users
    public List<User> getAll(){
        return userRepository.findAll();
    }



}
