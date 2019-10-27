package com.elogstation.api.elogstationapi.repo;

import com.elogstation.api.elogstationapi.db.BlockedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedUserRepo extends JpaRepository<BlockedUser, Long> {
    BlockedUser findBySub(String sub);
    void deleteBySub(String sub);
}
