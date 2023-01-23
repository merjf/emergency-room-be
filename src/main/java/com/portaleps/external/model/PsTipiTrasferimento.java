package com.portaleps.external.model;

import javax.persistence.*;

@Table(name = "ps_tipi_trasferimento", indexes = {
        @Index(name = "cod_trasferimento", columnList = "cod_trasferimento"),
        @Index(name = "attivo", columnList = "attivo")
})
@Entity
public class PsTipiTrasferimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trasferimento", nullable = false)
    private Integer id;

    @Column(name = "cod_trasferimento", length = 1)
    private String codTrasferimento;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "attivo")
    private Boolean attivo;

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCodTrasferimento() {
        return codTrasferimento;
    }

    public void setCodTrasferimento(String codTrasferimento) {
        this.codTrasferimento = codTrasferimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}