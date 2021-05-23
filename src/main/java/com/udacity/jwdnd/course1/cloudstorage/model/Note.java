package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {

    private Integer noteId;
    private String noteTittle;
    private String noteDescription;
    private Integer userId;

    public Note(Integer noteId, String noteTittle, String noteDescription, Integer userId) {
        this.noteId = noteId;
        this.noteTittle = noteTittle;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }

    public Note() {
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteTittle() {
        return noteTittle;
    }

    public void setNoteTittle(String noteTittle) {
        this.noteTittle = noteTittle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
