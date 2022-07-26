package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.models.Authority;
import com.endava.tmd.endavatmdbookproject.models.User;
import com.endava.tmd.endavatmdbookproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService  implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    //get all users
    public List<User> list(){
        return userRepository.findAll();
    }


    //create an account
    public User create(User user){
        Authority userAuth = new Authority();
        userAuth.setAuth_id(1L);
        userAuth.setRoleCode("USER");

        user.setEnabled(true);
        user.setAuthority(userAuth);
        try{
            return userRepository.saveAndFlush(user);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.getUserByEmail(username);

       if(user == null){
           throw new UsernameNotFoundException("User not found with username: " + username);
       }

       return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
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
