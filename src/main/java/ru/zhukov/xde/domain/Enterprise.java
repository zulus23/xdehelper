package ru.zhukov.xde.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by Gukov on 29.05.2017.
 */
@Data
@EqualsAndHashCode(of = "name")
@ToString

public class Enterprise {
    private final String name;
    private final String nameDatabase;
}
