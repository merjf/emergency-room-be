package com.portaleps.external.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "ps_accessi_farm", indexes = {
        @Index(name = "annullato", columnList = "annullato"),
        @Index(name = "somm_uid", columnList = "somm_uid"),
        @Index(name = "rich_uid", columnList = "rich_uid"),
        @Index(name = "rifiutato", columnList = "rifiutato"),
        @Index(name = "id_articolo", columnList = "id_articolo"),
        @Index(name = "id_accesso", columnList = "id_accesso")
})
@Entity
public class PsAccessiFarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_farmaco", nullable = false)
    private Long id;

    @Column(name = "id_accesso")
    private Long idAccesso;

    @Column(name = "id_articolo")
    private Integer idArticolo;

    @Column(name = "qnt")
    private Integer qnt;

    @Column(name = "rich_uid", length = 20)
    private String richUid;

    @Column(name = "rich_data")
    private LocalDateTime richData;

    @Column(name = "rich_note")
    private String richNote;

    @Column(name = "rich_ins_data")
    private LocalDateTime richInsData;

    @Column(name = "rich_ins_ip", length = 15)
    private String richInsIp;

    @Column(name = "rich_somm", length = 20)
    private String richSomm;

    @Column(name = "somm_uid", length = 20)
    private String sommUid;

    @Column(name = "somm_data")
    private LocalDateTime sommData;

    @Column(name = "somm_note")
    private String sommNote;

    @Column(name = "somm_ins_ip", length = 16)
    private String sommInsIp;

    @Column(name = "somm_ins_data")
    private LocalDateTime sommInsData;

    @Column(name = "rifiutato")
    private Integer rifiutato;

    @Column(name = "annullato")
    private Integer annullato;

    public Integer getAnnullato() {
        return annullato;
    }

    public void setAnnullato(Integer annullato) {
        this.annullato = annullato;
    }

    public Integer getRifiutato() {
        return rifiutato;
    }

    public void setRifiutato(Integer rifiutato) {
        this.rifiutato = rifiutato;
    }

    public LocalDateTime getSommInsData() {
        return sommInsData;
    }

    public void setSommInsData(LocalDateTime sommInsData) {
        this.sommInsData = sommInsData;
    }

    public String getSommInsIp() {
        return sommInsIp;
    }

    public void setSommInsIp(String sommInsIp) {
        this.sommInsIp = sommInsIp;
    }

    public String getSommNote() {
        return sommNote;
    }

    public void setSommNote(String sommNote) {
        this.sommNote = sommNote;
    }

    public LocalDateTime getSommData() {
        return sommData;
    }

    public void setSommData(LocalDateTime sommData) {
        this.sommData = sommData;
    }

    public String getSommUid() {
        return sommUid;
    }

    public void setSommUid(String sommUid) {
        this.sommUid = sommUid;
    }

    public String getRichSomm() {
        return richSomm;
    }

    public void setRichSomm(String richSomm) {
        this.richSomm = richSomm;
    }

    public String getRichInsIp() {
        return richInsIp;
    }

    public void setRichInsIp(String richInsIp) {
        this.richInsIp = richInsIp;
    }

    public LocalDateTime getRichInsData() {
        return richInsData;
    }

    public void setRichInsData(LocalDateTime richInsData) {
        this.richInsData = richInsData;
    }

    public String getRichNote() {
        return richNote;
    }

    public void setRichNote(String richNote) {
        this.richNote = richNote;
    }

    public LocalDateTime getRichData() {
        return richData;
    }

    public void setRichData(LocalDateTime richData) {
        this.richData = richData;
    }

    public String getRichUid() {
        return richUid;
    }

    public void setRichUid(String richUid) {
        this.richUid = richUid;
    }

    public Integer getQnt() {
        return qnt;
    }

    public void setQnt(Integer qnt) {
        this.qnt = qnt;
    }

    public Integer getIdArticolo() {
        return idArticolo;
    }

    public void setIdArticolo(Integer idArticolo) {
        this.idArticolo = idArticolo;
    }

    public Long getIdAccesso() {
        return idAccesso;
    }

    public void setIdAccesso(Long idAccesso) {
        this.idAccesso = idAccesso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}