package ru.zhukov.xde.domain;

import javax.persistence.*;

/**
 * Created by Gukov on 05.06.2017.
 */


/**
*  т.к. всегда выбираем адрес по seq_num == 0 , то cust_num будет уникальным идентификатором
* */

@Entity
//@Table(name = "custaddr")
//@NamedNativeQuery(name = "customer.custAddressForXML",
//                  query = "SELECT '' as site,c.cust_num,c.name,c.RUSExtName,c.RUSinn,c.RUSkpp," +
//                          " dbo.GTKFormatAddress(c.cust_num,0,'custaddr') as address,c.RUSokpo,c.zip,ISNULL(co.Uf_RUS_CountryCode,643) as country"+
//                          " FROM dbo.custaddr c "+
//                          " LEFT JOIN dbo.country co ON c.country = co.country "+
//                          " WHERE c.cust_seq = 0 and c.cust_num in (?)",resultSetMapping = "customer.custAddressForXMLMap")
//@SqlResultSetMapping(name = "customer.custAddressForXMLMap",
//                     entities = @EntityResult(entityClass = Customer.class))

public class Customer {
    @Id
    @Column(name = "cust_num")
    private String customerNumber;
    @Column(name = "site")
    private String site;
    @Column(name = "RUSinn")
    private String inn;
    @Column(name = "RUSkpp")
    private String kpp;
    @Column(name ="name")
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

    public Customer() {
    }


    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
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
}
