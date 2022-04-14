package edu.ucalgary.ensf409;

public class FileAccessException extends Exception{
    FileAccessException(String e) {
        super("Error accessing file: " + e);
    }
}
