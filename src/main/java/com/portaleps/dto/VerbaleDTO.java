package com.portaleps.dto;

import java.io.Serializable;

public class VerbaleDTO implements Serializable {

    private String colore;
    private Long accesso;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String problemaAccesso;
    private String dataAccesso;
    private String oraAccesso;
    private String dataDimissione;
    private String oraDimissione;
    private String diagnosiDimissione;
    private String esitoTrattamento;

    public VerbaleDTO(){}

    public VerbaleDTO(String colore, Long accesso, String nome, String cognome,
                      String codiceFiscale, String problemaAccesso, String dataAccesso,
                      String oraAccesso, String dataDimissione, String oraDimissione,
                      String diagnosiDimissione, String esitoTrattamento) {
        this.colore = colore;
        this.accesso = accesso;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.problemaAccesso = problemaAccesso;
        this.dataAccesso = dataAccesso;
        this.oraAccesso = oraAccesso;
        this.dataDimissione = dataDimissione;
        this.oraDimissione = oraDimissione;
        this.diagnosiDimissione = diagnosiDimissione;
        this.esitoTrattamento = esitoTrattamento;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public Long getAccesso() {
        return accesso;
    }

    public void setAccesso(Long accesso) {
        this.accesso = accesso;
    }

    public String getProblemaAccesso() {
        return problemaAccesso;
    }

    public void setProblemaAccesso(String problemaAccesso) {
        this.problemaAccesso = problemaAccesso;
    }

    public String getDataAccesso() {
        return dataAccesso;
    }

    public void setDataAccesso(String dataAccesso) {
        this.dataAccesso = dataAccesso;
    }

    public String getDataDimissione() {
        return dataDimissione;
    }

    public void setDataDimissione(String dataDimissione) {
        this.dataDimissione = dataDimissione;
    }

    public String getDiagnosiDimissione() {
        return diagnosiDimissione;
    }

    public void setDiagnosiDimissione(String diagnosiDimissione) {
        this.diagnosiDimissione = diagnosiDimissione;
    }

    public String getEsitoTrattamento() {
        return esitoTrattamento;
    }

    public void setEsitoTrattamento(String esitoTrattamento) {
        this.esitoTrattamento = esitoTrattamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getOraAccesso() {
        return oraAccesso;
    }

    public void setOraAccesso(String oraAccesso) {
        this.oraAccesso = oraAccesso;
    }

    public String getOraDimissione() {
        return oraDimissione;
    }

    public void setOraDimissione(String oraDimissione) {
        this.oraDimissione = oraDimissione;
    }

    @Override
    public String toString(){
        return  "Paziente: " + nome + " " + cognome + " - Codice Fiscale: " + codiceFiscale + " - " +
                "Colore: " + colore + "Problema Accesso: " + problemaAccesso + " - " +
                "Data Accesso: " + dataAccesso + " " + oraAccesso +
                " - Data Dimissione: " + dataDimissione + " " + oraDimissione + " - " +
                "Diagnosi: " + diagnosiDimissione + " - Esito Trattamento: " + esitoTrattamento;
    }
}
