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

public class AddCourseExecutorTest {
    CommandData command;
    CommandExecutor commandExecutor;
    LearningManagementSystem learningManagementSystem;
    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws InvalidInputException {
        System.setOut(new PrintStream(outputStream));
        command = new CommandData("ADD-COURSE-OFFERING DARKMAGIC HARRY 06032023 1 3");
        commandExecutor = CommandExecutorFactory.getCommandExecutor(command.getCommandName());
        learningManagementSystem = new LearningManagementSystem("Hogwarts");
    }

    @Test
    public void testExecutor() {
        assertDoesNotThrow(()->commandExecutor.execute(learningManagementSystem, command));
    }

    @Test
    public void testOutput() throws InvalidInputException, CourseFullException, CourseFullException {
        commandExecutor.execute(learningManagementSystem, command);
        assertEquals("OFFERING-DARKMAGIC-HARRY",outputStream.toString().trim());
    }

    @Test
    public void invalidCommand() throws InvalidInputException, CourseFullException {
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            command = new CommandData("ADD-COURSE-OFFERINg DARKMAGIC HARRY 06032023 1 3");
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
            command = new CommandData("ADD-COURSE-OFFERING DARKMAGIC");
            commandExecutor = CommandExecutorFactory.getCommandExecutor(command.getCommandName());
            learningManagementSystem = new LearningManagementSystem("Hogwarts");
            commandExecutor.execute(learningManagementSystem, command);
        });
        String expectedMessage = "INPUT_DATA_ERROR";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
