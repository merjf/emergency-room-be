package com.portaleps.external.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "ps_accessi_dett", indexes = {
        @Index(name = "car_uid", columnList = "car_uid"),
        @Index(name = "id_accesso", columnList = "id_accesso")
})
@Entity
public class PsAccessiDett {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dettaglio", nullable = false)
    private Integer id;

    @Column(name = "id_accesso")
    private Long idAccesso;

    @Column(name = "car_uid", length = 20)
    private String carUid;

    @Column(name = "car_data")
    private LocalDateTime carData;

    @Column(name = "car_ip", length = 16)
    private String carIp;

    @Column(name = "car_note")
    private String carNote;

    @Column(name = "cess_data")
    private LocalDateTime cessData;

    @Column(name = "cess_note")
    private String cessNote;

    @Column(name = "cess_ip", length = 16)
    private String cessIp;

    public String getCessIp() {
        return cessIp;
    }

    public void setCessIp(String cessIp) {
        this.cessIp = cessIp;
    }

    public String getCessNote() {
        return cessNote;
    }

    public void setCessNote(String cessNote) {
        this.cessNote = cessNote;
    }

    public LocalDateTime getCessData() {
        return cessData;
    }

    public void setCessData(LocalDateTime cessData) {
        this.cessData = cessData;
    }

    public String getCarNote() {
        return carNote;
    }

    public void setCarNote(String carNote) {
        this.carNote = carNote;
    }

    public String getCarIp() {
        return carIp;
    }

    public void setCarIp(String carIp) {
        this.carIp = carIp;
    }

    public LocalDateTime getCarData() {
        return carData;
    }

    public void setCarData(LocalDateTime carData) {
        this.carData = carData;
    }

    public String getCarUid() {
        return carUid;
    }

    public void setCarUid(String carUid) {
        this.carUid = carUid;
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