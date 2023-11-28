package com.ms.user.controllers;

import com.ms.user.dtos.UserRecordSaveDTO;
import com.ms.user.dtos.UserRecordUpdateDTO;
import com.ms.user.models.User;
import com.ms.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody @Valid UserRecordSaveDTO dto){
        return service.save(dto);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") UUID id){
        return service.findById(id);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> list(){
        return service.listAll();
    }

    @PutMapping("/users")
    public ResponseEntity<User> update(@RequestBody @Valid UserRecordUpdateDTO dto){
        return service.update(dto);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") UUID id){
        return service.deleteById(id);
    }
}
