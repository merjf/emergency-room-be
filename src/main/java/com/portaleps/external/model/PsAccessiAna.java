package com.portaleps.external.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "ps_accessi_ana", indexes = {
        @Index(name = "id_cittadinanza", columnList = "id_cittadinanza"),
        @Index(name = "stato_civile", columnList = "stato_civile"),
        @Index(name = "cod_comune_res", columnList = "cod_comune_res"),
        @Index(name = "cod_comune_nas", columnList = "cod_comune_nas"),
        @Index(name = "asl", columnList = "asl")
})
@Entity
public class PsAccessiAna {
    @Id
    @Column(name = "id_accesso", nullable = false)
    private Long id;

    @Column(name = "cognome", length = 30)
    private String cognome;

    @Column(name = "nome", length = 30)
    private String nome;

    @Column(name = "data_nascita")
    private LocalDate dataNascita;

    @Column(name = "data_morte")
    private LocalDateTime dataMorte;

    @Column(name = "sesso")
    private Character sesso;

    @Column(name = "cod_fisc", length = 16)
    private String codFisc;

    @Column(name = "cod_team", length = 28)
    private String codTeam;

    @Column(name = "indirizzo", length = 150)
    private String indirizzo;

    @Column(name = "stato_civile")
    private Integer statoCivile;

    @Column(name = "cod_comune_nas", nullable = false, length = 6)
    private String codComuneNas;

    @Column(name = "cod_comune_res", nullable = false, length = 6)
    private String codComuneRes;

    @Column(name = "id_cittadinanza")
    private Integer idCittadinanza;

    @Column(name = "asl")
    private Integer asl;

    @Column(name = "professione")
    private Integer professione;

    @Column(name = "nas_comune", length = 50)
    private String nasComune;

    @Column(name = "nas_provincia", length = 2)
    private String nasProvincia;

    @Column(name = "nas_cod_istat", length = 6)
    private String nasCodIstat;

    @Column(name = "res_comune", length = 50)
    private String resComune;

    @Column(name = "res_provincia", length = 2)
    private String resProvincia;

    @Column(name = "res_cod_istat", length = 6)
    private String resCodIstat;

    @Column(name = "res_id_regione")
    private Integer resIdRegione;

    public Integer getResIdRegione() {
        return resIdRegione;
    }

    public void setResIdRegione(Integer resIdRegione) {
        this.resIdRegione = resIdRegione;
    }

    public String getResCodIstat() {
        return resCodIstat;
    }

    public void setResCodIstat(String resCodIstat) {
        this.resCodIstat = resCodIstat;
    }

    public String getResProvincia() {
        return resProvincia;
    }

    public void setResProvincia(String resProvincia) {
        this.resProvincia = resProvincia;
    }

    public String getResComune() {
        return resComune;
    }

    public void setResComune(String resComune) {
        this.resComune = resComune;
    }

    public String getNasCodIstat() {
        return nasCodIstat;
    }

    public void setNasCodIstat(String nasCodIstat) {
        this.nasCodIstat = nasCodIstat;
    }

    public String getNasProvincia() {
        return nasProvincia;
    }

    public void setNasProvincia(String nasProvincia) {
        this.nasProvincia = nasProvincia;
    }

    public String getNasComune() {
        return nasComune;
    }

    public void setNasComune(String nasComune) {
        this.nasComune = nasComune;
    }

    public Integer getProfessione() {
        return professione;
    }

    public void setProfessione(Integer professione) {
        this.professione = professione;
    }

    public Integer getAsl() {
        return asl;
    }

    public void setAsl(Integer asl) {
        this.asl = asl;
    }

    public Integer getIdCittadinanza() {
        return idCittadinanza;
    }

    public void setIdCittadinanza(Integer idCittadinanza) {
        this.idCittadinanza = idCittadinanza;
    }

    public String getCodComuneRes() {
        return codComuneRes;
    }

    public void setCodComuneRes(String codComuneRes) {
        this.codComuneRes = codComuneRes;
    }

    public String getCodComuneNas() {
        return codComuneNas;
    }

    public void setCodComuneNas(String codComuneNas) {
        this.codComuneNas = codComuneNas;
    }

    public Integer getStatoCivile() {
        return statoCivile;
    }

    public void setStatoCivile(Integer statoCivile) {
        this.statoCivile = statoCivile;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCodTeam() {
        return codTeam;
    }

    public void setCodTeam(String codTeam) {
        this.codTeam = codTeam;
    }

    public String getCodFisc() {
        return codFisc;
    }

    public void setCodFisc(String codFisc) {
        this.codFisc = codFisc;
    }

    public Character getSesso() {
        return sesso;
    }

    public void setSesso(Character sesso) {
        this.sesso = sesso;
    }

    public LocalDateTime getDataMorte() {
        return dataMorte;
    }

    public void setDataMorte(LocalDateTime dataMorte) {
        this.dataMorte = dataMorte;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}