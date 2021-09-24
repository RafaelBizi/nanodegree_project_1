package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapping.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int createNote(Note note) {
        return noteMapper.addNote(
                new Note(null, note.getNoteTitle(), note.getNoteDescription(), note.getUserId())
        );
    }

    public List<Note> getAllNotes() {
        return noteMapper.getAllNotes();
    }

    public List<Note> getAllNotesByUserId(Integer id) {
        return noteMapper.getAllNotesByUserId(id);
    }

    public void updateNote(String title, String description, Integer id){
        noteMapper.updateNote(title, description, id);
    }

    public Note getNoteById(Integer id){
        return noteMapper.getNoteById(id);
    }

    public void deleteNote(Integer noteId) {
        noteMapper.deleteNote(noteId);
    }
}
