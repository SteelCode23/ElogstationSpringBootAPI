package com.elogstation.api.elogstationapi.repo;

import com.elogstation.api.elogstationapi.db.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface StatusRepo extends JpaRepository<Status, Long> {

//    List<Status> findAllByUserSubAndUserDeviceIdAndDeviceTimeGreaterThanEqualAndDeviceTimeLessThanEqualOrderByDeviceTimeDesc(String sub,
//                    String deviceId, Date fromTime, Date toTime);
//
    Status findFirstBySubAndDeviceIdOrderByDeviceTimeDesc(String sub,
                                                          String deviceId);

//    List<Status> findAllByStatusUser_SubAndDeviceTimeGreaterThanEqualAndDeviceTimeLessThanEqualOrderByDeviceTimeDesc(
//            String sub, Date fromTime, Date toTime);

    List<Status> findAllBySubAndDeviceTimeGreaterThanEqualAndDeviceTimeLessThanEqualOrderByDeviceTimeDesc(
            String sub, Date fromTime, Date toTime);

//    List<Status> findAll();

}
