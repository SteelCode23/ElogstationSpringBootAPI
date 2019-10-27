package com.elogstation.api.elogstationapi.controller;


import com.elogstation.api.elogstationapi.batch.PostToWebServiceBatch;
import com.elogstation.api.elogstationapi.db.*;
import com.elogstation.api.elogstationapi.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
public class OrgController {

    @Autowired
    OrgRepo orgRepo;
    @Autowired
    Person_OrgRepo person_orgRepo;
    @Autowired
    PersonRepo personRepo;
    @Autowired
    EldRepo eldRepo;
    @Autowired
    Eld_OrgRepo eld_orgRepo;

    @GetMapping("/getorginfo")
    ResponseEntity<?> getOrgInfo(Principal principal) {

        String[] parts = principal.getName().split("-");

        List<Org> orgs = new ArrayList<>();
        List<Person_Org> persons_orgs_list = person_orgRepo.findAllByPersonSubAndMemberType(parts[0], MemberType.ADMIN);
        for (int j = 0; j < persons_orgs_list.size(); j++) {
            orgs.add(orgRepo.findById(persons_orgs_list.get(j).getOrgId()).get());
        }

        System.out.println("persons_orgs_list"+persons_orgs_list.size());
        System.out.println("org"+orgs.size());

        orgs = setOrgInfo(orgs);

        return new ResponseEntity<>(orgs, HttpStatus.OK);
    }

    @PostMapping("/admin/createneworg")
    ResponseEntity<?> createNewOrg(Principal principal, @RequestBody String orgName) {
        boolean isSuccess = false;
        try {
            Org org = new Org();
            org.setName(orgName.trim());
            org = orgRepo.save(org);
            isSuccess = true;
        } catch (Exception e){}

        return new ResponseEntity<Boolean>(isSuccess, HttpStatus.OK);
    }

    @PostMapping("/admin/createneworgnew")
    ResponseEntity<?> createNewOrgNew(Principal principal, @RequestBody Org org) {
        boolean isSuccess = false;
        try {
            org.setName(org.getName().trim());
            org = orgRepo.save(org);
            isSuccess = true;
        } catch (Exception e){}

        return new ResponseEntity<Boolean>(isSuccess, HttpStatus.OK);
    }

    @PostMapping("/admin/deleteorg")
    ResponseEntity<?> deleteOrg(Principal principal, @RequestBody String  orgId) {

        boolean isSuccess = false;
        try {
            Long id = Long.parseLong(orgId);
            if (orgRepo.existsById(id)) {
                orgRepo.deleteById(id);
                isSuccess = true;
            }
        } catch (Exception e){}

        return new ResponseEntity<Boolean>(isSuccess, HttpStatus.OK);
    }

    @GetMapping("/admin/getorginfo")
    ResponseEntity<?> getOrgInfoAdmin(Principal principal) {

        String[] parts = principal.getName().split("-");

        List<Org> orgs = orgRepo.findAll();

        orgs = setOrgInfo(orgs);

        return new ResponseEntity<>(orgs, HttpStatus.OK);
    }

    private List<Org> setOrgInfo(List<Org> orgs){

        for(int i = 0; i < orgs.size(); i++){

            List<Person_Org> persons_orgs_list = person_orgRepo.findAllByOrgIdAndMemberType(orgs.get(i).getId(), MemberType.MEMBER);
            Set<Person> list_person = new HashSet<>();
            for (int j = 0; j < persons_orgs_list.size(); j++) {
                System.out.println("getPersonSub:" + persons_orgs_list.get(j).getPersonSub());
                System.out.println("getId:" + persons_orgs_list.get(j).getId());
                list_person.add(persons_orgs_list.get(j) != null ?
                        personRepo.findBySub(persons_orgs_list.get(j).getPersonSub()) : null);
            }
            orgs.get(i).setMembers(list_person);

            persons_orgs_list = person_orgRepo.findAllByOrgIdAndMemberType(orgs.get(i).getId(), MemberType.ADMIN);
            list_person = new HashSet<>();
            for (int j = 0; j < persons_orgs_list.size(); j++) {
                System.out.println("getPersonSub:" + persons_orgs_list.get(j).getPersonSub());
                System.out.println("getId:" + persons_orgs_list.get(j).getId());
                list_person.add(persons_orgs_list.get(j) != null ?
                        personRepo.findBySub(persons_orgs_list.get(j).getPersonSub()) : null);
            }
            orgs.get(i).setAdmins(list_person);

            List<Eld_Org> elds_orgs_list = eld_orgRepo.findAllByOrgId(orgs.get(i).getId());
            Set<Eld> list_eld = new HashSet<>();
            for (int j = 0; j < elds_orgs_list.size(); j++) {
                Eld eld = new Eld();
                if(elds_orgs_list.get(j).getEldId() != null) {
                    eld.setName(elds_orgs_list.get(j).getEldId());
                    list_eld.add(eld);
                }
            }
            orgs.get(i).setElds(list_eld);

        }
        return orgs;
    }

    @PostMapping("/admin/addusertogroup")
    ResponseEntity<?> addUserToGroup(Principal principal, @RequestBody Person_Org person_org) {
        boolean isSuccess = false;
        try {
            Person_Org person_org_old = person_orgRepo.findByOrgIdAndPersonSub(person_org.getOrgId(), person_org.getPersonSub());
            if(person_org_old != null){
                person_orgRepo.deleteById(person_org_old.getId());
            }
            person_orgRepo.save(person_org);
            isSuccess = true;
        } catch (Exception e){}
        return new ResponseEntity<Boolean>(isSuccess, HttpStatus.OK);
    }


    @PostMapping("/admin/deleteuserfromgroup")
    ResponseEntity<?> deleteUserFromGroup(Principal principal, @RequestBody Person_Org person_org) {
        boolean isSuccess = false;
        try {
            Person_Org person_org_old = person_orgRepo.findByOrgIdAndPersonSub(person_org.getOrgId(), person_org.getPersonSub());
            if(person_org_old != null){
                person_orgRepo.deleteById(person_org_old.getId());
                isSuccess = true;
            }
        } catch (Exception e){}
        return new ResponseEntity<Boolean>(isSuccess, HttpStatus.OK);
    }

}
