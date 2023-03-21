package com.example.UserLogin;
import java.io.IOException;
import java.util.*;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class LoginController {
    private Set<User> users;


    public LoginController() {
        users = new HashSet<>();
    }

    @GetMapping("/")
    public String print(){
        return "Hello";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpServletResponse res) throws IOException {
        if (!containsUser(email, password))
            res.sendRedirect("http://localhost:8080/create.html");

        return "Logged In";
    }

    @PostMapping("/create")
    public String addAccount(@RequestParam String email, @RequestParam String password, HttpServletResponse res) throws IOException{
        users.add(new User(email, password));
        res.sendRedirect("http://localhost:8080/login.html");

        return "Account created";
    }
    public boolean containsUser(String e, String p) {
        for (User u: users){
            if (u.getEmail().equals(e) && u.getPass().equals(p))
                return true;
        }

        return false;

    }



}
