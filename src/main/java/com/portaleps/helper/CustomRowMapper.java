package com.portaleps.helper;

import com.portaleps.dto.VerbaleDTO;

import com.portaleps.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

public class CustomRowMapper implements RowMapper<VerbaleDTO> {

    private final DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("HH:mm").withLocale(Locale.ITALY);
    private final DateTimeFormatter dbSimpleFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.ITALY);
    private final DateTimeFormatter dbFullFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withLocale(Locale.ITALY);
    private final DateTimeFormatter documentSimpleFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.ITALY);
    private final DateTimeFormatter documentFullFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm").withLocale(Locale.ITALY);

    @Override
    public VerbaleDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    public AccettazioneTriage mapRowToAccettazioneTriage(ResultSet rs, int rowNum) throws SQLException {
        
        AccettazioneTriage accettazioneTriage = new AccettazioneTriage();

        StringBuilder dataAccesso = new StringBuilder();
        Optional.ofNullable(rs.getString("data_acc")).ifPresent(s ->{
            dataAccesso.append(LocalDate.parse(s, dbSimpleFormat).format(documentSimpleFormat));
        });
        accettazioneTriage.setDataAccesso(dataAccesso.toString());
        accettazioneTriage.setProblemaAccesso(rs.getString("problema"));
        accettazioneTriage.setNote(rs.getString("note"));
        accettazioneTriage.setCausaAccesso(rs.getString("causa"));
        accettazioneTriage.setMezzoArrivo(rs.getString("mezzo"));
        accettazioneTriage.setTargaMezzo(rs.getString("targa_mezzo"));
        accettazioneTriage.setCircostanze(rs.getString("inc_circ"));
        accettazioneTriage.setDettProblema(rs.getString("dettProblema"));
        accettazioneTriage.setEsenzione(rs.getString("esenzione"));
        accettazioneTriage.setIstitutoProvenienza(rs.getString("istituto"));
        Optional.ofNullable(rs.getString("inc_resp")).ifPresent( s -> {
            accettazioneTriage.setResponsabilità(s.equalsIgnoreCase("0") ? "No" : "Si");
        });
        Optional.ofNullable(rs.getString("inc_data")).ifPresent(s ->{
            accettazioneTriage.setDataEvento(LocalDate.parse(s, dbFullFormat).format(documentSimpleFormat));
        });
        accettazioneTriage.setEroganteServizio(rs.getString("erogante"));
        accettazioneTriage.setLuogoIncidente(rs.getString("inc_luogo"));

        
        return accettazioneTriage;
    }

    public Triage mapRowToTriage(ResultSet rs, int rowNum) throws SQLException {
        
        Triage triage = new Triage();

        triage.setColore(rs.getString("colore"));
        triage.setNomeOperatore(rs.getString("nome") + " " + rs.getString("cognome"));
        triage.setNote(rs.getString("note"));
        StringBuilder dataValutazione = new StringBuilder();
        Optional.ofNullable(rs.getString("val_data")).ifPresent(s ->{
            dataValutazione.append(LocalDate.parse(s, dbSimpleFormat).format(documentSimpleFormat));
        });
        dataValutazione.append(", ");
        dataValutazione.append(LocalTime.parse(rs.getString("val_ora")));
        triage.setDataValutazione(dataValutazione.toString());

        
        return triage;
    }

    public CondizioneClinica mapRowToCondizioneClinica(ResultSet rs, int rowNum) throws SQLException {
        
        CondizioneClinica condizioneClinica = new CondizioneClinica();

        condizioneClinica.setInfo(rs.getString("info"));
        condizioneClinica.setValutatore(rs.getString("nome") + " " + rs.getString("cognome"));
        condizioneClinica.setValore(rs.getString("descrizione"));
        StringBuilder dataValutazione = new StringBuilder();
        Optional.ofNullable(rs.getString("val_data")).ifPresent(s ->{
            dataValutazione.append(LocalDate.parse(s, dbSimpleFormat).format(documentSimpleFormat));
        });
        dataValutazione.append(", ");
        dataValutazione.append(LocalTime.parse(rs.getString("val_ora")));
        condizioneClinica.setData(dataValutazione.toString());

        
        return condizioneClinica;
    }

    public AccettazioneMedico mapRowToAccettazioneMedico(ResultSet rs, int rowNum) throws SQLException {
        
        AccettazioneMedico accettazioneMedico = new AccettazioneMedico();

        accettazioneMedico.setMedico(rs.getString("nome") + " " + rs.getString("cognome"));
        Optional.ofNullable(rs.getString("car_data")).ifPresent(s ->{
            accettazioneMedico.setDataCarico(LocalDate.parse(s, dbFullFormat).format(documentSimpleFormat));
        });
        accettazioneMedico.setNoteCarico(rs.getString("car_note"));
        accettazioneMedico.setNoteCessione(rs.getString("cess_note"));
        Optional.ofNullable(rs.getString("cess_data")).ifPresent(s ->{
            accettazioneMedico.setDataCessione(LocalDate.parse(s, dbFullFormat).format(documentSimpleFormat));
        });

        
        return accettazioneMedico;
    }

    public IterDiagnostico mapRowToIterDiagnostico(ResultSet rs, int rowNum) throws SQLException {
        
        IterDiagnostico iterDiagnostico = new IterDiagnostico();

        Optional.ofNullable(rs.getString("data_richiesta")).ifPresent(s ->{
            iterDiagnostico.setDataRichiesta(LocalDateTime.parse(s, dbFullFormat).format(documentFullFormat));
        });
        iterDiagnostico.setRichiedente(rs.getString("nome") + " " + rs.getString("cognome"));
        iterDiagnostico.setEsame(rs.getString("esame"));

        
        return iterDiagnostico;
    }

    public FarmacoSomministrato mapRowToFarmacoSomministrato(ResultSet rs, int rowNum) throws SQLException {
        
        FarmacoSomministrato farmacoSomministrato = new FarmacoSomministrato();

        farmacoSomministrato.setFarmaco(rs.getString("farmaco"));
        farmacoSomministrato.setRichiedente(rs.getString("nome") + " " + rs.getString("cognome"));
        farmacoSomministrato.setQnt(rs.getString("qnt"));
        farmacoSomministrato.setSomministratore(rs.getString("somm_nome") + " " + rs.getString("somm_cognome"));
        farmacoSomministrato.setNoteSomministrazione(rs.getString("somm_note"));
        Optional.ofNullable(rs.getString("somm_data")).ifPresent(s ->{
            farmacoSomministrato.setDataSomministrazione(LocalDate.parse(s, dbFullFormat).format(documentSimpleFormat));
        });
        farmacoSomministrato.setNoteRichiesta(rs.getString("rich_note"));
        Optional.ofNullable(rs.getString("rich_data")).ifPresent(s ->{
            farmacoSomministrato.setDataRichiesta(LocalDate.parse(s, dbFullFormat).format(documentSimpleFormat));
        });

        
        return farmacoSomministrato;
    }

    public EsitoDimissione mapRowToEsitoDimissione(ResultSet rs, int rowNum) throws SQLException {
        
        EsitoDimissione esitoDimissione = new EsitoDimissione();

        esitoDimissione.setEsito(rs.getString("esito"));
        esitoDimissione.setReparto(rs.getString("reparto"));
        esitoDimissione.setMotivo(rs.getString("motivo"));
        esitoDimissione.setConclusioni(rs.getString("conclusioni"));
        esitoDimissione.setMezzo(rs.getString("mezzo"));
        esitoDimissione.setTargaMezzo(rs.getString("targa_mezzo"));
        esitoDimissione.setIstituto(rs.getString("istituto"));
        esitoDimissione.setPrognosi(rs.getString("prognosi"));
        esitoDimissione.setTerapia(rs.getString("terapia"));
        esitoDimissione.setFollowUp(rs.getString("followup"));
        esitoDimissione.setReferto(rs.getString("referto"));
        esitoDimissione.setDiagnosi(rs.getString("diagnosi"));

        StringBuilder dataDimissione = new StringBuilder();
        Optional.ofNullable(rs.getString("data_dim")).ifPresent(s ->{
            dataDimissione.append(LocalDate.parse(s, dbSimpleFormat).format(documentSimpleFormat));
        });
        dataDimissione.append(", ");
        dataDimissione.append(LocalTime.parse(rs.getString("ora_dim")));
        esitoDimissione.setDataDimissione(dataDimissione.toString());

        String refertiCompilati = "";
        if(!rs.getString("ref_inail").isEmpty() && rs.getString("ref_inail").equalsIgnoreCase("1")){
            refertiCompilati = "INAIL";
        } else if(!rs.getString("ref_giudiz").isEmpty() && rs.getString("ref_giudiz").equalsIgnoreCase("1")){
            refertiCompilati = "Autorità giudiziaria";
        } else if(!rs.getString("ref_morso").isEmpty() && rs.getString("ref_morso").equalsIgnoreCase("1")){
            refertiCompilati = "Morso di Animale";
        } else if(!rs.getString("ref_altro").isEmpty() && rs.getString("ref_altro").equalsIgnoreCase("1")){
            refertiCompilati = "Altro";
        }
        esitoDimissione.setRefertiCompilati(refertiCompilati);

        
        return esitoDimissione;
    }

    public Paziente mapRowToPaziente(ResultSet rs, int rowNum) throws SQLException {
        
        Paziente paziente = new Paziente();

        paziente.setNumeroAccesso(rs.getLong("id_accesso"));
        paziente.setNome(rs.getString("nome"));
        paziente.setCognome(rs.getString("cognome"));
        Optional.ofNullable(rs.getString("data_nascita")).ifPresent(s ->{
            paziente.setDataNascita(LocalDate.parse(s, dbSimpleFormat).format(documentSimpleFormat));
        });
        paziente.setSesso(rs.getString("sesso"));
        paziente.setCodiceFiscale(rs.getString("cod_fisc"));
        paziente.setIndirizzo(rs.getString("indirizzo"));
        paziente.setStatoCivile(rs.getString("stato_civile"));
        paziente.setComuneNascita(rs.getString("cod_comune_nas"));
        paziente.setComuneResidenza(rs.getString("cod_comune_res"));
        paziente.setComuneNascita(rs.getString("nas_comune"));
        paziente.setCodiceIstatNascita(rs.getString("nas_cod_istat"));
        paziente.setComuneResidenza(rs.getString("res_comune"));
        paziente.setProvinciaResidenza(rs.getString("res_provincia"));
        paziente.setCodiceIstatResidenza(rs.getString("res_cod_istat"));
        paziente.setProvinciaNascita(rs.getString("nas_provincia"));
        paziente.setCittadinanza(rs.getString("stato"));
        paziente.setAsl(rs.getString("asl"));
        paziente.setRegioneResidenza(rs.getString("regione"));

        
        return paziente;
    }

    public VerbaleDTO mapRowToVerbale(ResultSet rs, int rowNum) throws SQLException {
        
        VerbaleDTO verbaleDTO = new VerbaleDTO();

        verbaleDTO.setAccesso(rs.getLong("Accesso"));
        verbaleDTO.setColore(rs.getString("Colore"));
        verbaleDTO.setNome(rs.getString("Nome"));
        verbaleDTO.setCognome(rs.getString("Cognome"));
        verbaleDTO.setCodiceFiscale(rs.getString("Codice Fiscale"));
        verbaleDTO.setProblemaAccesso(rs.getString("Problema Accesso"));
        Optional.ofNullable(rs.getString("Data Accesso")).ifPresent(s ->{
            verbaleDTO.setDataAccesso(LocalDate.parse(s, dbSimpleFormat).format(documentSimpleFormat));
        });
        Optional.ofNullable(rs.getString("Ora Accesso")).ifPresent(s ->{
            verbaleDTO.setOraAccesso(LocalTime.parse(s).format(hourFormat));
        });
        verbaleDTO.setEsitoTrattamento(rs.getString("Esito Trattamento"));
        verbaleDTO.setDiagnosiDimissione(rs.getString("Diagnosi"));
        Optional.ofNullable(rs.getString("Data Dimissione")).ifPresent(s ->{
            verbaleDTO.setDataDimissione(LocalDate.parse(s, dbSimpleFormat).format(documentSimpleFormat));
        });
        Optional.ofNullable(rs.getString("Ora Dimissione")).ifPresent(s ->{
            verbaleDTO.setOraDimissione(LocalTime.parse(s).format(hourFormat));
        });

        
        return verbaleDTO;
    }
}