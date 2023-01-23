package com.portaleps.external.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "ps_accessi_esa", indexes = {
        @Index(name = "id_rich_acc_dett", columnList = "id_rich_acc_dett"),
        @Index(name = "id_acc_dett", columnList = "id_acc_dett"),
        @Index(name = "ins_uid", columnList = "ins_uid"),
        @Index(name = "id_esame", columnList = "id_esame"),
        @Index(name = "id_accesso", columnList = "id_accesso"),
        @Index(name = "id_rich_acc", columnList = "id_rich_acc")
})
@Entity
public class PsAccessiEsa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accesso_esa", nullable = false)
    private Integer id;

    @Column(name = "id_accesso")
    private Long idAccesso;

    @Column(name = "id_esame")
    private Integer idEsame;

    @Column(name = "id_acc_dett")
    private Integer idAccDett;

    @Column(name = "id_rich_acc")
    private Integer idRichAcc;

    @Column(name = "id_rich_acc_dett")
    private Integer idRichAccDett;

    @Column(name = "data_richiesta")
    private LocalDateTime dataRichiesta;

    @Column(name = "note")
    private String note;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "annullato")
    private Boolean annullato;

    @Column(name = "rifiutato")
    private Integer rifiutato;

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

    public Integer getRifiutato() {
        return rifiutato;
    }

    public void setRifiutato(Integer rifiutato) {
        this.rifiutato = rifiutato;
    }

    public Boolean getAnnullato() {
        return annullato;
    }

    public void setAnnullato(Boolean annullato) {
        this.annullato = annullato;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(LocalDateTime dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    public Integer getIdRichAccDett() {
        return idRichAccDett;
    }

    public void setIdRichAccDett(Integer idRichAccDett) {
        this.idRichAccDett = idRichAccDett;
    }

    public Integer getIdRichAcc() {
        return idRichAcc;
    }

    public void setIdRichAcc(Integer idRichAcc) {
        this.idRichAcc = idRichAcc;
    }

    public Integer getIdAccDett() {
        return idAccDett;
    }

    public void setIdAccDett(Integer idAccDett) {
        this.idAccDett = idAccDett;
    }

    public Integer getIdEsame() {
        return idEsame;
    }

    public void setIdEsame(Integer idEsame) {
        this.idEsame = idEsame;
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