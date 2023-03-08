package com.example.geektrust.executors.implementation;

import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.executors.CommandExecutor;
import com.example.geektrust.model.Courses;
import com.example.geektrust.systems.LearningManagementSystem;
import com.example.geektrust.util.CommandData;

public class CancelRegistrationExecutor implements CommandExecutor {
    @Override
    public void execute(LearningManagementSystem learningManagementSystem, CommandData commandData) throws InvalidInputException, CourseFullException {
        String registrationId = commandData.getCommandParams().get(0);
        Courses course = learningManagementSystem.getCourseFromRegistrationID(registrationId);
        String cancellationStatus = course.cancelRegistration(registrationId);
        learningManagementSystem.cancelRegistration(registrationId);
        System.out.println(registrationId + " " + cancellationStatus);
    }
}

