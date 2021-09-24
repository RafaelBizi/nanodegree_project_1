package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.config.FileStoreConfig;
import com.udacity.jwdnd.course1.cloudstorage.exception.FileNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.exception.FileStorageException;
import com.udacity.jwdnd.course1.cloudstorage.mapping.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.FILE_NOT_FOUND;

@Service
public class FileService {

    private Logger logger = LoggerFactory.getLogger(FileService.class);
    private final Path fileLocation;

    @Autowired
    private FileMapper fileMapper;

    public FileService(FileStoreConfig fileLocation, FileMapper fileMapper) {
        this.fileLocation = Paths.get(fileLocation.getUploadDir()).toAbsolutePath().normalize();
        this.fileMapper = fileMapper;
        try {
            Files.createDirectories(this.fileLocation);
        } catch (IOException e) {
            throw new FileStorageException("An error occurred while creating a folder to store new files!");
        }
    }

    public int createFile(File file) {
        if (file == null) {
            throw new FileNotFoundException("The file is null!");
        } else {
            return fileMapper.addFile(
                    new File(null, file.getFilename(), file.getContentType(),
                            file.getFileSize(), file.getUserId(), file.getFileData()));
        }
    }

    public List<File> getAllFiles() {
        return fileMapper.getAllFiles();
    }

    public List<File> getAllFilesByUser(Integer userId) {
        return fileMapper.getAllFilesByUser(userId);
    }

    public File getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public File getFileByName(String filename) {
        return fileMapper.getFileByName(filename);
    }

    public File updateFile(File file) {
        return fileMapper.updateFile(file);
    }

    public void deleteFile(Integer fileId) {
        fileMapper.deleteFile(fileId);
    }

    public Resource loadFile(String fileName) {
        try {
            Path filePath = this.fileLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                logger.info("Loading file = " + fileName);
                return resource;
            } else {
                throw new FileNotFoundException(FILE_NOT_FOUND+  fileName);
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException(FILE_NOT_FOUND + fileName, e);
        }
    }
}
