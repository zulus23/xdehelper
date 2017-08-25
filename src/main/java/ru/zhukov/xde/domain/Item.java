package ru.zhukov.xde.domain;


/**
 * Created by Gukov on 26.05.2017.
 */

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "item")
    private String item;
    @Transient
    private String site;
    @Column(name = "description")
    private String description;
    @Column(name = "RUSExtDescription")
    private String rusDescription;
    @OneToOne
    @JoinColumn(name = "u_m")
    private Edizm edizm;//codeSync;
    @Column(name = "tax_code1")
    private String tax;
    @Column(name = "product_code")
    private String productCode;
    @Column(name = "comm_code")
    private String comment;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRusDescription() {
        return rusDescription;
    }

    public void setRusDescription(String rusDescription) {
        this.rusDescription = rusDescription;
    }

    public Edizm getEdizm() {
        return edizm;
    }

    public void setEdizm(Edizm edizm) {
        this.edizm = edizm;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item1 = (Item) o;

        return getItem().equals(item1.getItem());
    }

    @Override
    public int hashCode() {
        return getItem().hashCode();
    }
}
