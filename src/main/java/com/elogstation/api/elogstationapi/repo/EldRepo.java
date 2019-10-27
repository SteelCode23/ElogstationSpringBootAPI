package com.elogstation.api.elogstationapi.repo;

import com.elogstation.api.elogstationapi.db.Eld;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EldRepo extends JpaRepository<Eld, Long> {
    Eld findByEldId(String eldId);

}

