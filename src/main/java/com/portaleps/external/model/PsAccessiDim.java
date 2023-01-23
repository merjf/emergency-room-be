package com.portaleps.external.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Table(name = "ps_accessi_dim", indexes = {
        @Index(name = "id_mezzo_dim", columnList = "id_mezzo_dim"),
        @Index(name = "id_trasferimento", columnList = "id_trasferimento"),
        @Index(name = "id_forza_ordine", columnList = "id_forza_ordine"),
        @Index(name = "id_istituto_dim", columnList = "id_istituto_dim"),
        @Index(name = "id_reparto", columnList = "id_reparto"),
        @Index(name = "id_ricovero", columnList = "id_ricovero"),
        @Index(name = "id_esito", columnList = "id_esito"),
        @Index(name = "id_rich_ricovero", columnList = "id_rich_ricovero")
})
@Entity
public class PsAccessiDim {
    @Id
    @Column(name = "id_accesso", nullable = false)
    private Long id;

    @Column(name = "id_esito")
    private Integer idEsito;

    @Column(name = "id_rich_ricovero")
    private Integer idRichRicovero;

    @Column(name = "id_mezzo_dim")
    private Integer idMezzoDim;

    @Column(name = "id_istituto_dim")
    private Integer idIstitutoDim;

    @Column(name = "id_trasferimento")
    private Integer idTrasferimento;

    @Column(name = "id_reparto")
    private Integer idReparto;

    @Column(name = "id_ricovero")
    private Integer idRicovero;

    @Column(name = "id_forza_ordine")
    private Integer idForzaOrdine;

    @Column(name = "agente_forza_ordine", length = 50)
    private String agenteForzaOrdine;

    @Column(name = "data_dim")
    private LocalDate dataDim;

    @Column(name = "ora_dim")
    private LocalTime oraDim;

    @Column(name = "targa_mezzo", length = 10)
    private String targaMezzo;

    @Column(name = "prognosi")
    private Integer prognosi;

    @Lob
    @Column(name = "terapia")
    private String terapia;

    @Lob
    @Column(name = "referto")
    private String referto;

    @Lob
    @Column(name = "conclusioni")
    private String conclusioni;

    @Lob
    @Column(name = "followup")
    private String followup;

    @Column(name = "ref_inail")
    private Integer refInail;

    @Column(name = "ref_giudiz")
    private Boolean refGiudiz;

    @Column(name = "ref_morso")
    private Boolean refMorso;

    @Column(name = "ref_altro")
    private Boolean refAltro;

    @Column(name = "ins_uid", length = 20)
    private String insUid;

    @Column(name = "ins_data")
    private LocalDateTime insData;

    @Column(name = "ins_ip", length = 16)
    private String insIp;

    public String getInsIp() {
        return insIp;
    }

    public void setInsIp(String insIp) {
        this.insIp = insIp;
    }

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

    public Boolean getRefAltro() {
        return refAltro;
    }

    public void setRefAltro(Boolean refAltro) {
        this.refAltro = refAltro;
    }

    public Boolean getRefMorso() {
        return refMorso;
    }

    public void setRefMorso(Boolean refMorso) {
        this.refMorso = refMorso;
    }

    public Boolean getRefGiudiz() {
        return refGiudiz;
    }

    public void setRefGiudiz(Boolean refGiudiz) {
        this.refGiudiz = refGiudiz;
    }

    public Integer getRefInail() {
        return refInail;
    }

    public void setRefInail(Integer refInail) {
        this.refInail = refInail;
    }

    public String getFollowup() {
        return followup;
    }

    public void setFollowup(String followup) {
        this.followup = followup;
    }

    public String getConclusioni() {
        return conclusioni;
    }

    public void setConclusioni(String conclusioni) {
        this.conclusioni = conclusioni;
    }

    public String getReferto() {
        return referto;
    }

    public void setReferto(String referto) {
        this.referto = referto;
    }

    public String getTerapia() {
        return terapia;
    }

    public void setTerapia(String terapia) {
        this.terapia = terapia;
    }

    public Integer getPrognosi() {
        return prognosi;
    }

    public void setPrognosi(Integer prognosi) {
        this.prognosi = prognosi;
    }

    public String getTargaMezzo() {
        return targaMezzo;
    }

    public void setTargaMezzo(String targaMezzo) {
        this.targaMezzo = targaMezzo;
    }

    public LocalTime getOraDim() {
        return oraDim;
    }

    public void setOraDim(LocalTime oraDim) {
        this.oraDim = oraDim;
    }

    public LocalDate getDataDim() {
        return dataDim;
    }

    public void setDataDim(LocalDate dataDim) {
        this.dataDim = dataDim;
    }

    public String getAgenteForzaOrdine() {
        return agenteForzaOrdine;
    }

    public void setAgenteForzaOrdine(String agenteForzaOrdine) {
        this.agenteForzaOrdine = agenteForzaOrdine;
    }

    public Integer getIdForzaOrdine() {
        return idForzaOrdine;
    }

    public void setIdForzaOrdine(Integer idForzaOrdine) {
        this.idForzaOrdine = idForzaOrdine;
    }

    public Integer getIdRicovero() {
        return idRicovero;
    }

    public void setIdRicovero(Integer idRicovero) {
        this.idRicovero = idRicovero;
    }

    public Integer getIdReparto() {
        return idReparto;
    }

    public void setIdReparto(Integer idReparto) {
        this.idReparto = idReparto;
    }

    public Integer getIdTrasferimento() {
        return idTrasferimento;
    }

    public void setIdTrasferimento(Integer idTrasferimento) {
        this.idTrasferimento = idTrasferimento;
    }

    public Integer getIdIstitutoDim() {
        return idIstitutoDim;
    }

    public void setIdIstitutoDim(Integer idIstitutoDim) {
        this.idIstitutoDim = idIstitutoDim;
    }

    public Integer getIdMezzoDim() {
        return idMezzoDim;
    }

    public void setIdMezzoDim(Integer idMezzoDim) {
        this.idMezzoDim = idMezzoDim;
    }

    public Integer getIdRichRicovero() {
        return idRichRicovero;
    }

    public void setIdRichRicovero(Integer idRichRicovero) {
        this.idRichRicovero = idRichRicovero;
    }

    public Integer getIdEsito() {
        return idEsito;
    }

    public void setIdEsito(Integer idEsito) {
        this.idEsito = idEsito;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}