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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/file")
public class FileController {

    @GetMapping()
    public String fileView() {
        return "file";
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private final String UPLOAD_DIR = "/uploads";

    @Autowired
    private final FileService fileService;
    @Autowired
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, RedirectAttributes attributes) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("uploadFailed", true);
            return "redirect:/result";
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
        } catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("uploadFailed", false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        fileService.createFile(fileModel);
        LOGGER.info("File was created!");
        attributes.addFlashAttribute("uploadSuccess", true);
        return "redirect:/result";
    }

    @GetMapping("/{id}")
    public String viewFile(@PathVariable Integer id, Model model) {
        File file = fileService.getFile(id);
        model.addAttribute("file", file);
        return "result";
    }

    @GetMapping("/download/{id}")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer id) {
        File file = fileService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""+file.getFilename()+"\"")
                .body(new ByteArrayResource(file.getFileData()));
    }

    @GetMapping("/delete={id}")
    public String deleteFile(@PathVariable Integer id, RedirectAttributes attributes) {

        Integer checkFile = fileService.getFile(id).getFileId();

        if (checkFile == 0 || checkFile == null){
            LOGGER.info("No file selected! Please try again!");
            attributes.addFlashAttribute("fileDeleteFailed", "No file selected! Please try again!");
            return "redirect:/result";
        }

        fileService.deleteFile(id);
        LOGGER.info("File ID={} was deleted!", id);
        attributes.addFlashAttribute("fileDeleteSuccess", "The file was deleted properly!");
        return  "redirect:/result";
    }

}
