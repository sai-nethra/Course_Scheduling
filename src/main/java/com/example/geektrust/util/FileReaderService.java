package com.example.geektrust.util;

import com.example.geektrust.exceptions.InvalidInputException;

import java.io.*;

public class FileReaderService {
    private final File file;
    private final BufferedReader fileReader;

    public FileReaderService(String filePath) throws FileNotFoundException {
        file = new File(filePath);
        fileReader = new BufferedReader(new FileReader(file));
    }

    private boolean isEmpty(String line) {
        return line==null || line.length()==0 || line.trim().isEmpty() || line.trim().startsWith("#");
    }

    public CommandData executeLine() throws IOException {
        try {
            String commandLine = this.fileReader.readLine();
            if(commandLine == null) {
                return null;
            }
            if(isEmpty(commandLine)) {
                return executeLine();
            }
            CommandData commandData = new CommandData(commandLine);
            return commandData;
        }
        catch (InvalidInputException exception) {
            System.out.println(exception.getMessage());
            return executeLine();
        }
    }

}
