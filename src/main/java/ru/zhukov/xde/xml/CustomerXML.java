package ru.zhukov.xde.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by Gukov on 05.06.2017.
 * cust_1С_gotek  - characte C is russian
 */
@XmlRootElement(name = "cust_1С_gotek")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerXML {
    @XmlAttribute(name = "seq")
    private  String seq;
    @XmlAttribute(name = "action")
    private  String action;

    private CustXML cust;

    @XmlRootElement(name = "cust")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CustXML {
        @XmlAttribute(name = "cust_num")
        private String custNum;
        @XmlAttribute(name = "LineSeq")
        private String linSeq;
        @XmlAttribute(name = "RUSinn")
        private String inn;
        @XmlAttribute(name = "RUSkpp")
        private String kpp;
        @XmlAttribute(name = "name")
        private String name;
        @XmlAttribute(name = "RUSExtName")
        private String fullName;
        @XmlAttribute(name = "Uf_RUS_CountryCode")
        private String countryCode;
        @XmlAttribute(name = "RUSokpo")
        private String okpo;
        @XmlAttribute(name = "AddrFull")
        private String addressFull;

        public String getLinSeq() {
            return linSeq;
        }

        public void setLinSeq(String linSeq) {
            this.linSeq = linSeq;
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

        public String getAddressFull() {
            return addressFull;
        }

        public void setAddressFull(String addressFull) {
            this.addressFull = addressFull;
        }

    public String getCustNum() {
        return custNum;
    }

    public void setCustNum(String custNum) {
        this.custNum = custNum;
    }
}


    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public CustXML getCust() {
        return cust;
    }

    public void setCust(CustXML cust) {
        this.cust = cust;
    }
}
/*
@XmlRootElement(name = "cust")
@XmlAccessorType(XmlAccessType.FIELD)
 class CustXML {
    @XmlAttribute(name = "LineSeq")
    private int linSeq;
    @XmlAttribute(name = "RUSinn")
    private String inn;
    @XmlAttribute(name = "RUSkpp")
    private String kpp;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "RUSExtName")
    private String fullName;
    @XmlAttribute(name = "Uf_RUS_CountryCode")
    private String countryCode;
    @XmlAttribute(name = "RUSokpo")
    private String okpo;
    @XmlAttribute(name = "AddrFull")
    private String addressFull;

    public int getLinSeq() {
        return linSeq;
    }

    public void setLinSeq(int linSeq) {
        this.linSeq = linSeq;
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

    public String getAddressFull() {
        return addressFull;
    }

    public void setAddressFull(String addressFull) {
        this.addressFull = addressFull;
    }
}*/
