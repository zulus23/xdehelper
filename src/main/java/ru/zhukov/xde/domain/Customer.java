package ru.zhukov.xde.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Gukov on 05.06.2017.
 */
@Data
@EqualsAndHashCode(of = "custNum")
public class Customer {

    private String site;
    private String custNum;
    private String inn;
    private String kpp;
    private String name;
    private String fullName;
    private String countryCode;
    private String okpo;
    private String address;
    private String zip;

}
