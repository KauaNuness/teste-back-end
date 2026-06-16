package com.kaua.testebackend.controller;

import com.kaua.testebackend.entity.User;
import com.kaua.testebackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(
        name = "Usuários",
        description = "Operações de gerenciamento de usuários e endereços"
)
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Listar todos os usuários")
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(
                userService.findAll()
        );
    }

    @Operation(summary = "Buscar usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(
            @PathVariable @Positive Long id
    ){
        return ResponseEntity.ok(
                userService.findById(id)
        );
    }

    @Operation(summary = "Criar usuário com endereço")
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

    @Operation(summary = "Atualizar usuário e endereços")
    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @PathVariable @Positive Long id,
            @Valid @RequestBody User user
    ){
        return ResponseEntity.ok(
                userService.update(id, user)
        );
    }

    @Operation(summary = "Remover usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable @Positive Long id
    ){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
