package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author RafaelBizi
 * @created 10/06/2021 - 09:09
 * @project nanodegree_project_1
 */

@Controller
@RequestMapping("/note")
public class NoteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private final NoteService noteService;
    @Autowired
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping
    public Note getNoteById(Integer id){
        return noteService.getNoteById(id);
    }

    @PostMapping
    public String createNote(@ModelAttribute Note note, RedirectAttributes attributes) {

        User user = userService.getUser(userService.getCurrentUsername());
        note.setUserId(user.getUserId());

        // check if note is empty
        if (note.getUserId() == null || note.getNoteTitle() == null) {
            attributes.addFlashAttribute("fillTheFields", "Please fill the all notes fields");
            return "redirect:/result";
        }

        noteService.createNote(note);
        LOGGER.info("Note was created!");
        attributes.addFlashAttribute("noteCreatedSuccess", "Success");

        return "redirect:/result";
    }

    @PostMapping("/edit")
    public String editNote(@ModelAttribute Note note, RedirectAttributes attributes) {

        User user = userService.getUser(userService.getCurrentUsername());
        note.setUserId(user.getUserId());

        note.setUserId(user.getUserId());

        // check if note is empty
        if (note.getNoteId() == null || note.getNoteTitle() == null) {
            attributes.addFlashAttribute("fillTheFields", "Please fill the all notes fields");
            return "redirect:/result";
        }

        noteService.updateNote(note.getNoteTitle(), note.getNoteDescription(), note.getNoteId());
        LOGGER.info("Note ID={} was updated!" , note.getNoteId());
        attributes.addFlashAttribute("noteUpdatedSuccess", "Success");
        return "redirect:/result";
    }

    @GetMapping("/delete={id}")
    public String deleteNote(@PathVariable Integer id, RedirectAttributes attributes) {

        Integer checkId = noteService.getNoteById(id).getNoteId();

        if (checkId == 0 || checkId == null){
            LOGGER.info("No file selected! Please try again!");
            attributes.addFlashAttribute("noteDeleteFailed", "No file selected! Please try again!");
            return "redirect:/result";
        }

        noteService.deleteNote(id);
        LOGGER.info("Note ID={} was deleted!", id);
        attributes.addFlashAttribute("noteDeleteSuccess", "The note was deleted properly!");

        return "redirect:/result";
    }

}
