package com.portaleps.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.portaleps.dto.VerbaleDTO;
import com.portaleps.exception.CustomException;
import com.portaleps.helper.*;
import com.portaleps.model.*;
import com.portaleps.model.entity.Archive;
import com.portaleps.model.response.PaginationResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

@Service
public class VerbaleService {

    private static final Logger LOGGER = LogManager.getLogger(VerbaleService.class);

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private PrinterHelper printerHelper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserHelper userHelper;

    public void getVerbali(Archive archive, ObjectNode filters, ArrayNode pagination, Consumer<List<VerbaleDTO>> getVerbali){
        LOGGER.info("getVerbali() - start");
        if(archive != null){
            databaseHelper.performQuery(archive, QueryBuilder.buildSearchQuery(filters, pagination), filters, resultSet -> {
                getVerbali.accept(extractVerbaliList(resultSet));
            });
        }
        LOGGER.info("getVerbali() - end");
    }

    public int getVerbaliLength(Archive archive, ObjectNode filters){
        LOGGER.info("getVerbaliLength() - start");
        ArrayList<Integer> count = new ArrayList<>();
        databaseHelper.performQuery(archive, QueryBuilder.buildCountRows(filters), filters, resultSet -> {
            try{
                if(resultSet.next()){
                    count.add(resultSet.getInt("tot_accessi"));
                } else {
                    count.add(0);
                }
            } catch (Exception e){
                LOGGER.error("getVerbaliLength() - Invalid result set. Filters: " + filters.toString());
                LOGGER.info("getVerbaliLength() - end");
                throw new CustomException("Invalid result set. Filters: " + filters, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        });
        LOGGER.info("getVerbaliLength() - end");
        return count.get(0);
    }

    public Resource printVerbale(Archive archive, Long numeroAccesso){
        LOGGER.info("printVerbale() - start");
        Resource resource = printerHelper.printPdf(packageDocument(archive, numeroAccesso));
        LOGGER.info("printVerbale() - end");
        return resource;
    }

    public PaginationResponse populatePagination(ArrayNode pagination, int count){
        LOGGER.info("populatePagination() - start");
        if(!pagination.isEmpty() && pagination.size() == 2){
            LOGGER.info("populatePagination() - end");
            return new PaginationResponse(pagination.get(0).asInt(), pagination.get(1).asInt(), count);
        }
        else{
            LOGGER.info("populatePagination() - end");
            return new PaginationResponse();
        }
    }

    // PRIVATE METHODS

    private Document packageDocument(Archive archive, Long numeroAccesso){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd, HH:MM").withLocale(Locale.ITALY);

        ObjectNode filters = objectMapper.createObjectNode();
        filters.put("numeroAccesso", numeroAccesso);

        Document document = new Document();
        document.setNomeDottore(userHelper.getCurrentUser().getUsername());
        document.setNomePresidio(archive.getName());
        document.setPrintingTime(LocalDateTime.now().format(formatter));

        databaseHelper.performQuery(archive, QueryBuilder.buildSearchPaziente(), filters, resultSet -> {
            document.setPaziente(extractPaziente(resultSet));
        });

        databaseHelper.performQuery(archive, QueryBuilder.buildSearchAccettazioneTriage(), filters, resultSet -> {
            document.setAccettazioneTriage(extractAccettazioneTriage(resultSet));
        });

        databaseHelper.performQuery(archive, QueryBuilder.buildSearchCronologiaTriage(), filters, resultSet -> {
            document.setCronologiaTriage(extractCronologiaTriage(resultSet));
        });

        databaseHelper.performQuery(archive, QueryBuilder.buildSearchCondizioniCliniche(), filters, resultSet -> {
            document.setCronologiaCondizioniCliniche(extractCronologiaCondizioniCliniche(resultSet));
        });

        databaseHelper.performQuery(archive, QueryBuilder.buildSearchAccettazioniMedici(), filters, resultSet -> {
            document.setCronologiaAccettazioneMedico(extractCronologiaAccettazioniMedici(resultSet));
        });

        databaseHelper.performQuery(archive, QueryBuilder.buildSearchIterDiagnostico(), filters, resultSet -> {
            document.setIterDiagnostico(extractIterDiagnostico(resultSet));
        });

        databaseHelper.performQuery(archive, QueryBuilder.buildSearchSomministrazioni(), filters, resultSet -> {
            document.setSomministrazioni(extractFarmaciSomministrati(resultSet));
        });

        databaseHelper.performQuery(archive, QueryBuilder.buildSearchEsitoDimissione(), filters, resultSet -> {
            document.setEsitoDimissione(extractEsitoDimissione(resultSet));
        });

        return document;
    }

    private List<VerbaleDTO> extractVerbaliList(ResultSet resultSet) {
        LOGGER.info("extractVerbaliList() - start");
        List<VerbaleDTO> verbali = new ArrayList<>();
        try {
            while (resultSet.next()) {
                verbali.add(new CustomRowMapper().mapRowToVerbale(resultSet, resultSet.getRow()));
            }
        } catch (SQLException e) {
            LOGGER.error("Wrong result set");
            LOGGER.info("extractVerbaliList() - end");
            throw new CustomException("Wrong result set", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("extractVerbaliList() - end");
        return verbali;
    }

    private Paziente extractPaziente(ResultSet resultSet){
        LOGGER.info("extractPaziente() - start");
        try {
            while (resultSet.next()) {
                return new CustomRowMapper().mapRowToPaziente(resultSet, resultSet.getRow());
            }
        } catch (SQLException e) {
            LOGGER.error("Wrong result set");
            LOGGER.info("extractPaziente() - end");
            throw new CustomException("Wrong result set", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("extractPaziente() - end");
        return new Paziente();
    }

    private AccettazioneTriage extractAccettazioneTriage(ResultSet resultSet){
        LOGGER.info("extractAccettazioneTriage() - start");
        try {
            while (resultSet.next()) {
                return new CustomRowMapper().mapRowToAccettazioneTriage(resultSet, resultSet.getRow());
            }
        } catch (SQLException e) {
            LOGGER.error("Wrong result set");
            LOGGER.info("extractAccettazioneTriage() - end");
            throw new CustomException("Wrong result set", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("extractAccettazioneTriage() - end");
        return new AccettazioneTriage();
    }

    private List<Triage> extractCronologiaTriage(ResultSet resultSet){
        LOGGER.info("extractCronologiaTriage() - start");
        List<Triage> triage = new ArrayList<>();
        try {
            while (resultSet.next()) {
                triage.add(new CustomRowMapper().mapRowToTriage(resultSet, resultSet.getRow()));
            }
        } catch (SQLException e) {
            LOGGER.error("Wrong result set");
            LOGGER.info("extractCronologiaTriage() - end");
            throw new CustomException("Wrong result set", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("extractCronologiaTriage() - end");
        return triage;
    }

    private List<CondizioneClinica> extractCronologiaCondizioniCliniche(ResultSet resultSet){
        LOGGER.info("extractCronologiaCondizioniCliniche() - start");
        List<CondizioneClinica> condizioniCliniche = new ArrayList<>();
        try {
            while (resultSet.next()) {
                condizioniCliniche.add(new CustomRowMapper().mapRowToCondizioneClinica(resultSet, resultSet.getRow()));
            }
        } catch (SQLException e) {
            LOGGER.error("Wrong result set");
            LOGGER.info("extractCronologiaCondizioniCliniche() - end");
            throw new CustomException("Wrong result set", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("extractCronologiaCondizioniCliniche() - end");
        return condizioniCliniche;
    }

    private List<AccettazioneMedico> extractCronologiaAccettazioniMedici(ResultSet resultSet){
        LOGGER.info("extractCronologiaAccettazioniMedici() - start");
        List<AccettazioneMedico> accettazioniMedici = new ArrayList<>();
        try {
            while (resultSet.next()) {
                accettazioniMedici.add(new CustomRowMapper().mapRowToAccettazioneMedico(resultSet, resultSet.getRow()));
            }
        } catch (SQLException e) {
            LOGGER.error("Wrong result set");
            LOGGER.info("extractCronologiaAccettazioniMedici() - end");
            throw new CustomException("Wrong result set", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("extractCronologiaAccettazioniMedici() - end");
        return accettazioniMedici;
    }

    private List<IterDiagnostico> extractIterDiagnostico(ResultSet resultSet){
        LOGGER.info("extractIterDiagnostico() - start");
        List<IterDiagnostico> iterDiagnostico = new ArrayList<>();
        try {
            while (resultSet.next()) {
                iterDiagnostico.add(new CustomRowMapper().mapRowToIterDiagnostico(resultSet, resultSet.getRow()));
            }
        } catch (SQLException e) {
            LOGGER.error("Wrong result set");
            LOGGER.info("extractIterDiagnostico() - end");
            throw new CustomException("Wrong result set", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("extractIterDiagnostico() - end");
        return iterDiagnostico;
    }

    private List<FarmacoSomministrato> extractFarmaciSomministrati(ResultSet resultSet){
        LOGGER.info("extractFarmaciSomministrati() - start");
        List<FarmacoSomministrato> somministrazioni = new ArrayList<>();
        try {
            while (resultSet.next()) {
                somministrazioni.add(new CustomRowMapper().mapRowToFarmacoSomministrato(resultSet, resultSet.getRow()));
            }
        } catch (SQLException e) {
            LOGGER.error("Wrong result set");
            LOGGER.info("extractFarmaciSomministrati() - end");
            throw new CustomException("Wrong result set", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("extractFarmaciSomministrati() - end");
        return somministrazioni;
    }

    private EsitoDimissione extractEsitoDimissione(ResultSet resultSet){
        LOGGER.info("extractEsitoDimissione() - start");
        try {
            while (resultSet.next()) {
                return new CustomRowMapper().mapRowToEsitoDimissione(resultSet, resultSet.getRow());
            }
        } catch (SQLException e) {
            LOGGER.error("Wrong result set");
            LOGGER.info("extractEsitoDimissione() - end");
            throw new CustomException("Wrong result set", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("extractEsitoDimissione() - end");
        return new EsitoDimissione();
    }
}
