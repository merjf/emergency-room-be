package com.portaleps.model;

import java.util.List;

public class Document {

    private String nomeDottore;
    private String printingTime;
    private String nomePresidio;

    private Paziente paziente;
    private AccettazioneTriage accettazioneTriage;
    private List<Triage> cronologiaTriage;
    private List<CondizioneClinica> cronologiaCondizioniCliniche;
    private List<AccettazioneMedico> cronologiaAccettazioneMedico;
    private List<IterDiagnostico> iterDiagnostico;
    private List<FarmacoSomministrato> somministrazioni;
    private EsitoDimissione esitoDimissione;

    public Paziente getPaziente() {
        return paziente;
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    public AccettazioneTriage getAccettazioneTriage() {
        return accettazioneTriage;
    }

    public void setAccettazioneTriage(AccettazioneTriage accettazioneTriage) {
        this.accettazioneTriage = accettazioneTriage;
    }

    public EsitoDimissione getEsitoDimissione() {
        return esitoDimissione;
    }

    public void setEsitoDimissione(EsitoDimissione esitoDimissione) {
        this.esitoDimissione = esitoDimissione;
    }

    public String getNomeDottore() {
        return nomeDottore;
    }

    public void setNomeDottore(String nomeDottore) {
        this.nomeDottore = nomeDottore;
    }

    public String getPrintingTime() {
        return printingTime;
    }

    public void setPrintingTime(String printingTime) {
        this.printingTime = printingTime;
    }

    public String getNomePresidio() {
        return nomePresidio;
    }

    public void setNomePresidio(String nomePresidio) {
        this.nomePresidio = nomePresidio;
    }

    public List<Triage> getCronologiaTriage() {
        return cronologiaTriage;
    }

    public void setCronologiaTriage(List<Triage> triage) {
        this.cronologiaTriage = triage;
    }

    public List<CondizioneClinica> getCronologiaCondizioniCliniche() {
        return cronologiaCondizioniCliniche;
    }

    public void setCronologiaCondizioniCliniche(List<CondizioneClinica> cronologiaCondizioniCliniche) {
        this.cronologiaCondizioniCliniche = cronologiaCondizioniCliniche;
    }

    public List<AccettazioneMedico> getCronologiaAccettazioneMedico() {
        return cronologiaAccettazioneMedico;
    }

    public void setCronologiaAccettazioneMedico(List<AccettazioneMedico> cronologiaAccettazioneMedico) {
        this.cronologiaAccettazioneMedico = cronologiaAccettazioneMedico;
    }

    public List<FarmacoSomministrato> getSomministrazioni() {
        return somministrazioni;
    }

    public void setSomministrazioni(List<FarmacoSomministrato> somministrazioni) {
        this.somministrazioni = somministrazioni;
    }

    public List<IterDiagnostico> getIterDiagnostico() {
        return iterDiagnostico;
    }

    public void setIterDiagnostico(List<IterDiagnostico> iterDiagnostico) {
        this.iterDiagnostico = iterDiagnostico;
    }
}
