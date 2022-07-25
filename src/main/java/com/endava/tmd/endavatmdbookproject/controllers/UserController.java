package com.endava.tmd.endavatmdbookproject.controllers;

import com.endava.tmd.endavatmdbookproject.models.User;
import com.endava.tmd.endavatmdbookproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "htpp://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //get all users
    @RequestMapping(method = RequestMethod.GET)
    public List<User> list(){
        return userService.list();
    }

    //create an account
    @RequestMapping(path = "/create",
            method = RequestMethod.POST)
    public Object create(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Object userCreated = userService.create(user);
        if(userCreated == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return userCreated;
    }

    //get user by email
    @RequestMapping(path = "/email",
            method = RequestMethod.GET)
    public Object getUserByEmail(@RequestParam("email") String email){
        Object result = userService.getUserByEmail(email);

        if(result == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return result;
    }
}
