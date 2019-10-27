package com.elogstation.api.elogstationapi.repo;

import com.elogstation.api.elogstationapi.db.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
    Person findBySub(String sub);
}