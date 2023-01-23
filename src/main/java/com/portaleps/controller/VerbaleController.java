package com.portaleps.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.portaleps.dto.ResponseDto;
import com.portaleps.dto.VerbaleDTO;
import com.portaleps.exception.CustomException;
import com.portaleps.helper.UserHelper;
import com.portaleps.model.entity.Archive;
import com.portaleps.model.response.VerbaleResponse;
import com.portaleps.service.ArchiveService;
import com.portaleps.service.UserService;
import com.portaleps.service.VerbaleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/verbale")
public class VerbaleController {

    private static final Logger LOGGER = LogManager.getLogger(VerbaleController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VerbaleService verbaleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private ArchiveService archiveService;

    @PostMapping(value = "/all")
    public ResponseEntity<VerbaleResponse> getAll(@RequestParam Integer archiveId, @RequestBody ObjectNode body){
        LOGGER.info("getAll() - start");
        LOGGER.info("Richieta da utente [" + userHelper.getCurrentUser().getUsername() + "] per archivio: [" + archiveId + "] con parametri: [" + body.toString() + "]");
        VerbaleResponse verbaleResponse = new VerbaleResponse();
        if(userService.preliminaryChecks(archiveId).getCode() == 200) {
            Archive archive = archiveService.getArchiveById(archiveId);
            ObjectNode filters = body.has("filters") ? (ObjectNode) body.get("filters") : objectMapper.createObjectNode();
            ArrayNode pagination = body.has("pagination") ? (ArrayNode) body.get("pagination") : objectMapper.createArrayNode();
            verbaleResponse.setPagination(verbaleService.populatePagination(pagination, verbaleService.getVerbaliLength(archive, filters)));
            verbaleService.getVerbali(archive, filters, pagination, verbali -> {
                for(VerbaleDTO verbale : verbali) {
                    verbaleResponse.getVerbali().add(verbale);
                }
            });
            verbaleResponse.setResponse(new ResponseDto(true, 200, "Verbali list"));
            LOGGER.info("getAll() - action performed");
        } else {
            verbaleResponse.setVerbali(new ArrayList<>());
            verbaleResponse.setResponse(new ResponseDto(false, 401, "User not authorized"));
            LOGGER.warn("getAll() - User not authorized");
        }
        LOGGER.info("getAll() - end");
        return new ResponseEntity(verbaleResponse, HttpStatus.valueOf(verbaleResponse.getResponse().getCode()));
    }

    @PostMapping(value = "/print")
    public ResponseEntity<Resource> printVerbale(@RequestParam String archiveId,@RequestParam Long numeroAccesso,
                                                 HttpServletRequest request){
        LOGGER.info("printVerbale() - start");
        LOGGER.info("Richieta da utente [" + userHelper.getCurrentUser().getUsername() + "] per archivio: [" + archiveId + "] per verbale: [" + numeroAccesso + "]");
        if(userService.preliminaryChecks(Integer.valueOf(archiveId)).getCode() == 200) {
            Archive archive = archiveService.getArchiveById(Integer.valueOf(archiveId));
            Resource resource = verbaleService.printVerbale(archive, numeroAccesso);
            if (resource.exists()) {
                String contentType = null;
                try {
                    contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
                } catch (IOException ex) {
                    LOGGER.error("printVerbale() - resource not valid", ex);
                    throw new CustomException("Resource not valid - wrong content type", HttpStatus.INTERNAL_SERVER_ERROR);
                }
                // Fallback to the default content type if type could not be determined
                if(contentType == null) {
                    contentType = "application/octet-stream";
                }
                LOGGER.info("printVerbale() - resource printed and returned");
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                LOGGER.warn("printVerbale() - resource not present and not printed");
                return new ResponseEntity(new ResponseDto(false, 400, "PDF not printed"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        LOGGER.info("printVerbale() - end");
        return new ResponseEntity(new ResponseDto(false, 400, "User not allowed to perform this action"), HttpStatus.BAD_REQUEST);
    }
}
