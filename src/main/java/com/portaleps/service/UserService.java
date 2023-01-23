package com.portaleps.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.portaleps.dto.ArchiveDTO;
import com.portaleps.dto.ResponseDto;
import com.portaleps.dto.UserDTO;
import com.portaleps.helper.UserHelper;
import com.portaleps.model.entity.Archive;
import com.portaleps.model.entity.Role;
import com.portaleps.model.entity.User;
import com.portaleps.repository.UserArchiveRepository;
import com.portaleps.repository.UserRepository;
import com.portaleps.model.response.UserResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService{

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserArchiveRepository userArchiveRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ArchiveService archiveService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserHelper userHelper;

    public ResponseDto preliminaryChecks(Integer archiveId){
        LOGGER.info("preliminaryChecks() - start");

        User user = userHelper.getCurrentUser();
        if(user != null && archiveId != null){
            Archive archive = archiveService.getArchiveById(archiveId);
            if(archive != null){
                if(userHasAccessToArchive(user, archive)){
                    LOGGER.info("preliminaryChecks() - end");
                    return new ResponseDto(true, 200, "User allowed");
                } else {
                    LOGGER.info("preliminaryChecks() - end");
                    return new ResponseDto(false, 401, "Archive not accessible by this user");
                }
            } else {
                LOGGER.info("preliminaryChecks() - end");
                return new ResponseDto(false, 404, "Archive not found");
            }
        } else {
            LOGGER.info("preliminaryChecks() - end");
            return new ResponseDto(false, 404, "User not found or archive not present");
        }
    }

    @Transactional
    public UserResponse createOrModifyUser(ObjectNode body) {
        LOGGER.info("createNewUser() - start");
        UserResponse userResponse = new UserResponse();
        if(!body.isEmpty() && checkIntegrityBody(body)){
            if (!userRepository.existsByUsername(body.get("username").asText())) {
                User user = storeUser(body, new User(), false);
                UserDTO userDTO = toDto(user);
                userDTO.setArchives(storeAccessToArchives(body, user));
                userResponse.setUsers(Collections.singletonList(userDTO));
                userResponse.setResponseDto(new ResponseDto(true, 200, "User has been created successfully"));
                LOGGER.info("createNewUser() - user created" + userDTO.toString());
            } else {
                User user = storeUser(body, userRepository.findByUsername(body.get("username").asText()), true);
                UserDTO userDTO = toDto(user);
                userDTO.setArchives(storeAccessToArchives(body, user));
                userResponse.setUsers(Collections.singletonList(userDTO));
                userResponse.setResponseDto(new ResponseDto(true, 200, "User has been modified successfully"));
                LOGGER.info("createNewUser() - user modified" + userDTO);
            }
        } else {
            userResponse.setUsers(new ArrayList<>());
            userResponse.setResponseDto(new ResponseDto(false, 400, "Parameters not valid"));
            LOGGER.error("createNewUser() - paramters not valid: " + body);
        }
        LOGGER.info("createNewUser() - end");
        return userResponse;
    }

    @Transactional
    public UserResponse updatePassword(String password) {
        LOGGER.info("updatePassword() - start");
        User currentUser = userHelper.getCurrentUser();
        currentUser.setPassword(passwordEncoder.encode(password));
        currentUser.setChangePassword(false);
        userRepository.saveAndFlush(currentUser);
        UserDTO userDTO = toDto(currentUser);
        userDTO.setArchives(archiveService.getArchivesByUser(Optional.of(currentUser)));
        LOGGER.info("updatePassword() - end");
        return new UserResponse(new ResponseDto(true, 200, "Password updated"), List.of(userDTO));
    }

    public List<UserDTO> getUserList(){
        LOGGER.info("getUserList() - start");
        List<UserDTO> users = new ArrayList<>();
        userRepository.findAll()
                .stream()
                .forEach(user -> {
                    UserDTO userDTO = toDto(user);
                    userDTO.setArchives(archiveService.getArchivesByUser(Optional.of(user)));
                    users.add(userDTO);
                });
        LOGGER.info("getUserList() - end");
        return users;
    }

    public UserDTO getUserDTO(String username){
        LOGGER.info("getUserDTO() - start");
        if(userRepository.existsByUsername(username)){
            User user = userRepository.findByUsername(username);
            UserDTO userDTO = toDto(user);
            userDTO.setArchives(archiveService.getArchivesByUser(Optional.of(user)));
            return userDTO;
        }
        LOGGER.info("getUserDTO() - end");
        return new UserDTO();
    }

    public void delete(String username) {
        LOGGER.info("delete() - start");
        userRepository.deleteByUsername(username);
        LOGGER.info("delete() - end");
    }

    public UserDTO toDto(User user) {
        LOGGER.info("toDto() - start");
        LOGGER.info("toDto() - end");
        return modelMapper.map(user, UserDTO.class);
    }

    public User toEntity(UserDTO userDTO) {
        LOGGER.info("toDto() - start");
        LOGGER.info("toDto() - end");
        return modelMapper.map(userDTO, User.class);
    }

    // PRIVATE METHODS

    private User storeUser(ObjectNode body, User user, boolean update){
        LOGGER.info("storeUser() - start");
        user.setEmail(body.get("email").asText());
        user.setName(body.get("name").asText());
        user.setSurname(body.get("surname").asText());
        user.setUsername(body.get("username").asText());
        user.setChangePassword(true);
        List<Role> roles = new ArrayList<>();
        roles.add(Role.valueOf(body.get("roles").asText()));
        user.setRoles(roles);
        if(update){
            if(!body.get("password").asText().equalsIgnoreCase("********")){
                user.setPassword(passwordEncoder.encode(body.get("password").asText()));
            }
        } else {
            user.setPassword(passwordEncoder.encode(body.get("password").asText()));
        }
        user.setActive(body.get("active").asBoolean());
        userRepository.saveAndFlush(user);
        LOGGER.info("storeUser() - end");
        return user;
    }

    private List<ArchiveDTO> storeAccessToArchives(ObjectNode body, User user){
        LOGGER.info("storeAccessToArchives() - start");
        List<ArchiveDTO> archives = new ArrayList<>();
        List<Integer> archiveIds = new ArrayList<>();
        for (JsonNode archiveItem : body.get("archives")) {
            Archive archive = archiveService.getArchiveById(archiveItem.get("id").asInt());
            archiveService.createUserAccessToArchives(user, archive);
            archives.add(archiveService.toDto(archive));
            archiveIds.add(archive.getId());
        }
        archiveService.clearOldAccessToArchives(user, archiveIds);
        LOGGER.info("storeAccessToArchives() - end");
        return archives;
    }

    private boolean checkIntegrityBody(ObjectNode body) {
        LOGGER.info("checkIntegrityBody() - start");
        LOGGER.info("checkIntegrityBody() - end");
        return body.has("username") && body.has("password") && body.has("email") &&
                body.has("roles") && body.has("archives") && body.has("active") &&
                body.has("name") && body.has("surname");
    }

    private boolean userHasAccessToArchive(User user, Archive archive){
        LOGGER.info("userHasAccessToArchive() - start");
        LOGGER.info("userHasAccessToArchive() - end");
        return userArchiveRepository.findByUserAndArchive(user, archive) != null;
    }

    private Role mapRole(int index){
        LOGGER.info("mapRole() - start");
        Role role;
        switch (index){
            case 0:
                role = Role.ROLE_ADMIN;
            case 1:
                role = Role.ROLE_RESEARCHER;
            default:
                role = null;
        }
        LOGGER.info("mapRole() - end");
        return role;
    }
}
