package com.elogstation.api.elogstationapi.repo;

import com.elogstation.api.elogstationapi.db.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface TrackingRepo extends JpaRepository<Tracking, Long> {

//    List<Tracking> findAllByTrackingUser_SubAndTrackingUser_DeviceIdAndDeviceTimeGreaterThanEqualAndDeviceTimeLessThanEqualOrderByDeviceTimeDesc(String sub,
//                        String deviceId, Date fromTime, Date toTime);

    List<Tracking> findAllBySubAndGpsDateTimeGreaterThanEqualAndGpsDateTimeLessThanEqualOrderByGpsDateTimeDesc(
            String sub, Date fromTime, Date toTime);

    List<Tracking> findAllByEldIdAndGpsDateTimeGreaterThanEqualAndGpsDateTimeLessThanEqualOrderByGpsDateTimeDesc(
            String sub, Date fromTime, Date toTime);
}
