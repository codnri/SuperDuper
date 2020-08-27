package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM Files WHERE userId = #{userId}")
    List<File> getFilesByUserId(Integer userId);

    @Select("SELECT * FROM Files WHERE fileId = #{fileId}")
    File getFile(Integer fileId);

    @Select("SELECT * FROM Files WHERE fileName = #{fileName} LIMIT 1")
    File getFileByName(String fileName);


    @Insert("INSERT INTO Files (fileName,contentType,fileSize,fileData,userid)"
            +"VALUES(#{fileName},#{contentType},#{fileSize},#{fileData},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Delete("DELETE Files WHERE fileId = #{fileId}")
    int deleteFile(Integer fileId);

}
