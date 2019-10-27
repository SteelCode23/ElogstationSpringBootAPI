package com.elogstation.api.elogstationapi.db;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "elds_orgs")
public class Eld_Org {

    @Id
    @GeneratedValue(generator = "id_generator")
    @SequenceGenerator(
            name = "id_generator",
            sequenceName = "id_sequence",
            initialValue = 1000
    )
    private Long id;

    private String eldId;

    private Long orgId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEldId() {
        return eldId;
    }

    public void setEldId(String eldId) {
        this.eldId = eldId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "Eld_Org{" +
                "id=" + id +
                ", eldId='" + eldId + '\'' +
                ", orgId=" + orgId +
                '}';
    }
}
