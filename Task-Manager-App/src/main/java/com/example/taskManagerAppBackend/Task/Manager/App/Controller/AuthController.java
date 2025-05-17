package com.example.taskManagerAppBackend.Task.Manager.App.Controller;

import com.example.taskManagerAppBackend.Task.Manager.App.Model.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/login/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;


//    @PostMapping("/login")
//    public ResponseEntity<?> loginPage(@RequestBody UserDTO request, HttpServletRequest httpRequest){
//
//        try{
//
//            Authentication auth = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(auth);
//            HttpSession session = httpRequest.getSession(true);
//
//            return ResponseEntity.ok(Map.of(
//                    "message", "Login Successful",
//                    "sessionId", session.getId()
//            ));
//
//        }catch(Exception e){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
//                    "error",
//                    "invalid username and password"
//            ));
//        }
//    }


    @PostMapping("/logout")
    public ResponseEntity<?> logoutPage(HttpServletRequest httpRequest){
        HttpSession session = httpRequest.getSession(false);

        if(session != null){
            session.invalidate();
        }

        SecurityContextHolder.clearContext();

        return ResponseEntity.ok(Map.of(
                "message", "Logged Out Successful"
        ));
    }
}
