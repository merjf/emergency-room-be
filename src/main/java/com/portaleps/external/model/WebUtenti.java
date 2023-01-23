package com.portaleps.external.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "web_utenti", indexes = {
        @Index(name = "stato_account", columnList = "stato_account"),
        @Index(name = "id_modello", columnList = "id_modello"),
        @Index(name = "blocco_sconto", columnList = "blocco_sconto"),
        @Index(name = "refertante", columnList = "refertante"),
        @Index(name = "id_lingua", columnList = "id_lingua"),
        @Index(name = "profilo", columnList = "profilo"),
        @Index(name = "reparto", columnList = "reparto")
})
@Entity
public class WebUtenti {
    @Id
    @Column(name = "uid", nullable = false, length = 20)
    private String id;

    @Column(name = "nome", nullable = false, length = 20)
    private String nome;

    @Column(name = "cognome", nullable = false, length = 20)
    private String cognome;

    @Column(name = "cod_fisc", nullable = false, length = 16)
    private String codFisc;

    @Column(name = "pwd", nullable = false, length = 32)
    private String pwd;

    @Column(name = "profilo", nullable = false)
    private Integer profilo;

    @Column(name = "tel_int", nullable = false, length = 20)
    private String telInt;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "tel_pri", nullable = false, length = 20)
    private String telPri;

    @Column(name = "tel_cel", nullable = false, length = 20)
    private String telCel;

    @Column(name = "ruolo", nullable = false, length = 50)
    private String ruolo;

    @Column(name = "cod_enpam", length = 11)
    private String codEnpam;

    @Column(name = "cod_reg", length = 10)
    private String codReg;

    @Column(name = "viewer", nullable = false)
    private Integer viewer;

    @Column(name = "tipo_viewer")
    private Integer tipoViewer;

    @Column(name = "ris", nullable = false)
    private Integer ris;

    @Column(name = "zeus", nullable = false)
    private Integer zeus;

    @Column(name = "pda", nullable = false)
    private Integer pda;

    @Column(name = "allegati", nullable = false)
    private Integer allegati;

    @Column(name = "vis_eseguiti", nullable = false)
    private Integer visEseguiti;

    @Column(name = "reparto", nullable = false)
    private Integer reparto;

    @Column(name = "refertante", nullable = false)
    private Integer refertante;

    @Column(name = "sms")
    private Integer sms;

    @Column(name = "anonimizza", nullable = false)
    private Integer anonimizza;

    @Column(name = "ses_timeout", nullable = false)
    private Integer sesTimeout;

    @Column(name = "ext_login", nullable = false)
    private Integer extLogin;

    @Column(name = "pwd_data")
    private LocalDate pwdData;

    @Column(name = "firma_rigo1", length = 50)
    private String firmaRigo1;

    @Column(name = "firma_rigo2", length = 50)
    private String firmaRigo2;

    @Column(name = "firma_rigo3", length = 50)
    private String firmaRigo3;

    @Column(name = "stato_account")
    private Integer statoAccount;

    @Lob
    @Column(name = "firma_digitale")
    private String firmaDigitale;

    @Column(name = "pwd_messaggi")
    private Integer pwdMessaggi;

    @Column(name = "id_magazzino")
    private Integer idMagazzino;

    @Column(name = "lettere_rif", length = 3)
    private String lettereRif;

    @Column(name = "mag_firma_rigo1", length = 30)
    private String magFirmaRigo1;

    @Column(name = "mag_firma_rigo2", length = 30)
    private String magFirmaRigo2;

    @Column(name = "mod_anagrafica")
    private Integer modAnagrafica;

    @Column(name = "db_download")
    private Integer dbDownload;

    @Column(name = "riepilogo")
    private Integer riepilogo;

    @Column(name = "statistiche")
    private Integer statistiche;

    @Column(name = "id_domanda")
    private Integer idDomanda;

    @Column(name = "risp_domanda")
    private String rispDomanda;

    @Column(name = "mod_fattura")
    private Integer modFattura;

    @Column(name = "mod_cartella_clinica")
    private Integer modCartellaClinica;

    @Column(name = "medico_ppi")
    private Integer medicoPpi;

    @Column(name = "blocco_accesso")
    private Integer bloccoAccesso;

    @Column(name = "id_lingua")
    private Integer idLingua;

    @Column(name = "perm_pers")
    private Integer permPers;

    @Column(name = "storico")
    private Integer storico;

    @Column(name = "contabilita_sdo")
    private Integer contabilitaSdo;

    @Column(name = "provenienza")
    private Integer provenienza;

    @Column(name = "id_modello")
    private Integer idModello;

    @Column(name = "data_creazione")
    private LocalDateTime dataCreazione;

    @Column(name = "data_modifica")
    private LocalDateTime dataModifica;

    @Column(name = "blocco_sconto")
    private Integer bloccoSconto;

    @Column(name = "cancella_esami_ref")
    private Integer cancellaEsamiRef;

    @Column(name = "lab_ref_log")
    private Integer labRefLog;

    @Column(name = "referti_propri")
    private Integer refertiPropri;

    @Column(name = "annulla_consegna")
    private Integer annullaConsegna;

    @Column(name = "cartella_stampa")
    private Integer cartellaStampa;

    @Column(name = "elimina_dimissione")
    private Integer eliminaDimissione;

    @Column(name = "cc_prescr_farm_pwd")
    private Integer ccPrescrFarmPwd;

    @Column(name = "utente_cassa")
    private Integer utenteCassa;

    @Column(name = "modifica_agenda")
    private Integer modificaAgenda;

    public Integer getModificaAgenda() {
        return modificaAgenda;
    }

    public void setModificaAgenda(Integer modificaAgenda) {
        this.modificaAgenda = modificaAgenda;
    }

    public Integer getUtenteCassa() {
        return utenteCassa;
    }

    public void setUtenteCassa(Integer utenteCassa) {
        this.utenteCassa = utenteCassa;
    }

    public Integer getCcPrescrFarmPwd() {
        return ccPrescrFarmPwd;
    }

    public void setCcPrescrFarmPwd(Integer ccPrescrFarmPwd) {
        this.ccPrescrFarmPwd = ccPrescrFarmPwd;
    }

    public Integer getEliminaDimissione() {
        return eliminaDimissione;
    }

    public void setEliminaDimissione(Integer eliminaDimissione) {
        this.eliminaDimissione = eliminaDimissione;
    }

    public Integer getCartellaStampa() {
        return cartellaStampa;
    }

    public void setCartellaStampa(Integer cartellaStampa) {
        this.cartellaStampa = cartellaStampa;
    }

    public Integer getAnnullaConsegna() {
        return annullaConsegna;
    }

    public void setAnnullaConsegna(Integer annullaConsegna) {
        this.annullaConsegna = annullaConsegna;
    }

    public Integer getRefertiPropri() {
        return refertiPropri;
    }

    public void setRefertiPropri(Integer refertiPropri) {
        this.refertiPropri = refertiPropri;
    }

    public Integer getLabRefLog() {
        return labRefLog;
    }

    public void setLabRefLog(Integer labRefLog) {
        this.labRefLog = labRefLog;
    }

    public Integer getCancellaEsamiRef() {
        return cancellaEsamiRef;
    }

    public void setCancellaEsamiRef(Integer cancellaEsamiRef) {
        this.cancellaEsamiRef = cancellaEsamiRef;
    }

    public Integer getBloccoSconto() {
        return bloccoSconto;
    }

    public void setBloccoSconto(Integer bloccoSconto) {
        this.bloccoSconto = bloccoSconto;
    }

    public LocalDateTime getDataModifica() {
        return dataModifica;
    }

    public void setDataModifica(LocalDateTime dataModifica) {
        this.dataModifica = dataModifica;
    }

    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public Integer getIdModello() {
        return idModello;
    }

    public void setIdModello(Integer idModello) {
        this.idModello = idModello;
    }

    public Integer getProvenienza() {
        return provenienza;
    }

    public void setProvenienza(Integer provenienza) {
        this.provenienza = provenienza;
    }

    public Integer getContabilitaSdo() {
        return contabilitaSdo;
    }

    public void setContabilitaSdo(Integer contabilitaSdo) {
        this.contabilitaSdo = contabilitaSdo;
    }

    public Integer getStorico() {
        return storico;
    }

    public void setStorico(Integer storico) {
        this.storico = storico;
    }

    public Integer getPermPers() {
        return permPers;
    }

    public void setPermPers(Integer permPers) {
        this.permPers = permPers;
    }

    public Integer getIdLingua() {
        return idLingua;
    }

    public void setIdLingua(Integer idLingua) {
        this.idLingua = idLingua;
    }

    public Integer getBloccoAccesso() {
        return bloccoAccesso;
    }

    public void setBloccoAccesso(Integer bloccoAccesso) {
        this.bloccoAccesso = bloccoAccesso;
    }

    public Integer getMedicoPpi() {
        return medicoPpi;
    }

    public void setMedicoPpi(Integer medicoPpi) {
        this.medicoPpi = medicoPpi;
    }

    public Integer getModCartellaClinica() {
        return modCartellaClinica;
    }

    public void setModCartellaClinica(Integer modCartellaClinica) {
        this.modCartellaClinica = modCartellaClinica;
    }

    public Integer getModFattura() {
        return modFattura;
    }

    public void setModFattura(Integer modFattura) {
        this.modFattura = modFattura;
    }

    public String getRispDomanda() {
        return rispDomanda;
    }

    public void setRispDomanda(String rispDomanda) {
        this.rispDomanda = rispDomanda;
    }

    public Integer getIdDomanda() {
        return idDomanda;
    }

    public void setIdDomanda(Integer idDomanda) {
        this.idDomanda = idDomanda;
    }

    public Integer getStatistiche() {
        return statistiche;
    }

    public void setStatistiche(Integer statistiche) {
        this.statistiche = statistiche;
    }

    public Integer getRiepilogo() {
        return riepilogo;
    }

    public void setRiepilogo(Integer riepilogo) {
        this.riepilogo = riepilogo;
    }

    public Integer getDbDownload() {
        return dbDownload;
    }

    public void setDbDownload(Integer dbDownload) {
        this.dbDownload = dbDownload;
    }

    public Integer getModAnagrafica() {
        return modAnagrafica;
    }

    public void setModAnagrafica(Integer modAnagrafica) {
        this.modAnagrafica = modAnagrafica;
    }

    public String getMagFirmaRigo2() {
        return magFirmaRigo2;
    }

    public void setMagFirmaRigo2(String magFirmaRigo2) {
        this.magFirmaRigo2 = magFirmaRigo2;
    }

    public String getMagFirmaRigo1() {
        return magFirmaRigo1;
    }

    public void setMagFirmaRigo1(String magFirmaRigo1) {
        this.magFirmaRigo1 = magFirmaRigo1;
    }

    public String getLettereRif() {
        return lettereRif;
    }

    public void setLettereRif(String lettereRif) {
        this.lettereRif = lettereRif;
    }

    public Integer getIdMagazzino() {
        return idMagazzino;
    }

    public void setIdMagazzino(Integer idMagazzino) {
        this.idMagazzino = idMagazzino;
    }

    public Integer getPwdMessaggi() {
        return pwdMessaggi;
    }

    public void setPwdMessaggi(Integer pwdMessaggi) {
        this.pwdMessaggi = pwdMessaggi;
    }

    public String getFirmaDigitale() {
        return firmaDigitale;
    }

    public void setFirmaDigitale(String firmaDigitale) {
        this.firmaDigitale = firmaDigitale;
    }

    public Integer getStatoAccount() {
        return statoAccount;
    }

    public void setStatoAccount(Integer statoAccount) {
        this.statoAccount = statoAccount;
    }

    public String getFirmaRigo3() {
        return firmaRigo3;
    }

    public void setFirmaRigo3(String firmaRigo3) {
        this.firmaRigo3 = firmaRigo3;
    }

    public String getFirmaRigo2() {
        return firmaRigo2;
    }

    public void setFirmaRigo2(String firmaRigo2) {
        this.firmaRigo2 = firmaRigo2;
    }

    public String getFirmaRigo1() {
        return firmaRigo1;
    }

    public void setFirmaRigo1(String firmaRigo1) {
        this.firmaRigo1 = firmaRigo1;
    }

    public LocalDate getPwdData() {
        return pwdData;
    }

    public void setPwdData(LocalDate pwdData) {
        this.pwdData = pwdData;
    }

    public Integer getExtLogin() {
        return extLogin;
    }

    public void setExtLogin(Integer extLogin) {
        this.extLogin = extLogin;
    }

    public Integer getSesTimeout() {
        return sesTimeout;
    }

    public void setSesTimeout(Integer sesTimeout) {
        this.sesTimeout = sesTimeout;
    }

    public Integer getAnonimizza() {
        return anonimizza;
    }

    public void setAnonimizza(Integer anonimizza) {
        this.anonimizza = anonimizza;
    }

    public Integer getSms() {
        return sms;
    }

    public void setSms(Integer sms) {
        this.sms = sms;
    }

    public Integer getRefertante() {
        return refertante;
    }

    public void setRefertante(Integer refertante) {
        this.refertante = refertante;
    }

    public Integer getReparto() {
        return reparto;
    }

    public void setReparto(Integer reparto) {
        this.reparto = reparto;
    }

    public Integer getVisEseguiti() {
        return visEseguiti;
    }

    public void setVisEseguiti(Integer visEseguiti) {
        this.visEseguiti = visEseguiti;
    }

    public Integer getAllegati() {
        return allegati;
    }

    public void setAllegati(Integer allegati) {
        this.allegati = allegati;
    }

    public Integer getPda() {
        return pda;
    }

    public void setPda(Integer pda) {
        this.pda = pda;
    }

    public Integer getZeus() {
        return zeus;
    }

    public void setZeus(Integer zeus) {
        this.zeus = zeus;
    }

    public Integer getRis() {
        return ris;
    }

    public void setRis(Integer ris) {
        this.ris = ris;
    }

    public Integer getTipoViewer() {
        return tipoViewer;
    }

    public void setTipoViewer(Integer tipoViewer) {
        this.tipoViewer = tipoViewer;
    }

    public Integer getViewer() {
        return viewer;
    }

    public void setViewer(Integer viewer) {
        this.viewer = viewer;
    }

    public String getCodReg() {
        return codReg;
    }

    public void setCodReg(String codReg) {
        this.codReg = codReg;
    }

    public String getCodEnpam() {
        return codEnpam;
    }

    public void setCodEnpam(String codEnpam) {
        this.codEnpam = codEnpam;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getTelCel() {
        return telCel;
    }

    public void setTelCel(String telCel) {
        this.telCel = telCel;
    }

    public String getTelPri() {
        return telPri;
    }

    public void setTelPri(String telPri) {
        this.telPri = telPri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelInt() {
        return telInt;
    }

    public void setTelInt(String telInt) {
        this.telInt = telInt;
    }

    public Integer getProfilo() {
        return profilo;
    }

    public void setProfilo(Integer profilo) {
        this.profilo = profilo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCodFisc() {
        return codFisc;
    }

    public void setCodFisc(String codFisc) {
        this.codFisc = codFisc;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}