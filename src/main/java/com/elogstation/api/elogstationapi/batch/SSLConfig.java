package com.elogstation.api.elogstationapi.batch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SSLConfig {

    @Value("${javax.net.ssl.keyStoreType}")
    private String keyStoreType;
    @Value("${javax.net.ssl.keyStore}")
    private String keyStore;
    @Value("${javax.net.ssl.keyStorePassword}")
    private String keyStorePassword;
    @Value("${javax.net.ssl.trustStore}")
    private String trustStore;
    @Value("${javax.net.ssl.trustStorePassword}")
    private String trustStorePassword;

    @PostConstruct
    void setSSLConfig(){
//        System.setProperty("javax.net.ssl.keyStoreType", keyStoreType);
//        System.setProperty("javax.net.ssl.keyStore",  keyStore);
//        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
//        System.setProperty("javax.net.ssl.trustStore", trustStore);
//        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
//        System.out.println("trustStorePassword" + trustStorePassword);
    }
}

