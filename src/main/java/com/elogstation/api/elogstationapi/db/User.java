package com.elogstation.api.elogstationapi.db;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity @IdClass(UsersId.class)
@Table(name = "users")
public class User extends AuditModel {

    @Id
    @Size(min = 3, max = 100)
    private String sub;

    @Id
    @Size(min = 3, max = 100)
    private String deviceId;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }



}
