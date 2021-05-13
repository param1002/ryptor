package com.param1002.ryptor.controller;

import com.param1002.ryptor.model.Password;
import com.param1002.ryptor.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ryptor/v1/passwords")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping
    public ResponseEntity<List<Password>> show() {
        return ResponseEntity.ok(passwordService.retrieve());
    }

    @PostMapping
    public void save(@RequestBody Password request) {
        passwordService.add(request);
    }

    @PutMapping
    public void update(@RequestBody Password request) {
        passwordService.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        passwordService.delete(id);
    }

}
