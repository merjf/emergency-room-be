package com.portaleps.external.model;

import javax.persistence.*;

@Table(name = "ps_tipi_problema_dett", indexes = {
        @Index(name = "cod_problema_dett", columnList = "cod_problema_dett"),
        @Index(name = "id_problema", columnList = "id_problema"),
        @Index(name = "attivo", columnList = "attivo")
})
@Entity
public class PsTipiProblemaDett {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_problema_dett", nullable = false)
    private Integer id;

    @Column(name = "id_problema")
    private Integer idProblema;

    @Column(name = "cod_problema_dett", length = 1)
    private String codProblemaDett;

    @Column(name = "cod_emur", length = 1)
    private String codEmur;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "attivo")
    private Integer attivo;

    public Integer getAttivo() {
        return attivo;
    }

    public void setAttivo(Integer attivo) {
        this.attivo = attivo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCodEmur() {
        return codEmur;
    }

    public void setCodEmur(String codEmur) {
        this.codEmur = codEmur;
    }

    public String getCodProblemaDett() {
        return codProblemaDett;
    }

    public void setCodProblemaDett(String codProblemaDett) {
        this.codProblemaDett = codProblemaDett;
    }

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(Integer idProblema) {
        this.idProblema = idProblema;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}