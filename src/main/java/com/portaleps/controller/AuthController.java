package com.portaleps.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.portaleps.dto.UserDTO;
import com.portaleps.helper.UserHelper;
import com.portaleps.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public UserDTO login(@RequestBody ObjectNode body) {
        LOGGER.info("login() - start");
        LOGGER.info("Richieta con body [" + body.toString() + "]");
        if(!body.isEmpty() && body.has("username") && body.has("password")){
            String username = body.get("username").asText();
            String password = body.get("password").asText();
            String token = userHelper.login(username, password);
            UserDTO userDTO = userService.getUserDTO(username);
            userDTO.setToken(token);
            LOGGER.info("login() - login success");
            LOGGER.info("login() - end");
            return userDTO;
        } else {
            LOGGER.warn("login() - login failed");
            LOGGER.info("login() - end");
            return new UserDTO();
        }
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_RESEARCHER')")
    public String refresh(HttpServletRequest req) {
        return userHelper.refresh(req.getRemoteUser());
    }
}
