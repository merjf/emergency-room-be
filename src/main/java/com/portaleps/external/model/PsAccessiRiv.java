package com.portaleps.external.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Table(name = "ps_accessi_riv", indexes = {
        @Index(name = "id_triage", columnList = "id_triage"),
        @Index(name = "ins_uid", columnList = "ins_uid"),
        @Index(name = "id_accesso", columnList = "id_accesso")
})
@Entity
public class PsAccessiRiv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rivalutazione", nullable = false)
    private Integer id;

    @Column(name = "id_accesso")
    private Long idAccesso;

    @Column(name = "id_triage")
    private Integer idTriage;

    @Column(name = "val_data")
    private LocalDate valData;

    @Column(name = "val_ora")
    private LocalTime valOra;

    @Column(name = "note")
    private String note;

    @Column(name = "ins_uid", length = 20)
    private String insUid;

    @Column(name = "ins_ip", length = 16)
    private String insIp;

    @Column(name = "ins_data")
    private LocalDateTime insData;

    public LocalDateTime getInsData() {
        return insData;
    }

    public void setInsData(LocalDateTime insData) {
        this.insData = insData;
    }

    public String getInsIp() {
        return insIp;
    }

    public void setInsIp(String insIp) {
        this.insIp = insIp;
    }

    public String getInsUid() {
        return insUid;
    }

    public void setInsUid(String insUid) {
        this.insUid = insUid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public Integer getIdTriage() {
        return idTriage;
    }

    public void setIdTriage(Integer idTriage) {
        this.idTriage = idTriage;
    }

    public Long getIdAccesso() {
        return idAccesso;
    }

    public void setIdAccesso(Long idAccesso) {
        this.idAccesso = idAccesso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}