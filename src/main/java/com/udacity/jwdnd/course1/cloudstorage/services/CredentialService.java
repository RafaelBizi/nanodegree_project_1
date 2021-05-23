package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapping.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

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

}
