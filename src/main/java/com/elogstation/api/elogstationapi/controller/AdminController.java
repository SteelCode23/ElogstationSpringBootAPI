package com.elogstation.api.elogstationapi.controller;

import com.elogstation.api.elogstationapi.batch.PostToWebServiceBatch;
import com.elogstation.api.elogstationapi.db.AdminUser;
import com.elogstation.api.elogstationapi.db.BlockedUser;
import com.elogstation.api.elogstationapi.db.Org;
import com.elogstation.api.elogstationapi.repo.AdminUserRepo;
import com.elogstation.api.elogstationapi.repo.BlockedUserRepo;
import jdk.nashorn.internal.ir.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class AdminController {

    @Autowired
    PostToWebServiceBatch postToWebServiceBatch;

    @Autowired
    BlockedUserRepo blockedUserRepo;

    @Autowired
    AdminUserRepo adminUserRepo;

    @GetMapping("/admin/launch")
    ResponseEntity<?> toggle() {
        return new ResponseEntity<>(postToWebServiceBatch.toggle(), HttpStatus.OK);
    }

        @PostMapping("/admin/addtoblockedusers")
    ResponseEntity<?> addToBlockedUsers(Principal principal, @RequestBody String sub) {
        boolean isSuccess = false;
        try {
            BlockedUser blockedUser = new BlockedUser();
            blockedUser.setSub(sub);
            blockedUserRepo.save(blockedUser);
            isSuccess = true;
        }catch (Exception e){}
        return new ResponseEntity<Boolean>(isSuccess, HttpStatus.OK);
    }

    @PostMapping("/admin/addtosuperusers")
    ResponseEntity<?> addToSuperUsers(Principal principal, @RequestBody String sub) {
        boolean isSuccess = false;
        try {
            AdminUser adminUser = new AdminUser();
            adminUser.setSub(sub);
            adminUserRepo.save(adminUser);
            isSuccess = true;
        }catch (Exception e){}
        return new ResponseEntity<Boolean>(isSuccess, HttpStatus.OK);
    }

    @PostMapping("/admin/deletefromblockedusers")
    ResponseEntity<?> deleteFromBlockedUsers(Principal principal, @RequestBody String sub) {
        boolean isSuccess = false;
        try {
            BlockedUser blockedUser = blockedUserRepo.findBySub(sub);
            blockedUserRepo.delete(blockedUser);
            isSuccess = true;
        }catch (Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<Boolean>(isSuccess, HttpStatus.OK);
    }

    @PostMapping("/admin/deletefromsuperusers")
    ResponseEntity<?> deleteFromSuperUsers(Principal principal, @RequestBody String sub) {
        boolean isSuccess = false;
        try {
            AdminUser adminUser = adminUserRepo.findBySub(sub);
            adminUserRepo.delete(adminUser);
            isSuccess = true;
        }catch (Exception e){}
        return new ResponseEntity<Boolean>(isSuccess, HttpStatus.OK);
    }

    @PostMapping("/admin/getallblockedusers")
    ResponseEntity<?> getAllBlockedUsers(Principal principal) {
        List<BlockedUser> blockedUser = blockedUserRepo.findAll();
        return new ResponseEntity<>(blockedUser, HttpStatus.OK);
    }

    @PostMapping("/admin/getallsuperusers")
    ResponseEntity<?> getAllSuperUsers(Principal principal) {
        List<AdminUser> adminUser = adminUserRepo.findAll();
        return new ResponseEntity<>(adminUser, HttpStatus.OK);
    }



}
