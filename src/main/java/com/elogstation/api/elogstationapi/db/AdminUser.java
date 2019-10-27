package com.elogstation.api.elogstationapi.db;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "adminusers")
public class AdminUser extends AuditModel {

    @Id
    @Size(min = 3, max = 100)
    private String sub;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}
