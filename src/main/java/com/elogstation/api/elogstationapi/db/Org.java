package com.elogstation.api.elogstationapi.db;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "orgs")
public class Org extends AuditModel {

    @Id
    @GeneratedValue(generator = "id_generator")
    @SequenceGenerator(
            name = "id_generator",
            sequenceName = "id_sequence",
            initialValue = 1000
    )
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Size(min = 3, max = 100)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    private Set<Person> members;
    @Transient
    private Set<Person> admins;
    @Transient
    private Set<Eld> elds;

    public Set<Person> getMembers() {
        return members;
    }

    public void setMembers(Set<Person> members) {
        this.members = members;
    }

    public Set<Person> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<Person> admins) {
        this.admins = admins;
    }

    public Set<Eld> getElds() {
        return elds;
    }

    public void setElds(Set<Eld> elds) {
        this.elds = elds;
    }


    private String usdotNumber;
    private int multiDayBasisUsed;

    @JsonFormat(pattern = "HHmmss")
    private Date twentyFourHourPeriodTimeStartingTime;

    private String timeOffsetFromUTC;

    public String getUsdotNumber() {
        return usdotNumber;
    }

    public void setUsdotNumber(String usdotNumber) {
        this.usdotNumber = usdotNumber;
    }

    public int getMultiDayBasisUsed() {
        return multiDayBasisUsed;
    }

    public void setMultiDayBasisUsed(int multiDayBasisUsed) {
        this.multiDayBasisUsed = multiDayBasisUsed;
    }

    public Date getTwentyFourHourPeriodTimeStartingTime() {
        return twentyFourHourPeriodTimeStartingTime;
    }

    public void setTwentyFourHourPeriodTimeStartingTime(Date twentyFourHourPeriodTimeStartingTime) {
        this.twentyFourHourPeriodTimeStartingTime = twentyFourHourPeriodTimeStartingTime;
    }

    public String getTimeOffsetFromUTC() {
        return timeOffsetFromUTC;
    }

    public void setTimeOffsetFromUTC(String timeOffsetFromUTC) {
        this.timeOffsetFromUTC = timeOffsetFromUTC;
    }
}
