package com.portaleps.model;

public class FarmacoSomministrato {

    private String farmaco;
    private String qnt;
    private String richiedente;
    private String dataRichiesta;
    private String noteRichiesta;
    private String somministratore;
    private String noteSomministrazione;
    private String dataSomministrazione;

    public String getFarmaco() {
        return farmaco;
    }

    public void setFarmaco(String farmaco) {
        this.farmaco = farmaco;
    }

    public String getQnt() {
        return qnt;
    }

    public void setQnt(String qnt) {
        this.qnt = qnt;
    }

    public String getRichiedente() {
        return richiedente;
    }

    public void setRichiedente(String richiedente) {
        this.richiedente = richiedente;
    }

    public String getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(String dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    public String getNoteRichiesta() {
        return noteRichiesta;
    }

    public void setNoteRichiesta(String noteRichiesta) {
        this.noteRichiesta = noteRichiesta;
    }

    public String getSomministratore() {
        return somministratore;
    }

    public void setSomministratore(String somministratore) {
        this.somministratore = somministratore;
    }

    public String getNoteSomministrazione() {
        return noteSomministrazione;
    }

    public void setNoteSomministrazione(String noteSomministrazione) {
        this.noteSomministrazione = noteSomministrazione;
    }

    public String getDataSomministrazione() {
        return dataSomministrazione;
    }

    public void setDataSomministrazione(String dataSomministrazione) {
        this.dataSomministrazione = dataSomministrazione;
    }
}
