package com.portaleps.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.portaleps.dto.ResponseDto;
import com.portaleps.helper.UserHelper;
import com.portaleps.model.response.UserResponse;
import com.portaleps.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserHelper userHelper;

    //@PreAuthorize("hasRole('ROLE_ADMIN')") // -> throws NOT FOUND - 404
    // So to check the right role there is isAdmin()
    // Should investigate and refactor
    @PostMapping(value = "/create")
    public ResponseEntity<UserResponse> createOrModifyUser(@RequestBody ObjectNode body) {
        LOGGER.info("createNewUser() - start");
        LOGGER.info("Richieta da utente [" + userHelper.getCurrentUser().getUsername() + "] con parametri: [" + body.toString() + "]");
        if(userHelper.isAdmin()){
            UserResponse userResponse = userService.createOrModifyUser(body);
            LOGGER.info("createNewUser() - user created");
            LOGGER.info("createNewUser() - end");
            return new ResponseEntity(userResponse, HttpStatus.valueOf(userResponse.getResponseDto().getCode()));
        }
        LOGGER.warn("createNewUser() - user not allowed to perform this action");
        LOGGER.info("createNewUser() - end");
        return new ResponseEntity(new UserResponse(new ResponseDto(false, 401, "User not allowed to perform this action"), new ArrayList<>()), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "/change-password")
    public ResponseEntity<UserResponse> changePassword(@RequestParam String password) {
        LOGGER.info("changePassword() - start");
        LOGGER.info("Richieta da utente [" + userHelper.getCurrentUser().getUsername() + "] con password: [" + password + "]");
        if(userHelper.userAllowedToChangePassword()){
            UserResponse userResponse = userService.updatePassword(password);
            LOGGER.info("changePassword() - password updated");
            LOGGER.info("changePassword() - end");
            return new ResponseEntity(userResponse, HttpStatus.valueOf(userResponse.getResponseDto().getCode()));
        }
        LOGGER.warn("createNewUser() - user not allowed to perform this action");
        LOGGER.info("createNewUser() - end");
        return new ResponseEntity(new UserResponse(new ResponseDto(false, 401, "User not allowed to perform this action"), new ArrayList<>()), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<ResponseDto> getUserList() {
        LOGGER.info("getUserList() - start");
        LOGGER.info("Richieta da utente [" + userHelper.getCurrentUser().getUsername() + "]");
        UserResponse userResponse = new UserResponse();
        if(userHelper.isAdmin()){
            userResponse.setUsers(userService.getUserList());
            userResponse.setResponseDto(new ResponseDto(true, 200, "User List"));
            LOGGER.info("getUserList() - user list found");
            LOGGER.info("getUserList() - end");
            return new ResponseEntity(userResponse, HttpStatus.OK);
        }
        LOGGER.warn("getUserList() - user not allowed to perform this action");
        LOGGER.info("getUserList() - end");
        return new ResponseEntity(new UserResponse(new ResponseDto(false, 401, "User not allowed to perform this action"), new ArrayList<>()), HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@PathVariable String username) {
        userService.delete(username);
        return username;
    }
}