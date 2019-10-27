package com.elogstation.api.elogstationapi.repo;

import com.elogstation.api.elogstationapi.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findBySubAndDeviceId(String sub, String deviceId);
//    Set<User> findBySub(String sub);

}