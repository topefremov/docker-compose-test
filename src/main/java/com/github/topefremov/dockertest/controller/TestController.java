package com.github.topefremov.dockertest.controller;

import com.github.topefremov.dockertest.domain.entity.User;
import com.github.topefremov.dockertest.domain.repository.TableRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {

    private final TableRepository tableRepository;

    public TestController(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @GetMapping(value = "/api/test/{id}")
    public ResponseEntity<?> getTest(@PathVariable("id") Long id) {
        Optional<User> table = tableRepository.findById(id);
        if (table.isPresent()) {
            return ResponseEntity.ok(table.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
