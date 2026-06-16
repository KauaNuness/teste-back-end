package com.kaua.testebackend.dto;

public record UserRequestDTO(
        String name,
        String email,
        String phone
) {
}
