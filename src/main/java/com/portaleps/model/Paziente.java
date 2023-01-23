package com.portaleps.model;

import java.time.LocalDate;

public class Paziente {

    private Long numeroAccesso;
    private String cognome;
    private String nome;
    private String dataNascita;
    private String sesso;
    private String codiceFiscale;
    private String statoCivile;
    private String comuneNascita;
    private String provinciaNascita;
    private String codiceIstatNascita;
    private String comuneResidenza;
    private String provinciaResidenza;
    private String codiceIstatResidenza;
    private String indirizzo;
    private String regioneResidenza;
    private String cittadinanza;
    private String asl;

    public Long getNumeroAccesso() {
        return numeroAccesso;
    }

    public void setNumeroAccesso(Long numeroAccesso) {
        this.numeroAccesso = numeroAccesso;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getStatoCivile() {
        return statoCivile;
    }

    public void setStatoCivile(String statoCivile) {
        this.statoCivile = statoCivile;
    }

    public String getComuneNascita() {
        return comuneNascita;
    }

    public void setComuneNascita(String comuneNascita) {
        this.comuneNascita = comuneNascita;
    }

    public String getProvinciaNascita() {
        return provinciaNascita;
    }

    public void setProvinciaNascita(String provinciaNascita) {
        this.provinciaNascita = provinciaNascita;
    }

    public String getCodiceIstatNascita() {
        return codiceIstatNascita;
    }

    public void setCodiceIstatNascita(String codiceIstatNascita) {
        this.codiceIstatNascita = codiceIstatNascita;
    }

    public String getComuneResidenza() {
        return comuneResidenza;
    }

    public void setComuneResidenza(String comuneResidenza) {
        this.comuneResidenza = comuneResidenza;
    }

    public String getProvinciaResidenza() {
        return provinciaResidenza;
    }

    public void setProvinciaResidenza(String provinciaResidenza) {
        this.provinciaResidenza = provinciaResidenza;
    }

    public String getCodiceIstatResidenza() {
        return codiceIstatResidenza;
    }

    public void setCodiceIstatResidenza(String codiceIstatResidenza) {
        this.codiceIstatResidenza = codiceIstatResidenza;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getRegioneResidenza() {
        return regioneResidenza;
    }

    public void setRegioneResidenza(String regioneResidenza) {
        this.regioneResidenza = regioneResidenza;
    }

    public String getCittadinanza() {
        return cittadinanza;
    }

    public void setCittadinanza(String cittadinanza) {
        this.cittadinanza = cittadinanza;
    }

    public String getAsl() {
        return asl;
    }

    public void setAsl(String asl) {
        this.asl = asl;
    }
}
