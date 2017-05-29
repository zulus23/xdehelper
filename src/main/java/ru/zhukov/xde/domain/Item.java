package ru.zhukov.xde.domain;


/**
 * Created by Gukov on 26.05.2017.
 */

import lombok.*;

@Data
@EqualsAndHashCode(of = "item")
public class Item {

    private String item;
    private String name;
    private String nameFull;
    private String edizm;
    private String nds;
    private String parent;

}
