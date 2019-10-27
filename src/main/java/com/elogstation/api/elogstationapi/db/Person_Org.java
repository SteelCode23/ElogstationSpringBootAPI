package com.elogstation.api.elogstationapi.db;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "persons_orgs")
public class Person_Org {

    @Id
    @GeneratedValue(generator = "id_generator")
    @SequenceGenerator(
            name = "id_generator",
            sequenceName = "id_sequence",
            initialValue = 1000
    )
    private Long id;

    @Size(min = 3, max = 100)
    private String personSub;

    private Long orgId;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonSub() {
        return personSub;
    }

    public void setPersonSub(String personSub) {
        this.personSub = personSub;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }
}
