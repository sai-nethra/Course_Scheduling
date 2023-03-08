package com.example.geektrust;

import com.example.geektrust.systems.LearningManagementSystem;
import com.example.geektrust.util.CommandData;
import com.example.geektrust.util.FileReaderService;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CourseScheduling {
    public static void main(String[] args) {
        try {
            if(args.length >= 1) {
                FileReaderService fileReaderService = new FileReaderService(args[0]);
                LearningManagementSystem learningManagementSystem = new LearningManagementSystem("Hogwarts");
                CommandData command = fileReaderService.executeLine();
                while(command != null) {
                    learningManagementSystem.executeCommand(command);
                    command = fileReaderService.executeLine();
                }
            }
            else {
                throw new FileNotFoundException("File not supplied");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
