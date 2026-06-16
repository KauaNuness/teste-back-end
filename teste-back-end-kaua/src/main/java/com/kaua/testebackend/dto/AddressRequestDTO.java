package com.kaua.testebackend.dto;

public record AddressRequestDTO(
        String cep,
        String street,
        String number,
        String state,
        String city,
        String neighborhood
) {
}
