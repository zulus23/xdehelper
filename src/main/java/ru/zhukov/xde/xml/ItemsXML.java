package ru.zhukov.xde.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gukov on 31.05.2017.
 */
@XmlRootElement(name = "item_1c")
public class ItemsXML {
    @XmlAttribute(name = "seq")

    private  String seq;

    private  List<ItemXML> itm;


    public static class ItemXML {

        @XmlAttribute(name = "site")
        private String site;
        @XmlAttribute(name = "item")
        private String item;
        @XmlAttribute(name = "description")
        private String description;
        @XmlAttribute(name = "RUSExtDescription")
        private String rusExtDescription;
        @XmlAttribute(name = "Uf_Code_Sync")
        private String edizmCode;
        @XmlAttribute(name = "tax_code1")
        private String tax;

        @XmlAttribute(name = "product_code")
        private String groupCode;
        @XmlAttribute(name = "CommConv")
        private String comment;
        public ItemXML(){

        }

        public ItemXML(String site, String item, String description, String rusExtDescription, String edizmCode, String tax, String groupCode, String comment) {
            this.site = site;
            this.item = item;
            this.description = description;
            this.rusExtDescription = rusExtDescription;
            this.edizmCode = edizmCode;
            this.tax = tax;
            this.groupCode = groupCode;
            this.comment = comment;
        }

        public String getSite() {
            return site;
        }

        public String getItem() {
            return item;
        }

        public String getDescription() {
            return description;
        }

        public String getRusExtDescription() {
            return rusExtDescription;
        }

        public String getEdizmCode() {
            return edizmCode;
        }

        public String getGroupCode() {
            return groupCode;
        }

        public String getComment() {
            return comment;
        }

        public String getTax() {
            return tax;
        }
    }

    public ItemsXML(String seq) {
        this.seq = seq;
        this.itm = new ArrayList<>();
    }

    public String getSeq() {
        return seq;
    }

    public List<ItemXML> getItm() {
        return itm;
    }

    public void setItm(List<ItemXML> itm) {
        this.itm = itm;
    }

    public ItemsXML() {
    }
}

