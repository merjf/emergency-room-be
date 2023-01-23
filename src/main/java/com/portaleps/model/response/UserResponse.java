package com.portaleps.model.response;

import com.portaleps.dto.ResponseDto;
import com.portaleps.dto.UserDTO;

import java.util.List;

public class UserResponse {

    private ResponseDto responseDto;
    private List<UserDTO> users;

    public UserResponse(){}

    public UserResponse(ResponseDto responseDto, List<UserDTO> users){
        this.responseDto = responseDto;
        this.users = users;
    }

    public ResponseDto getResponseDto() {
        return responseDto;
    }

    public void setResponseDto(ResponseDto responseDto) {
        this.responseDto = responseDto;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
