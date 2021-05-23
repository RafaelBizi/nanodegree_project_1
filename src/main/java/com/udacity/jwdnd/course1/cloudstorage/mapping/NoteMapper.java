package com.udacity.jwdnd.course1.cloudstorage.mapping;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import java.util.*;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTE")
    List<Note> getAllNotes();

    @Insert("INSERT INTO NOTE (notetittle, notedescription, userid) " +
            "VALUES (#{noteTittle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int addNote(Note note);
}
