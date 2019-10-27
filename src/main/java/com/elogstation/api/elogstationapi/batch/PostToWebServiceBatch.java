package com.elogstation.api.elogstationapi.batch;

import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class PostToWebServiceBatch {



    @Autowired
    FMCSAWebServiceClient fmcsaWebServiceClient;

    public final AtomicBoolean enabled = new AtomicBoolean(false);

    @Scheduled(fixedRate = 5000)
    public void execute() {




        if (enabled.get()) {
            // run spring batch here.
            System.out.println("sending...");
            fmcsaWebServiceClient.sendData();
        }
    }


    public boolean toggle() {



        enabled.set(!enabled.get());
        return enabled.get();
    }

}
