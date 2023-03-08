package com.example.geektrust.executors.implementation;

import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.executors.CommandExecutor;
import com.example.geektrust.model.Courses;
import com.example.geektrust.systems.LearningManagementSystem;
import com.example.geektrust.util.CommandData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddCourseExecutor implements CommandExecutor {
    @Override
    public void execute(LearningManagementSystem learningManagementSystem, CommandData commandData) throws InvalidInputException, CourseFullException {
        List<String> params = commandData.getCommandParams();
        Courses course = createCourse(params);
        learningManagementSystem.addCourse(course);
        System.out.println(course.getCourseID());
    }

    public Courses createCourse(List<String> params) throws InvalidInputException {
        try {
            String courseName = params.get(0);
            String instructor = params.get(1);
            SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMyyyy");
            Date date = inputFormat.parse(params.get(2));
            int minWorkers = Integer.parseInt(params.get(3));
            int maxWorkers = Integer.parseInt(params.get(4));
            String courseId = "OFFERING-" + courseName + "-" + instructor;
            return new Courses(courseId, courseName, instructor, date, minWorkers, maxWorkers, false, false);
        } catch (Exception e) {
            throw new InvalidInputException("INPUT_DATA_ERROR");
        }
    }
}
