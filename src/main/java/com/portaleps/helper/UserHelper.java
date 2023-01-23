package com.portaleps.helper;

import com.portaleps.exception.CustomException;
import com.portaleps.model.entity.Role;
import com.portaleps.model.entity.User;
import com.portaleps.repository.UserRepository;
import com.portaleps.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserHelper implements UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger(UserHelper.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("loadUserByUsername() - start");
        User user = userRepository.findByUsername(username);

        if(user == null){
            LOGGER.error("loadUserByUsername() - Username not valid: " + username);
            LOGGER.info("loadUserByUsername() - end");
            throw new CustomException("Username not valid", HttpStatus.NOT_FOUND);
        }

        LOGGER.info("loadUserByUsername() - end");
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    public String login(String username, String password) {
        LOGGER.info("login() - start");
        try {
            User user = userRepository.findByUsername(username);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtTokenProvider.createToken(user);
            SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(token));
            LOGGER.info("login() - end");
            return token;
        } catch (AuthenticationException e) {
            LOGGER.error("login() - username/password invalid: " + username);
            LOGGER.info("login() - end");
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public UserDetails loadUserByToken(String token) throws UsernameNotFoundException {
        LOGGER.info("loadUserByToken() - start");
        Claims claims = jwtTokenProvider.getClaimsFromToken(token);

        LOGGER.info("loadUserByToken() - end");
        return org.springframework.security.core.userdetails.User
                .withUsername(claims.get("username").toString())
                .password("")
                .authorities(claims.get("roles").toString())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    public boolean isAdmin(){
        LOGGER.info("isAdmin() - start");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info("isAdmin() - end");
        return auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().contains(Role.ROLE_ADMIN.getAuthority()));
    }

    public boolean userAllowedToChangePassword(){
        LOGGER.info("userAllowedToChangePassword() - start");
        User currentUser = getCurrentUser();
        LOGGER.info("userAllowedToChangePassword() - end");
        return currentUser.getChangePassword();
    }

    public User getCurrentUser(){
        LOGGER.info("getCurrentUser() - start");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        LOGGER.info("getCurrentUser() - end");
        return userRepository.findByUsername(username);
    }

    public String refresh(String username) {
        LOGGER.info("refresh() - start");
        LOGGER.info("refresh() - end");
        return jwtTokenProvider.createToken(userRepository.findByUsername(username));
    }
}
