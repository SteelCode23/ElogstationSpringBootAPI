package com.elogstation.api.elogstationapi;

import com.elogstation.api.elogstationapi.db.*;
import com.elogstation.api.elogstationapi.repo.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class ElogstationapiApplication {

    @Autowired
    UserRepo userRepo;
    @Autowired
    AdminUserRepo adminUserRepo;
    @Autowired
    BlockedUserRepo blockedUserRepo;
    @Autowired
    OrgRepo orgRepo;
    @Autowired
    EldRepo eldRepo;
    @Autowired
    PersonRepo personRepo;
    @Autowired
    Person_OrgRepo person_orgRepo;
    @Autowired
    Eld_OrgRepo eld_orgRepo;
    @Autowired
    StatusRepo statusRepo;
    @Autowired
    TrackingRepo trackingRepo;




	public static void main(String[] args) {
		SpringApplication.run(ElogstationapiApplication.class, args);

	}

    @Bean
    InitializingBean sendDatabase() {
        return () -> {

//            BlockedUser blockedUser = new BlockedUser ();
//            blockedUser.setSub("104979804300666828433");
//            blockedUserRepo.save(blockedUser);

            AdminUser adminUserExists = adminUserRepo.findBySub("104979804300666828433");
            if(adminUserExists == null) {
                AdminUser adminUser = new AdminUser();
                adminUser.setSub("104979804300666828433");
                adminUserRepo.save(adminUser);
            }

//            User user1 = new User();
//            user1.setSub("104979804300666828433");
//            user1.setDeviceId("abcde");
//
//            User user2 = new User();
//            user2.setSub("100002");
//            user2.setDeviceId("90002");
//            User user3 = new User();
//            user3.setSub("100003");
//            user3.setDeviceId("90003");
//
//            userRepo.save(user1);
//            userRepo.save(user2);
//            userRepo.save(user3);
//
//            Org org = new Org();
//            org.setName("New Org");
//            Org org2 = new Org();
//            org2.setName("Another Org");
//
//            org = orgRepo.save(org);
//            org2 = orgRepo.save(org2);
//
//            Person_Org person_org = new Person_Org();
//            person_org.setPersonSub("104979804300666828433");
//            person_org.setOrgId(org.getId());
//            person_org.setMemberType(MemberType.MEMBER);
//
//            person_orgRepo.save(person_org);
//
//            Person_Org person_org2 = new Person_Org();
//            person_org2.setPersonSub("104979804300666828433");
//            person_org2.setOrgId(org2.getId());
//            person_org2.setMemberType(MemberType.ADMIN);
//
//            person_orgRepo.save(person_org2);
//
//
//            Eld eld1 = new Eld();
//            eld1.setName("Toyota");
//            eld1.setEldId("240AC487B34A");
//            Eld eld2 = new Eld();
//            eld2.setName("Lexus");
//            eld2.setEldId("xyz");
//
//
//            eld1 = eldRepo.save(eld1);
//            eld2 = eldRepo.save(eld2);
//
//            Eld_Org eld_org = new Eld_Org();
//            eld_org.setEldId(eld1.getEldId());
//            eld_org.setOrgId(org2.getId());
//
//            eld_orgRepo.save(eld_org);
//
//
//            Status status = new Status();
//            status.setDeviceId(user1.getDeviceId());
//            status.setSub(user1.getSub());
//            status.setStatusType(StatusType.DRIVING);
//            status.setDeviceTime(new Date());
//
//            statusRepo.save(status);

//            status = new Status();
//            status.setDeviceId(user1.getDeviceId());
//            status.setSub(user1.getSub());
//            status.setStatusType(StatusType.NOTDRIVING);
//            SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
//            status.setDeviceTime(format.parse("06/11/2018"));
//
//            statusRepo.save(status);
//
//            Tracking tracking = new Tracking();
//            tracking.setDeviceId(user1.getDeviceId());
//            tracking.setSub(user1.getSub());
//            tracking.setEldId(eld1.getEldId());
//            tracking.setLatitude(10d);
//            tracking.setLongitude(11d);
//            tracking.setDeviceTime(format.parse("06/11/2018"));
//
//            trackingRepo.save(tracking);
//
//            tracking = new Tracking();
//            tracking.setDeviceId(user1.getDeviceId());
//            tracking.setSub(user1.getSub());
//            tracking.setEldId(eld1.getEldId());
//            tracking.setLatitude(12d);
//            tracking.setLongitude(11d);
//            tracking.setDeviceTime(format.parse("05/11/2018"));
//
//            trackingRepo.save(tracking);

        };
    }

}
