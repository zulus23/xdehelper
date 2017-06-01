package ru.zhukov.xde.domain;


/**
 * Created by Gukov on 26.05.2017.
 */

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@EqualsAndHashCode(of = "item")
public class Item {
    private String site;
    private String item;
    private String description;
    private String rusDescription;
    private String codeSync;
    private String tax;
    private String productCode;
    private String comment;


}
