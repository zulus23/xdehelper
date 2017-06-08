package ru.zhukov.xde.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Gukov on 07.06.2017.
 */
@Data
@EqualsAndHashCode(of = {"vendNum","lcrNum"})
public class VendorLcr {
    private String site;
    private String vendNum;
    private String inn;
    private String kpp;
    private String lcrNum;
    private String date;
    private String currentCode;
}
