package com.portaleps.dto;

import com.portaleps.model.entity.Role;

import java.util.List;

public class UserDTO {

    private Integer id;
    private String username;
    private String email;
    private String name;
    private String surname;
    List<Role> roles;
    List<ArchiveDTO> archives;
    private String token;
    private boolean active;
    private boolean changePassword;

    public UserDTO(){}

    public UserDTO(Integer id, String username, String email, List<Role> roles, List<ArchiveDTO> archives) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.archives = archives;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<ArchiveDTO> getArchives() {
        return archives;
    }

    public void setArchives(List<ArchiveDTO> archives) {
        this.archives = archives;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isChangePassword() {
        return changePassword;
    }

    public void setChangePassword(boolean changePassword) {
        this.changePassword = changePassword;
    }
}