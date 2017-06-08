package ru.zhukov.xde.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by Gukov on 07.06.2017.
 * in name root element character 'С' russian
 */

@XmlRootElement(name = "custlcr_1С_gotek")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerLcrXML {

    @XmlAttribute(name = "seq")
    private  String seq;
    @XmlAttribute(name = "action")
    private  String action;
    @XmlElement(name = "RUSXDECustlcrView")
    private CustLcr custLcr;

    @XmlRootElement(name = "RUSXDECustlcrView")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CustLcr {
        @XmlAttribute(name = "site")
        private String site;
        @XmlAttribute(name = "cust_num")
        private String custNum;
        @XmlAttribute(name = "RUSinn")
        private String inn;
        @XmlAttribute(name = "RUSkpp")
        private String kpp;
        @XmlAttribute(name = "curr_code")
        private String currCode;
        @XmlAttribute(name = "lcr_num")
        private String lcrNum;
        @XmlAttribute(name = "issue_date")
        private String date;


        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getCustNum() {
            return custNum;
        }

        public void setCustNum(String custNum) {
            this.custNum = custNum;
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

    public CustLcr getCustLcr() {
        return custLcr;
    }

    public void setCustLcr(CustLcr custLcr) {
        this.custLcr = custLcr;
    }
}
