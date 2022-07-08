package com.endava.tmd.endavatmdbookproject.controllers;

import com.endava.tmd.endavatmdbookproject.models.Book;
import com.endava.tmd.endavatmdbookproject.models.User;
import com.endava.tmd.endavatmdbookproject.repositories.UserRepository;
import com.endava.tmd.endavatmdbookproject.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> list(){
        return userService.list();
    }

    @RequestMapping(path = "/get",
            params = "id",
            method = RequestMethod.GET)
    public Object get(@RequestParam Long id){
        if(userService.get(id) == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return userService.get(id);
    }

    @RequestMapping(path = "/create",
            method = RequestMethod.POST)
    public Object create(@RequestBody User user){
        Object userCreated = userService.create(user);
        if(userCreated == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return userCreated;
    }

    @RequestMapping(path ="/delete",
            params = "id",
            method = RequestMethod.DELETE)
    public Object delete(@RequestParam Long id){
        if(!userService.deleteById(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/get/email",
    params = "email",
    method = RequestMethod.GET)
    public Object getUserByEmail(@RequestParam String email){
        if(userService.getUserByEmail(email) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return userService.getUserByEmail(email);
    }
}
