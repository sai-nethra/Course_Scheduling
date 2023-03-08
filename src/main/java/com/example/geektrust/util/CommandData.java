package com.example.geektrust.util;

import com.example.geektrust.enums.CommandName;
import com.example.geektrust.exceptions.InvalidInputException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandData {
    private CommandName commandName;
    private List<String> commandParams;

    public CommandData(String line) throws InvalidInputException {
        String[] commandList = line.split(" ");
        commandName = CommandName.valueOf(getCommandString(commandList[0]));
        commandParams = Arrays.stream(commandList).skip(1).collect(Collectors.toList());
        validateCommand();
    }

    public String getCommandString(String command) throws InvalidInputException {
        switch (command) {
            case "ADD-COURSE-OFFERING":
                return "ADD_COURSE_OFFERING";

            case "REGISTER":
                return "REGISTER";

            case "ALLOT":
                return "ALLOT";

            case "CANCEL":
                return "CANCEL";

            default:
                throw new InvalidInputException("INPUT_DATA_ERROR");
        }
    }

    public CommandName getCommandName() {
        return commandName;
    }

    public List<String> getCommandParams() {
        return commandParams;
    }

    public void validateCommand() throws InvalidInputException {
        if(commandName.getArgsLength() != commandParams.size()) {
            throw new InvalidInputException("INPUT_DATA_ERROR");
        }
    }
}
