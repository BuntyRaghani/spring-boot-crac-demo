package com.example.cracdemo;

import org.crac.Context;
import org.crac.Core;
import org.crac.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.IntStream;

@SpringBootApplication
public class CracDemoApplication implements CommandLineRunner, Resource {

    public static void main(String[] args) {
        SpringApplication.run(CracDemoApplication.class, args);
    }

    public CracDemoApplication() {
        Core.getGlobalContext().register(this);
    }

    @Override
    public void run(String... args) throws Exception {
        // This is a part of the saved state
        long startTime = System.currentTimeMillis();
        for (int counter : IntStream.range(1, 100).toArray()) {
            Thread.sleep(1000);
            long currentTime = System.currentTimeMillis();
            System.out.println("Counter: " + counter + "(passed " + (currentTime - startTime) + " ms)");
            startTime = currentTime;
        }
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
