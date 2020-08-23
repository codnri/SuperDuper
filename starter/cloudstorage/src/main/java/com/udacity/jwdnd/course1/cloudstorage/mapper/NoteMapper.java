package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM Notes WHERE userid = #{userId}")
    List<Note> getNotesByUserId(Integer userId);

    @Select("SELECT * FROM Notes WHERE noteid = #{noteId}")
    Note getNote(Integer noteId);

    @Insert("INSERT INTO NOTES (notetitle,notedescription,userid)"
            +"VALUES(#{noteTitle},#{noteDescription},#{userId})")
    @Options(useGeneratedKeys = true,keyProperty = "noteId")
    int insertNote(Note note);

    @Delete("DELETE Notes WHERE noteId = #{noteId}")
    int deleteNote(Integer noteId);

    //	@Update("UPDATE village SET name=#{villageName}, district =#{district} WHERE id =#{vid}")
    @Update({"UPDATE Notes SET noteTitle=#{noteTitle},noteDescription=#{noteDescription} WHERE noteId = #{noteId}"})
    int updateNote(Note note);
}
