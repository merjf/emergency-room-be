package com.portaleps.external.model;

import javax.persistence.*;

@Table(name = "tab_istituti", indexes = {
        @Index(name = "comune", columnList = "comune"),
        @Index(name = "codice", columnList = "codice"),
        @Index(name = "cod_regione", columnList = "cod_regione"),
        @Index(name = "provincia", columnList = "provincia"),
        @Index(name = "attivo", columnList = "attivo")
})
@Entity
public class TabIstituti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_istituto", nullable = false)
    private Integer id;

    @Column(name = "cod_regione")
    private String codRegione;

    @Column(name = "codice")
    private String codice;

    @Column(name = "denominazione")
    private String denominazione;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "cap", length = 5)
    private String cap;

    @Column(name = "comune")
    private String comune;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "telefono", length = 25)
    private String telefono;

    @Column(name = "apertura_giorno", length = 2)
    private String aperturaGiorno;

    @Column(name = "apertura_mese", length = 2)
    private String aperturaMese;

    @Column(name = "apertura_anno", length = 4)
    private String aperturaAnno;

    @Column(name = "tipo", length = 2)
    private String tipo;

    @Column(name = "attivo")
    private Integer attivo;

    public Integer getAttivo() {
        return attivo;
    }

    public void setAttivo(Integer attivo) {
        this.attivo = attivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAperturaAnno() {
        return aperturaAnno;
    }

    public void setAperturaAnno(String aperturaAnno) {
        this.aperturaAnno = aperturaAnno;
    }

    public String getAperturaMese() {
        return aperturaMese;
    }

    public void setAperturaMese(String aperturaMese) {
        this.aperturaMese = aperturaMese;
    }

    public String getAperturaGiorno() {
        return aperturaGiorno;
    }

    public void setAperturaGiorno(String aperturaGiorno) {
        this.aperturaGiorno = aperturaGiorno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getCodRegione() {
        return codRegione;
    }

    public void setCodRegione(String codRegione) {
        this.codRegione = codRegione;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}