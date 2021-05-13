package com.param1002.ryptor.service;

import com.param1002.ryptor.encryptor.Encryption;
import com.param1002.ryptor.model.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PasswordService {

    private final Map<String, Password> passwords = new HashMap<>();

    @Autowired
    Encryption encryption;

    public List<Password> retrieve() {

        return new ArrayList<>(passwords.values());
    }

    public void add(final Password password) throws InvalidKeySpecException, NoSuchAlgorithmException {

        final byte[] hashed = encryption.hash(password.getValue().toCharArray(), encryption.getNextSalt());

        System.out.println(new String(hashed, StandardCharsets.UTF_8));
        passwords.put(password.getId(), password);
    }

    public void update(final Password password) {

        passwords.put(password.getId(), password);
    }

    public void delete(final String id) {

        passwords.remove(id);
    }

}
