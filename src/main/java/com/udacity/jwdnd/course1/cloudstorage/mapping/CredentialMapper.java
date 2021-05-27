package com.udacity.jwdnd.course1.cloudstorage.mapping;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIAL")
    List<Credential> getAllCredentials();

    @Insert("INSERT INTO CREDENTIAL (url, username, key, password, userid) " +
            "VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredential(Credential credential);

    @Update("UPDATE CREDENTIAL SET url = #{url}, username = #{username}, password = #{password} " +
            "WHERE credentialid = #{credentialId}")
    Credential updateCredential(Credential credential);
}
