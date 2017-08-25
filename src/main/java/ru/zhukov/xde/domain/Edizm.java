package ru.zhukov.xde.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "u_m")
public class Edizm {
    @Id
    @Column(name = "u_m")
    private String id;
    @Column(name = "description")
    private  String description;
    @Column(name = "Uf_Code_Sync")
    private String codeSyncWint1C;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCodeSyncWint1C() {
        return codeSyncWint1C;
    }

    public void setCodeSyncWint1C(String codeSyncWint1C) {
        this.codeSyncWint1C = codeSyncWint1C;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edizm edizm = (Edizm) o;

        return getId().equals(edizm.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
