package com.endava.tmd.endavatmdbookproject.controllers;

import com.endava.tmd.endavatmdbookproject.models.User;
import com.endava.tmd.endavatmdbookproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "htpp://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //get all users
    @RequestMapping(method = RequestMethod.GET)
    public List<User> list(){
        return userService.list();
    }

    //create an account
    @RequestMapping(path = "/create",
            method = RequestMethod.POST)
    public Object create(@RequestBody User user){
        Object userCreated = userService.create(user);
        if(userCreated == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return userCreated;
    }
}
