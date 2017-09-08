package ru.zhukov.xde.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Gukov on 07.06.2017.
 */
@Entity
public class VendorLcr {
    @Id
    @Column(name = "vend_lcr_num")
    private String lcrNum;
    @Column(name = "site")
    private String site;
    @Column(name = "vend_num")
    private String vendNum;
    @Column(name = "RUSinn")
    private String inn;
    @Column(name = "RUSkpp")
    private String kpp;
    @Column(name = "issue_date")
    private String date;
    @Column(name = "curr_code")
    private String currentCode;


    public String getLcrNum() {
        return lcrNum;
    }

    public void setLcrNum(String lcrNum) {
        this.lcrNum = lcrNum;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getVendNum() {
        return vendNum;
    }

    public void setVendNum(String vendNum) {
        this.vendNum = vendNum;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrentCode() {
        return currentCode;
    }

    public void setCurrentCode(String currentCode) {
        this.currentCode = currentCode;
    }
}
