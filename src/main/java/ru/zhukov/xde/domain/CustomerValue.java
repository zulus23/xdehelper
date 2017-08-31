package ru.zhukov.xde.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerValue {
    @Id
    private  String code;
    private  String site;
    private  String inn;
    private  String kpp;
    private  String name;
    private  String fullName;
    private  String codeCountry;
    private  String okpo;
    private  String address;
    private  String zip;

    public CustomerValue() {
    }

    public CustomerValue(String code, String site, String inn, String kpp, String name, String fullName, String codeCountry, String okpo, String address, String zip) {
        this.code = code;
        this.site = site;
        this.inn = inn;
        this.kpp = kpp;
        this.name = name;
        this.fullName = fullName;
        this.codeCountry = codeCountry;
        this.okpo = okpo;
        this.address = address;
        this.zip = zip;
    }

    public String getCode() {
        return code;
    }

    public String getSite() {
        return site;
    }

    public String getInn() {
        return inn;
    }

    public String getKpp() {
        return kpp;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCodeCountry() {
        return codeCountry;
    }

    public String getOkpo() {
        return okpo;
    }

    public String getAddress() {
        return address;
    }

    public String getZip() {
        return zip;
    }
}
