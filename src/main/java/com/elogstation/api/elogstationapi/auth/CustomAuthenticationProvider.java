package com.elogstation.api.elogstationapi.auth;

import com.elogstation.api.elogstationapi.db.AdminUser;
import com.elogstation.api.elogstationapi.db.BlockedUser;
import com.elogstation.api.elogstationapi.repo.AdminUserRepo;
import com.elogstation.api.elogstationapi.repo.BlockedUserRepo;
import com.elogstation.api.elogstationapi.repo.UserRepo;
import com.elogstation.api.elogstationapi.util.Util;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private Util util;

    @Autowired
    private AdminUserRepo adminUserRepo;

    private static final Logger logger =     LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    public CustomAuthenticationProvider() {

        logger.info("*** CustomAuthenticationProvider created");


    }

    @Autowired
    BlockedUserRepo blockedUserRepo;
    @Autowired
    UserRepo userRepo;

    private boolean isActiveUser(String name) {
        //check for blocked users
        BlockedUser blockedUser = blockedUserRepo.findBySub(name);
        try {

            blockedUser.getSub();
            return false;

        } catch (Exception e){
            return true;
        }
    }

    private boolean validateWithGoogle(String sub, String deviceId, String token) {

        GoogleAPIClient googleAPIClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(GoogleAPIClient.class, "https://www.googleapis.com/oauth2/v3");
        try {
            GoogleAPI googleAPI = googleAPIClient.getByTokenId(token);
            //Add user to database
            if(sub.equals(googleAPI.getSub())) {
                util.addPerson(googleAPI);
                util.addUser(googleAPI, deviceId);
                return true;
            } else{
                return false;
            }
        } catch (Exception e) {

            return false;
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

//        System.out.println("sub-deviceId: " + name);
//        System.out.println("password: " + password);

        String[] parts = name.split("-");

        if (isActiveUser(parts[0])) {
            if (validateWithGoogle(parts[0], parts[1], password)) {
                List<GrantedAuthority> grantedAuths = new ArrayList<>();
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

                try {

//                System.out.println("logged in user");
//                System.out.println(parts[0]);
//                System.out.println(adminUserRepo.findBySub(parts[0]).getSub());
                    if (adminUserRepo.findBySub(parts[0]).getSub() != null) {
                        grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                        util.updatePersonAdmin(parts[0], true);
                    }

                } catch (Exception e) {
                    util.updatePersonAdmin(parts[0], false);
                }

                return new UsernamePasswordAuthenticationToken(name, "", grantedAuths);
            } else {
                AuthenticationException ex=new AuthenticationCredentialsNotFoundException("login failed");
                throw ex;
            }
        } else {
            //blocked user
            AuthenticationException ex=new AuthenticationCredentialsNotFoundException("blocked user");
            throw ex;
//            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}