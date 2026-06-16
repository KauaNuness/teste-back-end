package com.kaua.testebackend.dto;

public record AddressResponseDTO(
        Long id,
        String cep,
        String street,
        String number,
        String state,
        String city,
        String neighborhood
) {
}
