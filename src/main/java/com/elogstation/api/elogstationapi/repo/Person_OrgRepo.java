package com.elogstation.api.elogstationapi.repo;

import com.elogstation.api.elogstationapi.db.MemberType;
import com.elogstation.api.elogstationapi.db.Person_Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Person_OrgRepo extends JpaRepository<Person_Org, Long> {
    List<Person_Org> findAllByPersonSub(String sub);
    List<Person_Org> findAll();
    List<Person_Org> findAllByOrgId(Long id);
    List<Person_Org> findAllByOrgIdAndMemberType(Long id, MemberType memberType);
    Person_Org findByOrgIdAndPersonSub(Long id, String sub);
    List<Person_Org> findAllByPersonSubAndMemberType(String sub, MemberType memberType);

}