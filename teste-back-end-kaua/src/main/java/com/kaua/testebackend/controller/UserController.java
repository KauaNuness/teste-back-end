package com.kaua.testebackend.controller;

import com.kaua.testebackend.entity.User;
import com.kaua.testebackend.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(
                userService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(
            @PathVariable @Positive Long id
    ){
        return ResponseEntity.ok(
                userService.findById(id)
        );
    }

    @PostMapping
    public ResponseEntity<User> create(
            @Valid @RequestBody User user
    ){
        User createdUser = userService.create(user);

        URI location = URI.create(
                "/api/users/" + createdUser.getId()
        );

        return ResponseEntity.created(location)
                .body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @PathVariable @Positive Long id,
            @Valid @RequestBody User user
    ){
        return ResponseEntity.ok(
                userService.update(id, user)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable @Positive Long id
    ){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
