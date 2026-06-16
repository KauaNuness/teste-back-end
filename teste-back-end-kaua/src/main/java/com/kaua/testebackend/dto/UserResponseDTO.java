package com.kaua.testebackend.dto;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String phone
) {
}
