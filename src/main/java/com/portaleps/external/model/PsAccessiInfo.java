package com.portaleps.external.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Table(name = "ps_accessi_info", indexes = {
        @Index(name = "ins_uid", columnList = "ins_uid"),
        @Index(name = "id_info", columnList = "id_info"),
        @Index(name = "id_accesso", columnList = "id_accesso")
})
@Entity
public class PsAccessiInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accesso_info", nullable = false)
    private Integer id;

    @Column(name = "id_info")
    private Integer idInfo;

    @Column(name = "id_accesso")
    private Long idAccesso;

    @Lob
    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "val_data")
    private LocalDate valData;

    @Column(name = "val_ora")
    private LocalTime valOra;

    @Column(name = "chiusa")
    private Integer chiusa;

    @Column(name = "ins_ip", length = 16)
    private String insIp;

    @Column(name = "ins_uid", length = 20)
    private String insUid;

    @Column(name = "ins_data")
    private LocalDateTime insData;

    public LocalDateTime getInsData() {
        return insData;
    }

    public void setInsData(LocalDateTime insData) {
        this.insData = insData;
    }

    public String getInsUid() {
        return insUid;
    }

    public void setInsUid(String insUid) {
        this.insUid = insUid;
    }

    public String getInsIp() {
        return insIp;
    }

    public void setInsIp(String insIp) {
        this.insIp = insIp;
    }

    public Integer getChiusa() {
        return chiusa;
    }

    public void setChiusa(Integer chiusa) {
        this.chiusa = chiusa;
    }

    public LocalTime getValOra() {
        return valOra;
    }

    public void setValOra(LocalTime valOra) {
        this.valOra = valOra;
    }

    public LocalDate getValData() {
        return valData;
    }

    public void setValData(LocalDate valData) {
        this.valData = valData;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Long getIdAccesso() {
        return idAccesso;
    }

    public void setIdAccesso(Long idAccesso) {
        this.idAccesso = idAccesso;
    }

    public Integer getIdInfo() {
        return idInfo;
    }

    public void setIdInfo(Integer idInfo) {
        this.idInfo = idInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}