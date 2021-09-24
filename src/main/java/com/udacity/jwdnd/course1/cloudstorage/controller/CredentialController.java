package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.utils.GenerateRandomKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author RafaelBizi
 * @created 10/06/2021 - 09:11
 * @project nanodegree_project_1
 */

@Controller
@RequestMapping("/credential")
public class CredentialController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialController.class);

    @Autowired
    private final CredentialService credentialService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final EncryptionService encryptionService;

    public CredentialController(CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @PostMapping()
    public String createCredential(@ModelAttribute Credential credential, RedirectAttributes attributes) {

        String secretKey = generateKey();
        String encryptPassword = encryptionService.encryptValue(credential.getPassword(), secretKey);

        User user = userService.getUser(userService.getCurrentUsername());
        credential.setUserId(user.getUserId());

        credential.setKey(secretKey);
        credential.setPassword(encryptPassword);

        // check if credential is empty
        if (credential.getUrl() == null || credential.getUsername() == null || credential.getPassword() == null) {
            attributes.addFlashAttribute("fillTheFields", "Please fill the all credential fields");
            return "redirect:/result";
        }

        credentialService.createCredential(credential);
        LOGGER.info("Credential was created!");
        attributes.addFlashAttribute("credentialCreatedSuccess", "Please fill the all credential fields");
        return "redirect:/result";
    }

    @PostMapping("/edit")
    public String editCredential(@ModelAttribute Credential credential, RedirectAttributes attributes) {

        String secretKey = generateKey();
        String encryptPassword = encryptionService.encryptValue(credential.getPassword(), secretKey);

        User user = userService.getUser(userService.getCurrentUsername());
        credential.setUserId(user.getUserId());

        credential.setKey(secretKey);
        credential.setPassword(encryptPassword);

        // check if credential is empty
        if (credential.getUrl() == null || credential.getUsername() == null || credential.getPassword() == null) {
            attributes.addFlashAttribute("fillTheFields", "Please fill the all credential fields");
            return "redirect:/result";
        }

        credentialService.updateCredential(
                credential.getUrl(), credential.getKey(), credential.getPassword(),
                credential.getUsername(), credential.getCredentialId());
        LOGGER.info("Credential ID={} was updated!" , credential.getCredentialId());
        attributes.addFlashAttribute("credentialUpdatedSuccess", "Success");
        return "redirect:/result";
    }

    @GetMapping("/delete={id}")
    public String deleteCredential (@PathVariable Integer id, RedirectAttributes attributes) {

        Integer checkCredential = credentialService.getCredentialById(id).getCredentialId();

        if (checkCredential == 0 || checkCredential == null){
            LOGGER.info("No credential selected! Please try again!");
            attributes.addFlashAttribute("credentialDeleteFailed", "No credential selected! Please try again!");
            return "redirect:/result";
        }

        credentialService.deleteCredential(id);
        LOGGER.info("Credential ID={} was deleted!", id);
        attributes.addFlashAttribute("credentialDeleteSuccess", "The credential was deleted properly!");

        return "redirect:/result";
    }

    private String generateKey() {
        final Integer byteValue = 16;

        try{
            SecureRandom random = new SecureRandom();
            byte[] key = new byte[byteValue];
            random.nextBytes(key);
            return Base64.getEncoder().encodeToString(key);
        } catch (Exception e) {
            LOGGER.error("Cause: " + e.getCause() + " Message: " + e.getMessage());
        }
        return null;
    }
}
