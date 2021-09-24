package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapping.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public int createCredential(Credential credential) {
        return credentialMapper.addCredential(
                new Credential(null, credential.getUrl(), credential.getKey(),
                        credential.getPassword(), credential.getUsername(), credential.getUserId()));
    }

    public Credential getCredentialById(Integer idCredential){
        return credentialMapper.getCredentialById(idCredential);
    }

    public List<Credential> getAllCredentials() {
        return credentialMapper.getAllCredentials();
    }

    public List<Credential> getAllCredentialsByUser(Integer userId) {
        return credentialMapper.getAllCredentialsByUser(userId);
    }

    public void updateCredential(String url, String key, String password, String username, Integer id){
        credentialMapper.updateCredential(url, username, password, id);
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.deleteCredential(credentialId);
    }
}
