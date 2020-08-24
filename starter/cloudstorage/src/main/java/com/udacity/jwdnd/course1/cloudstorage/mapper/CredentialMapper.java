package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM Credentials WHERE userid = #{userId}")
    List<Credential> getCredentialsByUserId(Integer userId);

    @Select("SELECT * FROM Credentials WHERE credentialId = #{credentialId}")
    Credential getNote(Integer credentialId);

    @Insert("INSERT INTO Credentials (url,username,key,password,userid)"
            +"VALUES(#{url},#{username},#{key},#{password},#{userId}")
    @Options(useGeneratedKeys = true,keyProperty = "credentialId")
    int insertCredential(Credential credential);

    @Delete("DELETE Credential WHERE credentialId = #{credentialId}")
    int deleteCredential(Integer credentialId);

    @Update({"UPDATE Credentials SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialId = #{credentialId}"})
    int updateCredential(Credential credential);
}
