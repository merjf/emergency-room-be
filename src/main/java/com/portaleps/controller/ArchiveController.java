package com.portaleps.controller;

import com.portaleps.dto.ArchiveDTO;
import com.portaleps.dto.ResponseDto;
import com.portaleps.helper.UserHelper;
import com.portaleps.model.response.ArchiveResponse;
import com.portaleps.service.ArchiveService;
import com.portaleps.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/archive")
public class ArchiveController {

    private static final Logger LOGGER = LogManager.getLogger(ArchiveController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private ArchiveService archiveService;

    @GetMapping(value = "{archiveId}")
    public ResponseEntity<ArchiveResponse> getArchive(@PathVariable String archiveId){
        LOGGER.info("getArchive() - start");
        LOGGER.info("Richieta da utente [" + userHelper.getCurrentUser().getUsername() + "] per archivio con id [" + archiveId + "]");
        ArchiveResponse archiveResponse = new ArchiveResponse();
        if(userService.preliminaryChecks(Integer.valueOf(archiveId)).getCode() == 200){
            ArchiveDTO archive = archiveService.getArchiveDTO(Integer.valueOf(archiveId));
            archiveResponse.setArchives(Collections.singletonList(archive));
            archiveResponse.setResponseDto(new ResponseDto(true, 200, "Archive found"));
            LOGGER.info("getArchive() - archive found");

        } else {
            archiveResponse.setResponseDto(new ResponseDto(false, 404, "Archive not  found"));
            LOGGER.warn("getArchive() - archive not found");
        }
        LOGGER.info("getArchive() - end");
        return new ResponseEntity(archiveResponse, HttpStatus.valueOf(archiveResponse.getResponseDto().getCode()));
    }

    @GetMapping(value = "/my-archives")
    public ResponseEntity<ArchiveResponse> getMyArchives(){
        LOGGER.info("getMyArchives() - start");
        LOGGER.info("Richieta da utente [" + userHelper.getCurrentUser().getUsername() + "]");
        ArchiveResponse archiveResponse = new ArchiveResponse();
        archiveResponse.setArchives(archiveService.getArchivesByUser(Optional.empty()));
        archiveResponse.setResponseDto(new ResponseDto(true, 200, "Accessible archives"));
        LOGGER.info("getMyArchives() - end");
        return new ResponseEntity(archiveResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<ArchiveResponse> getAllArchives(){
        LOGGER.info("getAllArchives() - start");
        LOGGER.info("Richieta da utente [" + userHelper.getCurrentUser().getUsername() + "]");
        ArchiveResponse archiveResponse = new ArchiveResponse();
        if(userHelper.isAdmin()){
            archiveResponse.setArchives(archiveService.getArchivesByUser(Optional.empty()));
            archiveResponse.setResponseDto(new ResponseDto(true, 200, "Accessible archives"));
            return new ResponseEntity(archiveResponse, HttpStatus.OK);
        }
        LOGGER.info("getAllArchives() - start");
        return new ResponseEntity(new ArchiveResponse(new ResponseDto(false, 401, "User not allowed to perform this action"), new ArrayList<>()), HttpStatus.UNAUTHORIZED);
    }
}
