package com.udacity.jwdnd.course1.cloudstorage.mapping;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILE")
    List<File> getAllFiles();

    @Select("SELECT * FROM FILE WHERE userid = #{owner}")
    List<File> getAllFilesByUser(int userId);

    @Select("SELECT * FROM FILE WHERE fileid = #{fileId}")
    File getFile(Integer fileId);

    @Select("SELECT * FROM FILE WHERE filename = #{fileName}")
    File getFileByName(String fileName);

    @Insert("INSERT INTO FILE (filename, contenttype, filesize, userid, filedata) " +
            "VALUES (#{filename}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyColumn = "fileId", keyProperty = "fileId")
    int addFile(File file);

    @Update("UPDATE FILE SET filename = #{filename} WHERE fileid = #{fileId}")
    File updateFile(File file);

    @Delete("DELETE FROM FILE WHERE fileId = #{fileId}")
    void deleteFile(Integer fileId);
}
