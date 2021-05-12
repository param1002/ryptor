package com.param1002.ryptor.service;

import com.param1002.ryptor.model.Password;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PasswordService {
    private final Map<String, Password> passwords = new HashMap<>();

    public List<Password> retrieve() {
        return new ArrayList<>(passwords.values());
    }

    public void add(Password password) {
        passwords.put(password.getId(), password);
    }

    public void update(Password password) {
        passwords.put(password.getId(), password);
    }

    public void delete(String id) {
        passwords.remove(id);
    }

}
