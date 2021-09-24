package com.udacity.jwdnd.course1.cloudstorage.mapping;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTE")
    List<Note> getAllNotes();

    @Select("SELECT * FROM NOTE WHERE userid = #{userId}")
    List<Note> getAllNotesByUserId(Integer userId);

    @Select("SELECT * FROM NOTE WHERE noteid = #{noteId}")
    Note getNoteById(Integer noteId);

    @Insert("INSERT INTO NOTE (notetitle, notedescription, userid) " +
            "VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int addNote(Note note);

    @Update("UPDATE NOTE SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    void updateNote(String noteTitle, String noteDescription, Integer noteId);

    @Delete("DELETE FROM NOTE WHERE noteid = #{noteId}")
    void deleteNote(Integer noteId);
}
