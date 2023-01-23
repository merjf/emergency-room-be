package com.portaleps.external.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Table(name = "ps_accessi", indexes = {
        @Index(name = "annullato", columnList = "annullato"),
        @Index(name = "id_problema_dett", columnList = "id_problema_dett"),
        @Index(name = "id_erogante", columnList = "id_erogante"),
        @Index(name = "id_mezzo", columnList = "id_mezzo"),
        @Index(name = "escludi_emur", columnList = "emur_escludi"),
        @Index(name = "id_causa", columnList = "id_causa"),
        @Index(name = "ppi_consigliato", columnList = "ppi_consigliato"),
        @Index(name = "id_problema", columnList = "id_problema"),
        @Index(name = "medico_acc", columnList = "medico_acc"),
        @Index(name = "id_triage_attuale", columnList = "id_triage_attuale"),
        @Index(name = "id_esenzione", columnList = "id_esenzione"),
        @Index(name = "id_triage_ingresso", columnList = "id_triage_ingresso"),
        @Index(name = "id_istituto_prov", columnList = "id_istituto_prov"),
        @Index(name = "id_patologia", columnList = "id_patologia"),
        @Index(name = "ppi_accettato", columnList = "ppi_accettato"),
        @Index(name = "dimesso", columnList = "dimesso"),
        @Index(name = "ins_uid", columnList = "ins_ip"),
        @Index(name = "id_paz", columnList = "id_paz")
})
@Entity
public class PsAccessi {
    @Id
    @Column(name = "id_accesso", nullable = false)
    private Long id;

    @Column(name = "id_paz")
    private Integer idPaz;

    @Column(name = "id_triage_ingresso")
    private Integer idTriageIngresso;

    @Column(name = "id_triage_attuale")
    private Integer idTriageAttuale;

    @Column(name = "id_causa")
    private Integer idCausa;

    @Column(name = "id_erogante")
    private Integer idErogante;

    @Column(name = "id_esenzione")
    private Integer idEsenzione;

    @Column(name = "id_patologia")
    private Integer idPatologia;

    @Column(name = "id_mezzo")
    private Integer idMezzo;

    @Column(name = "id_problema")
    private Integer idProblema;

    @Column(name = "id_problema_dett")
    private Integer idProblemaDett;

    @Column(name = "id_istituto_prov")
    private Integer idIstitutoProv;

    @Column(name = "targa_mezzo", length = 10)
    private String targaMezzo;

    @Column(name = "data_acc")
    private LocalDate dataAcc;

    @Column(name = "ora_acc")
    private LocalTime oraAcc;

    @Column(name = "ultima_valutazione")
    private LocalDateTime ultimaValutazione;

    @Column(name = "118_centrale", length = 5)
    private String _118Centrale;

    @Column(name = "118_missione", length = 16)
    private String _118Missione;

    @Column(name = "inc_resp")
    private Integer incResp;

    @Column(name = "inc_luogo")
    private String incLuogo;

    @Column(name = "inc_circ")
    private String incCirc;

    @Column(name = "inc_data")
    private LocalDateTime incData;

    @Lob
    @Column(name = "note")
    private String note;

    @Column(name = "medico_acc", length = 20)
    private String medicoAcc;

    @Column(name = "annullato")
    private Boolean annullato;

    @Column(name = "stato", length = 2)
    private String stato;

    @Column(name = "obi")
    private Integer obi;

    @Column(name = "obi_dimesso")
    private Integer obiDimesso;

    @Column(name = "accettato")
    private Integer accettato;

    @Column(name = "ppi_consigliato")
    private Integer ppiConsigliato;

    @Column(name = "ppi_accettato")
    private Integer ppiAccettato;

    @Column(name = "dimesso")
    private Integer dimesso;

    @Column(name = "consegnato")
    private Integer consegnato;

    @Column(name = "riaperto")
    private Integer riaperto;

    @Column(name = "emur_escludi")
    private Integer emurEscludi;

    @Column(name = "emur_escludi_uid", length = 20)
    private String emurEscludiUid;

    @Column(name = "emur_escludi_data")
    private LocalDateTime emurEscludiData;

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

    public LocalDateTime getEmurEscludiData() {
        return emurEscludiData;
    }

    public void setEmurEscludiData(LocalDateTime emurEscludiData) {
        this.emurEscludiData = emurEscludiData;
    }

    public String getEmurEscludiUid() {
        return emurEscludiUid;
    }

    public void setEmurEscludiUid(String emurEscludiUid) {
        this.emurEscludiUid = emurEscludiUid;
    }

    public Integer getEmurEscludi() {
        return emurEscludi;
    }

    public void setEmurEscludi(Integer emurEscludi) {
        this.emurEscludi = emurEscludi;
    }

    public Integer getRiaperto() {
        return riaperto;
    }

    public void setRiaperto(Integer riaperto) {
        this.riaperto = riaperto;
    }

    public Integer getConsegnato() {
        return consegnato;
    }

