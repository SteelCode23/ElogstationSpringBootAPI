package com.elogstation.api.elogstationapi.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.elogstation.api.elogstationapi.db.BlockedUser;
import com.elogstation.api.elogstationapi.db.Person;
import com.elogstation.api.elogstationapi.db.User;
import com.elogstation.api.elogstationapi.repo.PersonRepo;
import com.elogstation.api.elogstationapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PersonRepo personRepo;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(Principal principal) {

        String[] parts = principal.getName().split("-");
        Person person = personRepo.findBySub(parts[0]);

        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

}