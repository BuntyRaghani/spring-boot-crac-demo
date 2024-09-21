package com.example.cracdemo;

import org.crac.Context;
import org.crac.Core;
import org.crac.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CracDemoApplication implements Resource {

    public static void main(String[] args) {
        SpringApplication.run(CracDemoApplication.class, args);
    }

    public CracDemoApplication() {
        Core.getGlobalContext().register(this);
    }

    @Override
    public void beforeCheckpoint(Context<? extends Resource> context) {
        System.out.println("CRaC's beforeCheckpoint callback method called...");
    }

    @Override
    public void afterRestore(Context<? extends Resource> context) {
        System.out.println("CRaC's afterRestore callback method called...");
    }
}
