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

    public List<User> list(){
        return userRepository.findAll();
    }
    public User get(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public User create(User user){
        try{
            return userRepository.saveAndFlush(user);
        }catch (Exception e){
            return null;
        }
    }
    public boolean deleteById(Long id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }
}
