package com.example.taskManagerAppBackend.Task.Manager.App.Service;

import com.example.taskManagerAppBackend.Task.Manager.App.Entity.User;
import com.example.taskManagerAppBackend.Task.Manager.App.Model.UserPrincipal;
import com.example.taskManagerAppBackend.Task.Manager.App.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);

        if(user == null){
            throw new UsernameNotFoundException("user not found with username " + username);
        }


        return new UserPrincipal(user);
    }
}
