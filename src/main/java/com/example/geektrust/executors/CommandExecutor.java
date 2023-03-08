package com.example.geektrust.executors;

import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.systems.LearningManagementSystem;
import com.example.geektrust.util.CommandData;

public interface CommandExecutor {
    public void execute(LearningManagementSystem learningManagementSystem, CommandData commandData) throws InvalidInputException, CourseFullException;
}
