package com.example.geektrust.executors.implementation;

import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.executors.CommandExecutor;
import com.example.geektrust.model.Courses;
import com.example.geektrust.model.Employee;
import com.example.geektrust.systems.LearningManagementSystem;
import com.example.geektrust.util.CommandData;

import java.util.List;

public class RegisterCourseExecutor implements CommandExecutor {

    @Override
    public void execute(LearningManagementSystem learningManagementSystem, CommandData commandData) throws InvalidInputException, CourseFullException {
        List<String> params = commandData.getCommandParams();
        String employeeMail = params.get(0);
        String courseId = params.get(1);
        Employee employee = new Employee(employeeMail);
        Courses course = learningManagementSystem.getCourseFromCourseID(courseId);
        String registrationId = course.registerEmployee(employee);
        if(course.getAllotted()) {
            System.out.println(registrationId + " " + "REJECTED");
        }
        else {
            learningManagementSystem.addRegistration(registrationId, course);
            System.out.println(registrationId + " " + "ACCEPTED");
        }

    }
}
