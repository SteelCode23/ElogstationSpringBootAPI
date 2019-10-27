package com.elogstation.api.elogstationapi.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.elogstation.api.elogstationapi.db.*;
import com.elogstation.api.elogstationapi.repo.*;
import com.elogstation.api.elogstationapi.util.DateRange;
import com.elogstation.api.elogstationapi.util.EldNameAndOrgId;
import com.elogstation.api.elogstationapi.util.EldNameAndPowerunit;
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
public class TrackingAndEldController {

    private static final Logger logger = LoggerFactory.getLogger(TrackingAndEldController.class);

    @Autowired
    UserRepo userRepo;

    @Autowired
    TrackingRepo trackingRepo;

    @Autowired
    Person_OrgRepo person_orgRepo;

    @Autowired
    Eld_OrgRepo eld_orgRepo;

    @Autowired
    EldRepo eldRepo;

    @Autowired
    OrgRepo orgRepo;

    @Autowired
    Util util;

    @Autowired
    Powerunit_OrgRepo powerunit_orgRepo;


    @PostMapping("/posttracking")
    @ResponseBody
    public ResponseEntity<?> tracking(Principal principal, @RequestBody List<Tracking> trackings) {

        String[] parts = principal.getName().split("-");
        User user = userRepo.findBySubAndDeviceId(parts[0], parts[1]);

        List<String> externalIds = new ArrayList<String>();

        for (Tracking tracking : trackings) {
            tracking.setSub(parts[0]);
            tracking.setDeviceId(parts[1]);
            trackingRepo.save(tracking);
            externalIds.add(tracking.getExternalId());
        }

        logger.info("Audit :: User :: " + parts[0] + "-" + parts[1] + " :: Tracking :: " + externalIds.size());

        return new ResponseEntity<>(externalIds, HttpStatus.OK);
    }

    @PostMapping("/gettracking")
    @ResponseBody
    public ResponseEntity<?> getTracking(Principal principal, @RequestBody DateRange dateRange) {

        String[] parts = principal.getName().split("-");
        User user = userRepo.findBySubAndDeviceId(parts[0], parts[1]);


        boolean accessValid = util.checkAccessPersonToPerson(parts[0], dateRange.getSub());
        List<Tracking> trackings  =null;
        if(accessValid == true) {
            trackings = trackingRepo.findAllBySubAndGpsDateTimeGreaterThanEqualAndGpsDateTimeLessThanEqualOrderByGpsDateTimeDesc(
                    dateRange.getSub(), dateRange.getFromTime(), dateRange.getToTime());
        } else {
            logger.info("gettracking: invalid access: from: " + parts[0] + " trying to access " + dateRange.getSub());
        }

        return new ResponseEntity<>(trackings, HttpStatus.OK);
    }


    @PostMapping("/admin/gettracking")
    @ResponseBody
    public ResponseEntity<?> getTrackingAdmin(Principal principal, @RequestBody DateRange dateRange) {

        List<Tracking> trackings = trackingRepo.findAllBySubAndGpsDateTimeGreaterThanEqualAndGpsDateTimeLessThanEqualOrderByGpsDateTimeDesc(
                dateRange.getSub(), dateRange.getFromTime(), dateRange.getToTime());

        return new ResponseEntity<>(trackings, HttpStatus.OK);
    }

    @PostMapping("/gettrackingforeld")
    @ResponseBody
    public ResponseEntity<?> getTrackingForEld(Principal principal, @RequestBody DateRange dateRange) {

        String[] parts = principal.getName().split("-");
        boolean accessValid = util.checkAccessPersonToEld(parts[0], dateRange.getSub());
        List<Tracking> trackings  =null;
        if(accessValid == true) {
            trackings = trackingRepo.findAllByEldIdAndGpsDateTimeGreaterThanEqualAndGpsDateTimeLessThanEqualOrderByGpsDateTimeDesc(
                    dateRange.getSub(), dateRange.getFromTime(), dateRange.getToTime());
        } else {
            logger.info("gettrackingforeld: invalid access: from: " + parts[0] + " trying to access " + dateRange.getSub());
        }

        return new ResponseEntity<>(trackings, HttpStatus.OK);
    }

    @PostMapping("/admin/gettrackingforeld")
    @ResponseBody
    public ResponseEntity<?> getTrackingAdminForEld(Principal principal, @RequestBody DateRange dateRange) {

        List<Tracking> trackings = trackingRepo.findAllByEldIdAndGpsDateTimeGreaterThanEqualAndGpsDateTimeLessThanEqualOrderByGpsDateTimeDesc(
                dateRange.getSub(), dateRange.getFromTime(), dateRange.getToTime());

        return new ResponseEntity<>(trackings, HttpStatus.OK);
    }

