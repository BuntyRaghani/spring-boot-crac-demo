package com.example.cracdemo.config;

import com.mongodb.client.MongoClient;
import org.crac.Context;
import org.crac.Core;
import org.crac.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoCheckpointHandler implements Resource {

    @Autowired
    private MongoClient mongoClient;

    public MongoCheckpointHandler() {
        Core.getGlobalContext().register(this);
    }

    @Override
    public void beforeCheckpoint(Context<? extends Resource> context) {
        // Close MongoDB connections before checkpoint
        System.out.println("Mongo beforeCheckpoint callback method called...");
        if (mongoClient != null) {
            System.out.println("Mongo connection not null. Closing the connection");
            mongoClient.close();
        }
    }

    @Override
    public void afterRestore(Context<? extends Resource> context) {
        System.out.println("Mongo afterRestore callback method called...");
    }
}
