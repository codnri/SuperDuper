package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private  final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public Credential getCredential(Integer credentialId){
        return credentialMapper.getCredential(credentialId);
    }

    public List<Credential> getCredentialByUserId(Integer userId){

        return credentialMapper.getCredentialsByUserId(userId).stream().map((c)->{
            String decryptedPassword = encryptionService.decryptValue(c.getPassword(), c.getKey());
            c.setDecryptedPassword(decryptedPassword);
            return c;
        }).collect(Collectors.toList());

    }

    public int createCredential(Credential credential){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getDecryptedPassword(), encodedKey);
        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);
        return credentialMapper.insertCredential(credential);
    }

    public int deleteCredential(Integer credentialId){
        return  credentialMapper.deleteCredential(credentialId);
    }

    public int updateCredential(Credential credential){
        Credential cred = this.getCredential(credential.getCredentialId());
        cred.setUrl(credential.getUrl());
        cred.setUsername(credential.getUsername());
        String encryptedPassword = encryptionService.encryptValue(credential.getDecryptedPassword(), cred.getKey());
        cred.setPassword(encryptedPassword);
        return credentialMapper.updateCredential(cred);
    }

}
