package com.example.geektrust.model;

import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.validators.EmailValidator;

public class Employee {
    private final String employeeMail;
    private final String employeeName;


    public Employee(String employeeMail, String employeeName) throws InvalidInputException {
        if(EmailValidator.validate(employeeMail)) {
            this.employeeMail = employeeMail;
            this.employeeName = employeeName;
        }
        else {
            throw new InvalidInputException("INPUT_DATA_ERROR");
        }
    }

    public Employee(String employeeMail) throws InvalidInputException {
        if(EmailValidator.validate(employeeMail)) {
            this.employeeMail = employeeMail;
            this.employeeName = employeeMail.substring(0, employeeMail.indexOf('@'));
        }
        else {
            throw new InvalidInputException("INPUT_DATA_ERROR");
        }
    }

    public String getEmployeeMail() {
        return this.employeeMail;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }
}
