package com.endava.tmd.endavatmdbookproject.controllers;

import com.endava.tmd.endavatmdbookproject.config.JWTTokenHelper;
import com.endava.tmd.endavatmdbookproject.models.User;
import com.endava.tmd.endavatmdbookproject.request.AuthenticationRequest;
import com.endava.tmd.endavatmdbookproject.response.LoginResponse;
import com.endava.tmd.endavatmdbookproject.response.UserInfo;
import com.endava.tmd.endavatmdbookproject.services.UserDetailsServiceImpl;
import com.endava.tmd.endavatmdbookproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(path = "/login",
            method = RequestMethod.POST)
    public String login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUserName(),
                            authenticationRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtTokenHelper.generateToken(authenticationRequest.getUserName());
    }

    @RequestMapping(path = "/auth/userinfo",
        method = RequestMethod.GET)
    public ResponseEntity<?> getUserInfo(Principal user) {
        User userObj = (User) userDetailsService.loadUserByUsername(user.getName());

        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(userObj.getFirst_name());
        userInfo.setLastName(userObj.getLast_name());
        userInfo.setRoles(userObj.getRole());

        return ResponseEntity.ok(userInfo);
    }
}
