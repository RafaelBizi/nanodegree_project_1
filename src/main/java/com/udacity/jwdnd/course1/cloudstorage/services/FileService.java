package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapping.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int createFile(File file){

        return fileMapper.addFile(
                new File(null, file.getFilename(), file.getContentType(),
                         file.getFileSize(), file.getUserId(), file.getFileData()));
    }

    public List<File> getAllFiles() {
        return fileMapper.getAllFiles();
    }

    public File updateFile(File file){
        return fileMapper.updateFile(file);
    }
}
