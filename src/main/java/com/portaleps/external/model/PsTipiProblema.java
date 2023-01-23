package com.portaleps.external.model;

import javax.persistence.*;

@Table(name = "ps_tipi_problema", indexes = {
        @Index(name = "cod_problema", columnList = "cod_problema"),
        @Index(name = "obbligo_dett", columnList = "obbligo_dett"),
        @Index(name = "attivo", columnList = "attivo")
})
@Entity
public class PsTipiProblema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_problema", nullable = false)
    private Integer id;

    @Column(name = "cod_problema", length = 6)
    private String codProblema;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "note")
    private String note;

    @Column(name = "obbligo_dett")
    private Boolean obbligoDett;

    @Column(name = "attivo")
    private Boolean attivo;

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public Boolean getObbligoDett() {
        return obbligoDett;
    }

    public void setObbligoDett(Boolean obbligoDett) {
        this.obbligoDett = obbligoDett;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCodProblema() {
        return codProblema;
    }

    public void setCodProblema(String codProblema) {
        this.codProblema = codProblema;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}