    public void setConsegnato(Integer consegnato) {
        this.consegnato = consegnato;
    }

    public Integer getDimesso() {
        return dimesso;
    }

    public void setDimesso(Integer dimesso) {
        this.dimesso = dimesso;
    }

    public Integer getPpiAccettato() {
        return ppiAccettato;
    }

    public void setPpiAccettato(Integer ppiAccettato) {
        this.ppiAccettato = ppiAccettato;
    }

    public Integer getPpiConsigliato() {
        return ppiConsigliato;
    }

    public void setPpiConsigliato(Integer ppiConsigliato) {
        this.ppiConsigliato = ppiConsigliato;
    }

    public Integer getAccettato() {
        return accettato;
    }

    public void setAccettato(Integer accettato) {
        this.accettato = accettato;
    }

    public Integer getObiDimesso() {
        return obiDimesso;
    }

    public void setObiDimesso(Integer obiDimesso) {
        this.obiDimesso = obiDimesso;
    }

    public Integer getObi() {
        return obi;
    }

    public void setObi(Integer obi) {
        this.obi = obi;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Boolean getAnnullato() {
        return annullato;
    }

    public void setAnnullato(Boolean annullato) {
        this.annullato = annullato;
    }

    public String getMedicoAcc() {
        return medicoAcc;
    }

    public void setMedicoAcc(String medicoAcc) {
        this.medicoAcc = medicoAcc;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getIncData() {
        return incData;
    }

    public void setIncData(LocalDateTime incData) {
        this.incData = incData;
    }

    public String getIncCirc() {
        return incCirc;
    }

    public void setIncCirc(String incCirc) {
        this.incCirc = incCirc;
    }

    public String getIncLuogo() {
        return incLuogo;
    }

    public void setIncLuogo(String incLuogo) {
        this.incLuogo = incLuogo;
    }

    public Integer getIncResp() {
        return incResp;
    }

    public void setIncResp(Integer incResp) {
        this.incResp = incResp;
    }

    public String get_118Missione() {
        return _118Missione;
    }

    public void set_118Missione(String _118Missione) {
        this._118Missione = _118Missione;
    }

    public String get_118Centrale() {
        return _118Centrale;
    }

    public void set_118Centrale(String _118Centrale) {
        this._118Centrale = _118Centrale;
    }

    public LocalDateTime getUltimaValutazione() {
        return ultimaValutazione;
    }

    public void setUltimaValutazione(LocalDateTime ultimaValutazione) {
        this.ultimaValutazione = ultimaValutazione;
    }

    public LocalTime getOraAcc() {
        return oraAcc;
    }

    public void setOraAcc(LocalTime oraAcc) {
        this.oraAcc = oraAcc;
    }

    public LocalDate getDataAcc() {
        return dataAcc;
    }

    public void setDataAcc(LocalDate dataAcc) {
        this.dataAcc = dataAcc;
    }

    public String getTargaMezzo() {
        return targaMezzo;
    }

    public void setTargaMezzo(String targaMezzo) {
        this.targaMezzo = targaMezzo;
    }

    public Integer getIdIstitutoProv() {
        return idIstitutoProv;
    }

    public void setIdIstitutoProv(Integer idIstitutoProv) {
        this.idIstitutoProv = idIstitutoProv;
    }

    public Integer getIdProblemaDett() {
        return idProblemaDett;
    }

    public void setIdProblemaDett(Integer idProblemaDett) {
        this.idProblemaDett = idProblemaDett;
    }

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(Integer idProblema) {
        this.idProblema = idProblema;
    }

    public Integer getIdMezzo() {
        return idMezzo;
    }

    public void setIdMezzo(Integer idMezzo) {
        this.idMezzo = idMezzo;
    }

    public Integer getIdPatologia() {
        return idPatologia;
    }

    public void setIdPatologia(Integer idPatologia) {
        this.idPatologia = idPatologia;
    }

    public Integer getIdEsenzione() {
        return idEsenzione;
    }

    public void setIdEsenzione(Integer idEsenzione) {
        this.idEsenzione = idEsenzione;
    }

    public Integer getIdErogante() {
        return idErogante;
    }

    public void setIdErogante(Integer idErogante) {
        this.idErogante = idErogante;
    }

    public Integer getIdCausa() {
        return idCausa;
    }

    public void setIdCausa(Integer idCausa) {
        this.idCausa = idCausa;
    }

    public Integer getIdTriageAttuale() {
        return idTriageAttuale;
    }

    public void setIdTriageAttuale(Integer idTriageAttuale) {
        this.idTriageAttuale = idTriageAttuale;
    }

    public Integer getIdTriageIngresso() {
        return idTriageIngresso;
    }

    public void setIdTriageIngresso(Integer idTriageIngresso) {
        this.idTriageIngresso = idTriageIngresso;
    }

    public Integer getIdPaz() {
        return idPaz;
    }

    public void setIdPaz(Integer idPaz) {
        this.idPaz = idPaz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}