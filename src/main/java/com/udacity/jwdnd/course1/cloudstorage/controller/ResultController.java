package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;

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

    public ResultController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
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
}