    @PostMapping("/admin/gettrackingforeldall")
    @ResponseBody
    public ResponseEntity<?> getTrackingAdminForEldAll(Principal principal, @RequestBody DateRange dateRange) {

//        List<Tracking> trackings = trackingRepo.findAllByEldIdAndGpsDateTimeGreaterThanEqualAndGpsDateTimeLessThanEqualOrderByGpsDateTimeDesc(
//                dateRange.getSub(), dateRange.getFromTime(), dateRange.getToTime());
        List<Tracking> trackings = trackingRepo.findAll();
        return new ResponseEntity<>(trackings, HttpStatus.OK);
    }

    @PostMapping("/getmyelds")
    @ResponseBody
    public ResponseEntity<?> getMyElds(Principal principal) {

        String[] parts = principal.getName().split("-");

        List<Person_Org> persons_orgs = person_orgRepo.findAllByPersonSub(parts[0]);
        List<Long> orgs = new ArrayList<>();
        for(Person_Org person_org: persons_orgs){
            orgs.add(person_org.getOrgId());
        }
        List<Eld_Org> elds_orgs = eld_orgRepo.findAllByOrgIdIn(orgs);
        return new ResponseEntity<>(elds_orgs, HttpStatus.OK);
    }


    @PostMapping("/admin/createneweld")
    ResponseEntity<?> createNewOrg(Principal principal, @RequestBody EldNameAndOrgId eldNameAndOrgId) {
        boolean isSuccess = false;
        try {
            Long id = Long.parseLong(eldNameAndOrgId.getOrgId());
            Eld_Org eld_org_old = eld_orgRepo.findOneByOrgIdAndEldId(id, eldNameAndOrgId.getEldName());
            if(eld_org_old == null) {
                Org org = orgRepo.getOne(id);

                Eld_Org eld_org = new Eld_Org();
                eld_org.setEldId(eldNameAndOrgId.getEldName());
                eld_org.setOrgId(org.getId());

                eld_orgRepo.save(eld_org);

                isSuccess = true;
            }else {
                System.out.println(eld_org_old.toString());
            }
        } catch (Exception e){
            System.out.println(e);
        }

        return new ResponseEntity<Boolean>(isSuccess, HttpStatus.OK);
    }

    @PostMapping("/admin/deleteeld")
    ResponseEntity<?> deleteOrg(Principal principal, @RequestBody EldNameAndOrgId eldNameAndOrgId) {

        boolean isSuccess = false;
        try {
            Long orgId = Long.parseLong(eldNameAndOrgId.getOrgId());

            Eld_Org eld_org = eld_orgRepo.findOneByOrgIdAndEldId(orgId, eldNameAndOrgId.getEldId());
            if(eld_org != null) {
                eld_orgRepo.deleteById(eld_org.getId());
                isSuccess = true;
            }

        } catch (Exception e){}

        return new ResponseEntity<Boolean>(isSuccess, HttpStatus.OK);
    }

    @PostMapping("/admin/createnewpowerunit")
    ResponseEntity<?> createNewPowerUnit(Principal principal, @RequestBody EldNameAndPowerunit eldNameAndPowerunit) {
        boolean isSuccess = false;
        try {
            Long id = Long.parseLong(eldNameAndPowerunit.getOrgId());
            PowerUnit_Org powerUnit_org_old = powerunit_orgRepo.findOneByOrgIdAndPowerunitNumber(id, eldNameAndPowerunit.getPowerunitNumber());
            if(powerUnit_org_old == null) {
                Org org = orgRepo.getOne(id);

                PowerUnit_Org powerUnit_org = new PowerUnit_Org();

                powerUnit_org.setPowerunitNumber(eldNameAndPowerunit.getPowerunitNumber());
                powerUnit_org.setTrailerNumber(eldNameAndPowerunit.getTrailerNumber());
                powerUnit_org.setVin(eldNameAndPowerunit.getVin());
                powerUnit_org.setOrgId(org.getId());

                powerunit_orgRepo.save(powerUnit_org);

                isSuccess = true;
            }else {
                System.out.println(powerUnit_org_old.toString());
            }
        } catch (Exception e){
            System.out.println(e);
        }

        return new ResponseEntity<Boolean>(isSuccess, HttpStatus.OK);
    }

    @PostMapping("/admin/deletepowerunit")
    ResponseEntity<?> deletePowerUnit(Principal principal, @RequestBody EldNameAndPowerunit eldNameAndPowerunit) {

        boolean isSuccess = false;
        try {
            Long orgId = Long.parseLong(eldNameAndPowerunit.getOrgId());

            PowerUnit_Org powerUnit_org_old = powerunit_orgRepo.findOneByOrgIdAndPowerunitNumber(orgId, eldNameAndPowerunit.getPowerunitNumber());

            if(powerUnit_org_old != null) {
                powerunit_orgRepo.deleteById(powerUnit_org_old.getId());
                isSuccess = true;
            }

        } catch (Exception e){}

        return new ResponseEntity<Boolean>(isSuccess, HttpStatus.OK);
    }

}