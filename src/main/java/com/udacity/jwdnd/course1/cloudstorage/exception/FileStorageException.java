package com.udacity.jwdnd.course1.cloudstorage.exception;

/**
 * @author RafaelBizi
 * @project cloud-storage-project-1
 */

public class FileStorageException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
