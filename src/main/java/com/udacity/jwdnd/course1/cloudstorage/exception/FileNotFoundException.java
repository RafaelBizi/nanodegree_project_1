package com.udacity.jwdnd.course1.cloudstorage.exception;

/**
 * @author RafaelBizi
 * @project cloud-storage-project-1
 */

public class FileNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
