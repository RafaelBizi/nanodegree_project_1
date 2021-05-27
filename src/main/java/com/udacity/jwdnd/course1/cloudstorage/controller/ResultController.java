package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.utils.GenerateRandomKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
public class ResultController {
    @GetMapping("/result")
    public String resultView() {
        return "result";
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private final String UPLOAD_DIR = "/uploads";

    private final FileService fileService;
    private final UserService userService;
    private final NoteService noteService;
    private final CredentialService credentialService;

    public ResultController(FileService fileService, UserService userService, NoteService noteService, CredentialService credentialService) {
        this.fileService = fileService;
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, RedirectAttributes attributes) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/home";
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        File fileModel = new File();
        fileModel.setFilename(fileName);
        fileModel.setContentType(file.getContentType());
        fileModel.setFileSize(file.getSize());

        User user = userService.getUser(userService.getCurrentUsername());
        fileModel.setUserId(user.getUserId());

        // save the file on the local system
        try {
            InputStream is = file.getInputStream();
            fileModel.setFileData(is.readAllBytes());
//            String dir = System.getProperty("user.dir");
//            String completeDir = dir + UPLOAD_DIR + "/" + fileName;
//            Path folderPath = Paths.get(completeDir);
//            Files.copy(file.getInputStream(), folderPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("uploadSuccess", false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        fileService.createFile(fileModel);

        // return success response
        attributes.addFlashAttribute("uploadSuccess", true);

        return "redirect:/result";
    }

    @PostMapping("/note")
    public String createNote(@ModelAttribute Note note, RedirectAttributes attributes) {

        User user = userService.getUser(userService.getCurrentUsername());
        note.setUserId(user.getUserId());

        // check if note is empty
        if (note.getUserId() == null || note.getNoteTittle() == null) {
            attributes.addFlashAttribute("message", "Please fill the all notes fields");
            return "redirect:/home";
        }

        noteService.createNote(note);

        return "redirect:/home";
    }

    @PutMapping("/note/edit")
    public String createNote(@ModelAttribute Note note) {

        noteService.updateNote(note);

        return "redirect:/home";
    }

    @PostMapping("/credential")
    public String createCredential(@ModelAttribute Credential credential, RedirectAttributes attributes) {

        User user = userService.getUser(userService.getCurrentUsername());
        credential.setUserId(user.getUserId());

        credential.setKey(GenerateRandomKey.generateString());

        // check if credential is empty
        if (credential.getUrl() == null || credential.getUsername() == null || credential.getPassword() == null) {
            attributes.addFlashAttribute("message", "Please fill the all notes fields");
            return "redirect:/home";
        }

        credentialService.createCredential(credential);

        return "redirect:/home";
    }

}
