package com.example.geektrust.systems;

import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.executors.CommandExecutor;
import com.example.geektrust.factory.CommandExecutorFactory;
import com.example.geektrust.model.Courses;
import com.example.geektrust.util.CommandData;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LearningManagementSystem {
    private String LMSName;
    private final Map<String, Courses> courses;
    private final TreeMap<String, Courses> courseRegestrationsId;

    public String getLMSName() {
        return LMSName;
    }

    public void setLMSName(String LMSName) {
        this.LMSName = LMSName;
    }

    public Map<String, Courses> getCourses() {
        return courses;
    }

    public Map<String, Courses> getCourseRegestrationsId() {
        return courseRegestrationsId;
    }

    public LearningManagementSystem(String LMSName) {
        this.LMSName = LMSName;
        this.courses = new HashMap<>();
        this.courseRegestrationsId = new TreeMap<>();
    }

    public void addCourse(Courses course) {
        this.courses.put(course.getCourseID(), course);
    }

    public void addRegistration(String registrationId, Courses course) {
        this.courseRegestrationsId.put(registrationId, course);
    }

    public void cancelRegistration(String registrationId) throws InvalidInputException {
        if(this.getCourseFromRegistrationID(registrationId) == null) {
            throw new InvalidInputException("INPUT_DATA_ERROR");
        }
        this.courseRegestrationsId.remove(registrationId);
    }

    public Courses getCourseFromCourseID(String courseId) throws InvalidInputException {
        if(courses.get(courseId) == null) {
            throw new InvalidInputException("INPUT_DATA_ERROR");
        }
        return courses.get(courseId);
    }

    public Courses getCourseFromRegistrationID(String registrationId) throws InvalidInputException {
        if(courseRegestrationsId.get(registrationId) == null) {
            throw new InvalidInputException("INPUT_DATA_ERROR");
        }
        return courseRegestrationsId.get(registrationId);
    }

    public void executeCommand(CommandData commandData) {
        try {
            CommandExecutor executor = CommandExecutorFactory.getCommandExecutor(commandData.getCommandName());
            executor.execute(this, commandData);
        }
        catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        } catch (CourseFullException e) {
            System.out.println(e.getMessage());
        }

    }
}
