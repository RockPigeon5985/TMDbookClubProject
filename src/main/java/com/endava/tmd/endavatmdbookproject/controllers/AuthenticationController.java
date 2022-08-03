package com.endava.tmd.endavatmdbookproject.controllers;

import com.endava.tmd.endavatmdbookproject.config.JWTTokenHelper;
import com.endava.tmd.endavatmdbookproject.models.User;
import com.endava.tmd.endavatmdbookproject.request.AuthenticationRequest;
import com.endava.tmd.endavatmdbookproject.response.UserInfo;
import com.endava.tmd.endavatmdbookproject.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @RequestMapping(path = "/login",
            method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUserName(),
                            authenticationRequest.getPassword())
            );
        } catch (Exception ex) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Expose-Headers", "*");
        responseHeaders.set("Authorization",
                jwtTokenHelper.generateToken(authenticationRequest.getUserName()));

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .build();
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
