package com.elogstation.api.elogstationapi.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.elogstation.api.elogstationapi.db.*;
import com.elogstation.api.elogstationapi.repo.*;
import com.elogstation.api.elogstationapi.util.DateRange;
import com.elogstation.api.elogstationapi.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class StatusController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    StatusRepo statusRepo;

    @Autowired
    Person_OrgRepo person_orgRepo;

    @Autowired
    OrgRepo orgRepo;

    @Autowired
    Util util;

    private static final Logger logger = LoggerFactory.getLogger(StatusController.class);


    @PostMapping("/poststatus")
    @ResponseBody
    public ResponseEntity<?> status(Principal principal, @RequestBody Status statusPost) {

        String[] parts = principal.getName().split("-");

        Status status = new Status();
        status.setSub(parts[0]);
        status.setDeviceId(parts[1]);

        status.setStatusType(statusPost.getStatusType());
        status.setDeviceTime(statusPost.getDeviceTime());

        statusRepo.save(status);

        return new ResponseEntity<Status>(status, HttpStatus.OK);
    }

    @PostMapping("/getstatus")
    @ResponseBody
    public ResponseEntity<?> getStatus(Principal principal, @RequestBody DateRange dateRange) {

        String[] parts = principal.getName().split("-");
        boolean accessValid = util.checkAccessPersonToPerson(parts[0], dateRange.getSub());
        List<Status> statuses = null;
        if(accessValid == true) {
            statuses = statusRepo.findAllBySubAndDeviceTimeGreaterThanEqualAndDeviceTimeLessThanEqualOrderByDeviceTimeDesc(
                    dateRange.getSub(), dateRange.getFromTime(), dateRange.getToTime());
        } else {
            logger.info("getstatus: invalid access: from: " + parts[0] + " trying to access " + dateRange.getSub());
        }
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @PostMapping("/getstatuslatest")
    @ResponseBody
    public ResponseEntity<?> getStatus(Principal principal) {

        String[] parts = principal.getName().split("-");
        User user = userRepo.findBySubAndDeviceId(parts[0], parts[1]);

        Status status = statusRepo.findFirstBySubAndDeviceIdOrderByDeviceTimeDesc(
                parts[0], parts[1]);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PostMapping("/admin/getstatus")
    @ResponseBody
    public ResponseEntity<?> getAdminStatus(Principal principal, @RequestBody DateRange dateRange) {

//        String[] parts = principal.getName().split("-");
//        User user = userRepo.findBySubAndDeviceId(parts[0], parts[1]);
//        List<Status> statuses = null;
        List<Status> statuses = statusRepo.findAllBySubAndDeviceTimeGreaterThanEqualAndDeviceTimeLessThanEqualOrderByDeviceTimeDesc(
                dateRange.getSub(), dateRange.getFromTime(), dateRange.getToTime());
//                sub, dateRange.getFromTime(), dateRange.getToTime());


        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }


}
