package com.example.geektrust.executors;

import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.factory.CommandExecutorFactory;
import com.example.geektrust.systems.LearningManagementSystem;
import com.example.geektrust.util.CommandData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterCourseExecutorTest {
    CommandData command;
    CommandExecutor commandExecutor;
    LearningManagementSystem learningManagementSystem;
    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    String output = "";

    @BeforeEach
    public void setUp() throws InvalidInputException, CourseFullException {
        System.setOut(new PrintStream(outputStream));
        learningManagementSystem = new LearningManagementSystem("Hogwarts");

        command = new CommandData("ADD-COURSE-OFFERING DARKMAGIC HARRY 06032023 1 3");
        commandExecutor = CommandExecutorFactory.getCommandExecutor(command.getCommandName());
        commandExecutor.execute(learningManagementSystem, command);

        command = new CommandData("REGISTER HERMOINE@GMAIL.COM OFFERING-DARKMAGIC-HARRY");
        commandExecutor = CommandExecutorFactory.getCommandExecutor(command.getCommandName());

        output = "OFFERING-DARKMAGIC-HARRY\n";
    }

    @Test
    public void testExecutor() {
        assertDoesNotThrow(()->commandExecutor.execute(learningManagementSystem, command));
    }

    @Test
    public void testOutput() throws InvalidInputException, CourseFullException, CourseFullException {
        commandExecutor.execute(learningManagementSystem, command);
        assertEquals(output + "REG-COURSE-HERMOINE-DARKMAGIC ACCEPTED",outputStream.toString().trim());
    }

    @Test
    public void testOutputRegistrationRejected() throws InvalidInputException, CourseFullException, CourseFullException {
        commandExecutor.execute(learningManagementSystem, command);
        command = new CommandData("ALLOT OFFERING-DARKMAGIC-HARRY");
        commandExecutor = CommandExecutorFactory.getCommandExecutor(command.getCommandName());
        commandExecutor.execute(learningManagementSystem, command);
        String output2 = "REG-COURSE-HERMOINE-DARKMAGIC ACCEPTED\n" +
                "REG-COURSE-HERMOINE-DARKMAGIC HERMOINE@GMAIL.COM OFFERING-DARKMAGIC-HARRY DARKMAGIC HARRY 06032023 CONFIRMED\n";

        command = new CommandData("REGISTER RON@GMAIL.COM OFFERING-DARKMAGIC-HARRY");
        commandExecutor = CommandExecutorFactory.getCommandExecutor(command.getCommandName());
        commandExecutor.execute(learningManagementSystem, command);
        assertEquals(output + output2 + "REG-COURSE-RON-DARKMAGIC REJECTED",outputStream.toString().trim());
    }

    @Test
    public void courseFull() throws InvalidInputException, CourseFullException {
        Exception exception = assertThrows(CourseFullException.class, () -> {
            commandExecutor.execute(learningManagementSystem, command);
            command = new CommandData("REGISTER RON@GMAIL.COM OFFERING-DARKMAGIC-HARRY");
            commandExecutor = CommandExecutorFactory.getCommandExecutor(command.getCommandName());
            commandExecutor.execute(learningManagementSystem, command);
            command = new CommandData("REGISTER VOLDEMORT@GMAIL.COM OFFERING-DARKMAGIC-HARRY");
            commandExecutor = CommandExecutorFactory.getCommandExecutor(command.getCommandName());
            commandExecutor.execute(learningManagementSystem, command);
            command = new CommandData("REGISTER MALFOY@GMAIL.COM OFFERING-DARKMAGIC-HARRY");
            commandExecutor = CommandExecutorFactory.getCommandExecutor(command.getCommandName());
            commandExecutor.execute(learningManagementSystem, command);
        });
        String expectedMessage = "COURSE_FULL_ERROR";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void invalidCommand() throws InvalidInputException, CourseFullException {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            command = new CommandData("REGISTER_COURSE RON@GMAIL.COM OFFERING-DARKMAGIC-HARRY");
            commandExecutor = CommandExecutorFactory.getCommandExecutor(command.getCommandName());
            learningManagementSystem = new LearningManagementSystem("Hogwarts");
            commandExecutor.execute(learningManagementSystem, command);
        });
        String expectedMessage = "INPUT_DATA_ERROR";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void invalidArgs() throws InvalidInputException, CourseFullException {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            command = new CommandData("REGISTER RON@GMAIL.COM OFFERING-DARKMAGIC-HARRY 123");
            commandExecutor = CommandExecutorFactory.getCommandExecutor(command.getCommandName());
            learningManagementSystem = new LearningManagementSystem("Hogwarts");
            commandExecutor.execute(learningManagementSystem, command);
        });
        String expectedMessage = "INPUT_DATA_ERROR";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
