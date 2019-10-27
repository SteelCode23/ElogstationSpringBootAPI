package com.elogstation.api.elogstationapi.db;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "elds")
public class Eld extends AuditModel {

    @Id
    @GeneratedValue(generator = "id_generator")
    @SequenceGenerator(
            name = "id_generator",
            sequenceName = "id_sequence",
            initialValue = 1000
    )
    private Long id;

    @Size(min = 1, max = 100)
    private String eldId;

    @Size(min = 1, max = 100)
    String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
