package com.elogstation.api.elogstationapi.util;

import com.elogstation.api.elogstationapi.auth.GoogleAPI;
import com.elogstation.api.elogstationapi.db.*;
import com.elogstation.api.elogstationapi.repo.*;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class Util {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    Person_OrgRepo person_orgRepo;

    @Autowired
    OrgRepo orgRepo;

    @Autowired
    Eld_OrgRepo eld_orgRepo;


    private static final Logger logger = LoggerFactory.getLogger(Util.class);


    public boolean checkAccessPersonToPerson(String requesting, String accessTo){

        try {
            List<Person_Org> persons_orgs_list = person_orgRepo.findAllByPersonSubAndMemberType(requesting, MemberType.ADMIN);
            List<Org> orgs = new ArrayList<>();
            for (int j = 0; j < persons_orgs_list.size(); j++) {
                Org org = orgRepo.findById(persons_orgs_list.get(j).getOrgId()).get();
                List<Person_Org> persons_orgs_list_all = person_orgRepo.findAllByOrgId(org.getId());

                for (int i = 0; i < persons_orgs_list_all.size(); i++) {
                    if (accessTo.equals(persons_orgs_list_all.get(i).getPersonSub())) {
                        return true;
                    }
                }
            }

        } catch (Exception e){
            logger.info("checkAccessPersonToPerson exception: requesting: " + requesting + " of " + accessTo);
        }

        return false;
    }

    public boolean checkAccessPersonToEld(String requesting, String accessTo){
        try{

            List<Person_Org> persons_orgs_list = person_orgRepo.findAllByPersonSubAndMemberType(requesting, MemberType.ADMIN);
            List<Org> orgs = new ArrayList<>();
            for (int j = 0; j < persons_orgs_list.size(); j++) {
                Org org = orgRepo.findById(persons_orgs_list.get(j).getOrgId()).get();
                List<Eld_Org> elds_orgs_list_all = eld_orgRepo.findAllByOrgId(org.getId());

                for (int i = 0; i < elds_orgs_list_all.size(); i++) {
                    if (accessTo.equals(elds_orgs_list_all.get(i).getEldId())) {
                        return true;
                    }
                }
            }


        } catch (Exception e){
            logger.info("checkAccessPersonToEld exception: requesting: " + requesting + " of " + accessTo);
        }

        return false;
    }

    public User convertGoogleAPIToUser(GoogleAPI googleAPI, String deviceId) {
        User user = new User();
        user.setSub(googleAPI.getSub());
        user.setDeviceId(deviceId);
        return user;
    }

    public void addUser(GoogleAPI googleAPI, String deviceId) {

        try {
            User user = new User();
            user.setSub(googleAPI.getSub());
            user.setDeviceId(deviceId);

            if(userRepo.findBySubAndDeviceId(googleAPI.getSub(), deviceId) == null) {
                userRepo.save(user);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.getStackTrace();
        }
    }

    public void updatePersonAdmin(String sub, boolean isAdmin) {

        try {
            Person person = personRepo.findBySub(sub);
            person.setAdmin(isAdmin);
            personRepo.save(person);
        } catch (Exception e) {
            System.out.println(e);
            e.getStackTrace();
        }

    }

    public void addPerson(GoogleAPI googleAPI) {

        try {
            Person person = personRepo.findBySub(googleAPI.getSub());
            if(person == null){
                Person newPerson = new Person();
                newPerson.setSub(googleAPI.getSub());
                newPerson.setName(googleAPI.getName());
                newPerson.setEmail(googleAPI.getEmail());
                newPerson.setPicture(googleAPI.getPicture());
                personRepo.save(newPerson);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.getStackTrace();
        }

    }
}
