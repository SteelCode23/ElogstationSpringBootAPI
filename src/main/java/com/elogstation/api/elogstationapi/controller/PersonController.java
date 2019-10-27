package com.elogstation.api.elogstationapi.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.elogstation.api.elogstationapi.db.BlockedUser;
import com.elogstation.api.elogstationapi.db.Person;
import com.elogstation.api.elogstationapi.db.User;
import com.elogstation.api.elogstationapi.repo.BlockedUserRepo;
import com.elogstation.api.elogstationapi.repo.PersonRepo;
import com.elogstation.api.elogstationapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class PersonController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PersonRepo personRepo;

    @Autowired
    BlockedUserRepo blockedUserRepo;

    @GetMapping("/admin/person")
    @ResponseBody
    public ResponseEntity<?> person() {

        List<Person> persons = personRepo.findAll();
        List<Person> personsActive = new ArrayList<>();
        for(Person person: persons){
            System.out.println(person.toString());

            try{
                BlockedUser blockedUser = blockedUserRepo.findBySub(person.getSub());
                if(blockedUser == null){
                    personsActive.add(person);
                } else {
                    System.out.println("here2");
                }
            } catch (Exception e){
                System.out.println("here");
            }
        }

        return new ResponseEntity<List<Person>>(personsActive, HttpStatus.OK);
    }

    @GetMapping("/admin/personall")
    @ResponseBody
    public ResponseEntity<?> personAll() {

        List<Person> persons = personRepo.findAll();
        return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
    }

}
