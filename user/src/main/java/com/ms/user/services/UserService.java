package com.ms.user.services;

import com.ms.user.dtos.UserRecordSaveDTO;
import com.ms.user.dtos.UserRecordUpdateDTO;
import com.ms.user.models.User;
import com.ms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ResponseEntity<User> save(UserRecordSaveDTO dto) {
            var user = new User();
            BeanUtils.copyProperties(dto, user);
            user = repository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    public ResponseEntity<User> findById(UUID id) {
        var opt = repository.findById(id);

        return opt.map(user -> ResponseEntity.status(HttpStatus.ACCEPTED).body(user)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<List<User>> listAll() {
        var users = repository.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(users);
    }

    @Transactional
    public ResponseEntity<User> update(UserRecordUpdateDTO dto) {
        var updatedUser = new User();
        BeanUtils.copyProperties(dto, updatedUser);

        var opt = repository.findById(updatedUser.getId());

        return opt.map(user -> ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(updatedUser))).orElseGet(() ->
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    public ResponseEntity<String> deleteById(UUID id) {
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Id: " + id + ", deleted.");
    }
}
