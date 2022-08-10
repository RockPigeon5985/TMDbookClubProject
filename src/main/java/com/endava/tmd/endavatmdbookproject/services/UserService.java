package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.models.User;
import com.endava.tmd.endavatmdbookproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //get all users
    public List<User> list(){
        return userRepository.findAll();
    }

    //create an account
    public User create(User user){
        if(user.getPassword().equals("") || user.getEmail().equals("") ||
                user.getFirst_name().equals("") || user.getLast_name().equals("")){
            return null;
        }

        user.setEnabled(true);
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try{
            return userRepository.saveAndFlush(user);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    //get user by email
    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    //used by other services

    //get user by user id
    public User getUserByUserid(Long id){
        return userRepository.getUserByUserid(id);
    }

}
