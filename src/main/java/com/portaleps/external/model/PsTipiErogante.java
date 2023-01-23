package com.portaleps.external.model;

import javax.persistence.*;

@Table(name = "ps_tipi_erogante", indexes = {
        @Index(name = "cod_erogante", columnList = "cod_erogante"),
        @Index(name = "attivo", columnList = "attivo")
})
@Entity
public class PsTipiErogante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_erogante", nullable = false)
    private Integer id;

    @Column(name = "cod_erogante", length = 2)
    private String codErogante;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "descr_breve", length = 50)
    private String descrBreve;

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

    public String getCodErogante() {
        return codErogante;
    }

    public void setCodErogante(String codErogante) {
        this.codErogante = codErogante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}