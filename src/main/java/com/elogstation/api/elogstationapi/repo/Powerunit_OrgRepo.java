package com.elogstation.api.elogstationapi.repo;

import com.elogstation.api.elogstationapi.db.PowerUnit_Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Powerunit_OrgRepo extends JpaRepository<PowerUnit_Org, Long> {
    //    List<Person_Org> findAllByPersonSub(String sub);
    List<PowerUnit_Org> findAll();
    List<PowerUnit_Org> findAllByOrgId(Long id);
    PowerUnit_Org findOneByOrgIdAndPowerunitNumber(Long orgId, String powerunitNumber);
    List<PowerUnit_Org> findAllByOrgIdIn(List<Long> orgIds);
}
