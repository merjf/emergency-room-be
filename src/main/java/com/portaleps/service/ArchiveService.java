package com.portaleps.service;

import com.portaleps.dto.ArchiveDTO;
import com.portaleps.dto.ResponseDto;
import com.portaleps.helper.UserHelper;
import com.portaleps.model.entity.Archive;
import com.portaleps.model.entity.User;
import com.portaleps.model.entity.UserArchive;
import com.portaleps.repository.ArchiveRepository;
import com.portaleps.repository.UserArchiveRepository;
import com.portaleps.model.response.ArchiveResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ArchiveService {

    private static final Logger LOGGER = LogManager.getLogger(ArchiveService.class);

    @Autowired
    private ArchiveRepository archiveRepository;

    @Autowired
    private UserArchiveRepository userArchiveRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserHelper userHelper;

    public Archive getArchiveById(Integer id){
        return archiveRepository.getById(id);
    }

    public ArchiveResponse archiveDtoToResponse(ArchiveDTO archiveDTO){
        LOGGER.info("archiveDtoToResponse() - start");
        LOGGER.info("archiveDtoToResponse() - end");
        return new ArchiveResponse(new ResponseDto(), Collections.singletonList(archiveDTO));
    }

    public ArchiveDTO getArchiveDTO(Integer id){
        LOGGER.info("getArchiveDTO() - start");
        Optional<Archive> archive = archiveRepository.findById(id);
        if(archive.isPresent()){
            LOGGER.info("getArchiveDTO() - archive found: " + archive.get().getName());
            LOGGER.info("getArchiveDTO() - end");
            return toDto(archive.get());
        } else {
            LOGGER.error("getArchiveDTO() - archive not found: " + id);
            LOGGER.info("getArchiveDTO() - end");
            return null;
        }
    }

    public List<ArchiveDTO> getArchivesByUser(Optional<User> user){
        LOGGER.info("getArchivesByUser() - start");
        List<ArchiveDTO> archiveDTOS = new ArrayList<>();
        List<UserArchive> userArchives = new ArrayList<>();
        if(user.isPresent()){
             userArchives = userArchiveRepository.findByUser(user.get());
        } else {
            userArchives = userArchiveRepository.findByUser(userHelper.getCurrentUser());
        }
        userArchives.stream().forEach( userArchive -> {
            archiveDTOS.add(toDto(userArchive.getArchive()));
        });
        LOGGER.info("getArchivesByUser() - end");
        return archiveDTOS;
    }

    @Transactional
    public UserArchive createUserAccessToArchives(User user, Archive archive){
        LOGGER.info("getArchivesByUser() - start");
        if(user != null && archive != null){
            if(userArchiveRepository.findByUserAndArchive(user, archive) == null){
                UserArchive userArchive = new UserArchive();
                userArchive.setUser(user);
                userArchive.setArchive(archive);
                userArchiveRepository.saveAndFlush(userArchive);
                return userArchive;
            }
        }
        return null;
    }

    @Transactional
    public void clearOldAccessToArchives(User user, List<Integer> newArchives){
        List<UserArchive> oldArchives = userArchiveRepository.findOldArchives(user, newArchives);
        for(UserArchive userArchive : oldArchives){
            userArchiveRepository.delete(userArchive);
            userArchiveRepository.flush();
        }
    }

    public ArchiveDTO toDto(Archive archive) {
        return modelMapper.map(archive, ArchiveDTO.class);
    }

    public Archive toEntity(ArchiveDTO archiveDTO) {
        return modelMapper.map(archiveDTO, Archive.class);
    }
}
