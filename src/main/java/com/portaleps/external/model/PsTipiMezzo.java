package com.portaleps.external.model;

import javax.persistence.*;

@Table(name = "ps_tipi_mezzo", indexes = {
        @Index(name = "cod_mezzo", columnList = "cod_mezzo"),
        @Index(name = "attivo", columnList = "attivo")
})
@Entity
public class PsTipiMezzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mezzo", nullable = false)
    private Integer id;

    @Column(name = "cod_mezzo", length = 1)
    private String codMezzo;

    @Column(name = "cod_emur", length = 1)
    private String codEmur;

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

    public String getCodEmur() {
        return codEmur;
    }

    public void setCodEmur(String codEmur) {
        this.codEmur = codEmur;
    }

    public String getCodMezzo() {
        return codMezzo;
    }

    public void setCodMezzo(String codMezzo) {
        this.codMezzo = codMezzo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}