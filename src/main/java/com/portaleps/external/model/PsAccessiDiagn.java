package com.portaleps.external.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "ps_accessi_diagn", indexes = {
        @Index(name = "tipo", columnList = "tipo"),
        @Index(name = "id_diagnosi", columnList = "id_diagnosi"),
        @Index(name = "id_accesso", columnList = "id_accesso"),
})
@Entity
public class PsAccessiDiagn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accesso_diagn", nullable = false)
    private Integer id;

    @Column(name = "id_accesso")
    private Long idAccesso;

    @Column(name = "id_diagnosi")
    private Integer idDiagnosi;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "ins_data")
    private LocalDateTime insData;

    @Column(name = "ins_ip", length = 16)
    private String insIp;

    @Column(name = "ins_uid", length = 20)
    private String insUid;

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

    public LocalDateTime getInsData() {
        return insData;
    }

    public void setInsData(LocalDateTime insData) {
        this.insData = insData;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getIdDiagnosi() {
        return idDiagnosi;
    }

    public void setIdDiagnosi(Integer idDiagnosi) {
        this.idDiagnosi = idDiagnosi;
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