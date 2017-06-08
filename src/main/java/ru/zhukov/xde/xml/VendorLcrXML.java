package ru.zhukov.xde.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by Gukov on 07.06.2017.
 * in name root element character 'С' russian
 */

@XmlRootElement(name = "vendlcr_1С_gotek")
@XmlAccessorType(XmlAccessType.FIELD)
public class VendorLcrXML {

    @XmlAttribute(name = "seq")
    private  String seq;
    @XmlAttribute(name = "action")
    private  String action;
    @XmlElement(name = "RUSXDEVendlcrView")
    private VendLcr vendLcr;

    @XmlRootElement(name = "RUSXDEVendlcrView")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class VendLcr {
        @XmlAttribute(name = "site")
        private String site;
        @XmlAttribute(name = "vend_num")
        private String vendNum;
        @XmlAttribute(name = "RUSinn")
        private String inn;
        @XmlAttribute(name = "RUSkpp")
        private String kpp;
        @XmlAttribute(name = "curr_code")
        private String currCode;
        @XmlAttribute(name = "vend_lcr_num")
        private String lcrNum;
        @XmlAttribute(name = "issue_date")
        private String date;

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

        public String getCurrCode() {
            return currCode;
        }

        public void setCurrCode(String currCode) {
            this.currCode = currCode;
        }

        public String getLcrNum() {
            return lcrNum;
        }

        public void setLcrNum(String lcrNum) {
            this.lcrNum = lcrNum;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
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

    public VendLcr getVendLcr() {
        return vendLcr;
    }

    public void setVendLcr(VendLcr vendLcr) {
        this.vendLcr = vendLcr;
    }
}
