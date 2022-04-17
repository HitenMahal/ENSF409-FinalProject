package edu.ucalgary.ensf409;

/**
 * FileAccessException throws a exception when the file can't be accessed
 */
public class FileAccessException extends Exception{
    FileAccessException(String e) {
        super("Error accessing file: " + e);
    }
}
