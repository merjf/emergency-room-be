package com.portaleps.external.model;

import javax.persistence.*;

@Table(name = "ps_tipi_esito", indexes = {
        @Index(name = "cod_esito", columnList = "cod_esito"),
        @Index(name = "ricovero", columnList = "ricovero"),
        @Index(name = "attivo", columnList = "attivo")
})
@Entity
public class PsTipiEsito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_esito", nullable = false)
    private Integer id;

    @Column(name = "cod_esito", length = 1)
    private String codEsito;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "descr_breve", length = 50)
    private String descrBreve;

    @Column(name = "ricovero")
    private Integer ricovero;

    @Column(name = "attivo")
    private Integer attivo;

    @Column(name = "frequenza")
    private Integer frequenza;

    public Integer getFrequenza() {
        return frequenza;
    }

    public void setFrequenza(Integer frequenza) {
        this.frequenza = frequenza;
    }

    public Integer getAttivo() {
        return attivo;
    }

    public void setAttivo(Integer attivo) {
        this.attivo = attivo;
    }

    public Integer getRicovero() {
        return ricovero;
    }

    public void setRicovero(Integer ricovero) {
        this.ricovero = ricovero;
    }

    public String getDescrBreve() {
        return descrBreve;
    }

    public void setDescrBreve(String descrBreve) {
        this.descrBreve = descrBreve;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCodEsito() {
        return codEsito;
    }

    public void setCodEsito(String codEsito) {
        this.codEsito = codEsito;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}