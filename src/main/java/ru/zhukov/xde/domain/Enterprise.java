package ru.zhukov.xde.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by Gukov on 29.05.2017.
 */
/*@Data
@EqualsAndHashCode(of = "name")
@ToString
*/
public class Enterprise {
    private final String name;
    private final String nameDatabase;

    public Enterprise(String name, String nameDatabase) {
        this.name = name;
        this.nameDatabase = nameDatabase;
    }

    public String getName() {
        return name;
    }

    public String getNameDatabase() {
        return nameDatabase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enterprise)) return false;

        Enterprise that = (Enterprise) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
