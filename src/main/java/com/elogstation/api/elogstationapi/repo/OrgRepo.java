package com.elogstation.api.elogstationapi.repo;

import com.elogstation.api.elogstationapi.db.Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgRepo extends JpaRepository<Org, Long> {
//    List<Org> findByOrgAdminSub(String sub);
    Org findByName(String name);
}
