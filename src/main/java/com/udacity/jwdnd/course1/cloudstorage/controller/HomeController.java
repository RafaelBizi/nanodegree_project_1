package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapping.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapping.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapping.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
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
    FileService fileService;

    NoteMapper noteMapper;
    NoteService noteService;

    CredentialMapper credentialMapper;
    CredentialService credentialService;

    public HomeController(FileMapper fileMapper, FileService fileService,
                          NoteMapper noteMapper, NoteService noteService,
                          CredentialMapper credentialMapper, CredentialService credentialService) {
        this.fileMapper = fileMapper;
        this.fileService = fileService;
        this.noteMapper = noteMapper;
        this.noteService = noteService;
        this.credentialMapper = credentialMapper;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String loginView(Model model) {
        if (fileService.getAllFiles().size()>=1) {
            model.addAttribute("files", fileService.getAllFiles());
        }

        if (noteMapper.getAllNotes().size()>=1) {
            model.addAttribute("notes", noteService.getAllNotes());
        }

        if(credentialMapper.getAllCredentials().size()>=1){
            model.addAttribute("credentials", credentialService.getAllCredentials());
        }

        return "home";
    }

}
