package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapping.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapping.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapping.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    FileMapper fileMapper;
    NoteMapper noteMapper;
    CredentialMapper credentialMapper;

    @Autowired
    FileService fileService;
    @Autowired
    NoteService noteService;
    @Autowired
    CredentialService credentialService;
    @Autowired
    UserService userService;
    @Autowired
    EncryptionService encryptionService;

    public HomeController(FileMapper fileMapper, NoteMapper noteMapper, CredentialMapper credentialMapper, FileService fileService, NoteService noteService, CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.fileMapper = fileMapper;
        this.noteMapper = noteMapper;
        this.credentialMapper = credentialMapper;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @GetMapping()
    public String loginView(Model model) {

        User user = userService.getUser(userService.getCurrentUsername());
        Integer userID = user.getUserId();

        if (fileService.getAllFilesByUser(userID).size() >= 1) {
            model.addAttribute("files", fileService.getAllFilesByUser(user.getUserId()));
        }
        if (noteMapper.getAllNotesByUserId(userID).size() >= 1) {
            model.addAttribute("notes", noteService.getAllNotesByUserId(user.getUserId()));
        }
        if(credentialMapper.getAllCredentialsByUser(userID).size() >= 1){
            model.addAttribute("credentials", credentialService.getAllCredentialsByUser(user.getUserId()));
            model.addAttribute("encryptionService", encryptionService);
        }
        return "home";
    }

}
