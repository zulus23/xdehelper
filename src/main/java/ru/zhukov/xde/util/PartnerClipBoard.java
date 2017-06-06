package ru.zhukov.xde.util;

/**
 * Created by Gukov on 06.06.2017.
 */
public class PartnerClipBoard {
    private String partner;
    private String lcr;

    public PartnerClipBoard(String partner) {
        this(partner,"");
    }
    public PartnerClipBoard(String partner,String lcr) {
        this.partner = partner;
        this.lcr = lcr;
    }


    public String getPartner() {
        return partner;
    }

    public String getLcr() {
        return lcr;
    }
}
