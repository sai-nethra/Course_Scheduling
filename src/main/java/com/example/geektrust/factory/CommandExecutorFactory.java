package com.example.geektrust.factory;

import com.example.geektrust.enums.CommandName;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.executors.CommandExecutor;
import com.example.geektrust.executors.implementation.AddCourseExecutor;
import com.example.geektrust.executors.implementation.CancelRegistrationExecutor;
import com.example.geektrust.executors.implementation.CourseAllotmentExecutor;
import com.example.geektrust.executors.implementation.RegisterCourseExecutor;

public class CommandExecutorFactory {

    public static CommandExecutor getCommandExecutor(CommandName command) throws InvalidInputException {
        if(command != null) {
            switch (command) {
                case ADD_COURSE_OFFERING:
                    return new AddCourseExecutor();

                case REGISTER:
                    return new RegisterCourseExecutor();

                case ALLOT:
                    return new CourseAllotmentExecutor();

                case CANCEL:
                    return new CancelRegistrationExecutor();
            }
        }
        throw new InvalidInputException("INPUT_DATA_ERROR");
    }
}
