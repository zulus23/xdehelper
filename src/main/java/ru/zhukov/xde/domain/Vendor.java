package ru.zhukov.xde.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Gukov on 05.06.2017.
 */

@Entity
public class Vendor {
    @Id
    @Column(name = "vend_num")
    private String vendNum;

    @Column(name = "site")
    private String site;
    @Column(name = "RUSinn")
    private String inn;
    @Column(name = "RUSkpp")
    private String kpp;
    @Column(name = "name")
    private String name;
    @Column(name = "RUSExtName")
    private String fullName;
    @Column(name = "countryCode")
    private String countryCode;
    @Column(name = "RUSokpo")
    private String okpo;
    @Column(name = "address")
    private String address;
    @Column(name = "zip")
    private String zip;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }
}
