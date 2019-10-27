package com.elogstation.api.elogstationapi.repo;

import com.elogstation.api.elogstationapi.db.Eld_Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Eld_OrgRepo extends JpaRepository<Eld_Org, Long> {
    //    List<Person_Org> findAllByPersonSub(String sub);
    List<Eld_Org> findAll();
    List<Eld_Org> findAllByOrgId(Long id);
    Eld_Org findOneByOrgIdAndEldId(Long orgId, String eldId);
    List<Eld_Org> findAllByOrgIdIn(List<Long> orgIds);
}
