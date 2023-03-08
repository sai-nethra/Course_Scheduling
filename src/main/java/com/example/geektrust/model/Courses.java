package com.example.geektrust.model;

import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.exceptions.InvalidInputException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Courses implements Comparable<Courses> {
    private final String courseID;
    private final String courseName;
    private final String instructor;
    private final Date date;
    private final int minWorkers;
    private final int maxWorkers;
    private Boolean allotted;
    private Boolean cancel;
    private final TreeMap<String, Employee> courseEmployees;

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public Date getDate() {
        return date;
    }

    public int getMinWorkers() {
        return minWorkers;
    }

    public int getMaxWorkers() {
        return maxWorkers;
    }

    public Boolean getAllotted() {
        return allotted;
    }

    public void setAllotted(Boolean allotted) {
        this.allotted = allotted;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }

    public Map<String, Employee> getCourseEmployees() {
        return courseEmployees;
    }

    public Courses(String courseID, String courseName, String instructor, Date date, int minWorkers, int maxWorkers, Boolean allotted, Boolean cancel) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.instructor = instructor;
        this.date = date;
        this.minWorkers = minWorkers;
        this.maxWorkers = maxWorkers;
        this.allotted = allotted;
        this.cancel = cancel;
        this.courseEmployees = new TreeMap<>();
    }

    public String getCourseID() {
        return courseID;
    }

    public String registerEmployee(Employee employee) throws CourseFullException {
        if(courseEmployees.size() >= maxWorkers) {
            throw new CourseFullException("COURSE_FULL_ERROR");
        }
        String regId = "REG-COURSE-" + employee.getEmployeeName() + "-" + this.courseName;
        if(!this.allotted) {
            this.courseEmployees.put(regId, employee);
        }
        return regId;
    }

    public String cancelRegistration(String registrationId) throws InvalidInputException {
        Employee employee = courseEmployees.get(registrationId);
        if(employee == null) {
            throw new InvalidInputException("INPUT_DATA_ERROR");
        }
        if(!this.allotted) {
            courseEmployees.remove(registrationId);
            return "CANCEL_ACCEPTED";
        }
        else {
            return "CANCEL_REJECTED";
        }
    }

    public void printAllottedEmployees() {
        int currentEmployees = this.getCourseEmployees().size();
        String status = (this.getCancel() || currentEmployees < this.getMinWorkers()) ? "COURSE_CANCELED" : "CONFIRMED";
        for(Map.Entry<String, Employee> entry : this.getCourseEmployees().entrySet()) {
            String pattern = "ddMMyyyy";
            DateFormat df = new SimpleDateFormat(pattern);
            System.out.println(entry.getKey() + " " + entry.getValue().getEmployeeMail() + " " + this.getCourseID() + " "
                    + this.getCourseName() + " " + this.getInstructor() + " " + df.format(this.getDate()) + " " + status);
        }
    }

    @Override
    public int compareTo(Courses course) {
        return this.courseName.compareTo(course.courseName);
    }
}
