package com.udacity.jwdnd.course1.cloudstorage.mapping;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILE")
    List<File> getAllFiles();

    @Insert("INSERT INTO FILE (filename, contenttype, filesize, userid, filedata) " +
            "VALUES (#{filename}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(File file);

    @Update("UPDATE FILE SET filename = #{filename} WHERE fileid = #{fileId}")
    File updateFile(File file);
}
