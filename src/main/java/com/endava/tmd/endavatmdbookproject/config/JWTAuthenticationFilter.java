package com.endava.tmd.endavatmdbookproject.config;

import com.endava.tmd.endavatmdbookproject.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private UserService userService;
    private  JWTTokenHelper jwtTokenHelper;

    public JWTAuthenticationFilter(UserService userService, JWTTokenHelper jwtTokenHelper) {
        this.userService = userService;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authToken;

        authToken = jwtTokenHelper.getAuthHeaderFromHeader(request);

        if(authToken != null){
            String userName = jwtTokenHelper.getUsernameFromToken(authToken);

            if(userName != null){
                UserDetails userDetails = userService.loadUserByUsername(userName);

                if(jwtTokenHelper.validateToken(authToken, userDetails)){
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    auth.setDetails(request);

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
