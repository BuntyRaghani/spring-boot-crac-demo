package com.example.cracdemo.config;

import org.crac.Context;
import org.crac.Core;
import org.crac.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
class FileCheckpointHandler implements CommandLineRunner, Resource {

    private static final String FILE_PATH = "checkpoint_example.txt";
    private FileWriter fileWriter;

    public FileCheckpointHandler() {
        try {
            // Initialize FileWriter
            File file = new File(FILE_PATH);
            fileWriter = new FileWriter(file, true);  // Open file in append mode
            Core.getGlobalContext().register(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        // Write initial content to the file when the app starts
        writeToFile("Application started.\n");
    }

    @Override
    public void beforeCheckpoint(Context<? extends Resource> context) throws Exception {
        // Close the file before checkpoint
        writeToFile("Before checkpoint.\n");
        fileWriter.close();
    }

    @Override
    public void afterRestore(Context<? extends Resource> context) throws Exception {
        // Reinitialize the file writer after restore and append content
        fileWriter = new FileWriter(FILE_PATH, true);
        writeToFile("After restore.\n");
    }

    private void writeToFile(String content) {
        try {
            fileWriter.write(content);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
