package ru.zhukov.xde.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by Gukov on 05.06.2017.
 * cust_1С_gotek  - characte C is russian
 */
@XmlRootElement(name = "vend_1С_gotek")
@XmlAccessorType(XmlAccessType.FIELD)
public class VendorXML {
    @XmlAttribute(name = "seq")
    private  String seq;
    @XmlAttribute(name = "action")
    private  String action;

    @XmlElement(name = "vend")
    private VendXML cust;

    @XmlRootElement(name = "vend")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class VendXML {
        @XmlAttribute(name = "cust_num")
        private String vendNum;
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

        public String getVendNum() {
            return vendNum;
        }

        public void setVendNum(String vendNum) {
            this.vendNum = vendNum;
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

    public VendXML getCust() {
        return cust;
    }

    public void setCust(VendXML cust) {
        this.cust = cust;
    }
}
