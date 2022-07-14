package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.models.User;
import com.endava.tmd.endavatmdbookproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //get all users
    public List<User> list(){
        return userRepository.findAll();
    }


    //create an account
    public User create(User user){
        try{
            return userRepository.saveAndFlush(user);
        }catch (Exception e){
            return null;
        }
    }

    //used by other services

    //get user by user id
    public User getUserByUserid(Long id){
        return userRepository.getUserByUserid(id);
    }
    //get user by email
    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }
}
