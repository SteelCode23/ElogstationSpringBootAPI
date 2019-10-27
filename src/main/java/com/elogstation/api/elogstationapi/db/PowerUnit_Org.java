package com.elogstation.api.elogstationapi.db;

import javax.persistence.*;

@Entity
@Table(name = "powerunits_orgs")
public class PowerUnit_Org {

    @Id
    @GeneratedValue(generator = "id_generator")
    @SequenceGenerator(
            name = "id_generator",
            sequenceName = "id_sequence",
            initialValue = 1000
    )
    private Long id;

    private Long orgId;

    private String powerunitNumber;

    private String vin;

    private String trailerNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPowerunitNumber() {
        return powerunitNumber;
    }

    public void setPowerunitNumber(String powerunitNumber) {
        this.powerunitNumber = powerunitNumber;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getTrailerNumber() {
        return trailerNumber;
    }

    public void setTrailerNumber(String trailerNumber) {
        this.trailerNumber = trailerNumber;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "PowerUnit_Org{" +
                "id=" + id +
                ", powerunitNumber='" + powerunitNumber + '\'' +
                ", vin='" + vin + '\'' +
                ", trailerNumber='" + trailerNumber + '\'' +
                '}';
    }
}