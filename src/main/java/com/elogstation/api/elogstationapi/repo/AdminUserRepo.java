package com.elogstation.api.elogstationapi.repo;

import com.elogstation.api.elogstationapi.db.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepo extends JpaRepository<AdminUser, Long> {
    AdminUser findBySub(String sub);
    void deleteBySub(String sub);
}
