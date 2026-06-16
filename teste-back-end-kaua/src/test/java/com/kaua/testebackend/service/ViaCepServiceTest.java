package com.kaua.testebackend.service;

import com.kaua.testebackend.client.ViaCepClient;
import com.kaua.testebackend.dto.ViaCepResponseDTO;
import com.kaua.testebackend.exception.CepInvalidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ViaCepServiceTest {

    @Mock
    private ViaCepClient viaCepClient;

    @InjectMocks
    private ViaCepService viaCepService;

    @Test
    void shouldValidateValidCep() {

        ViaCepResponseDTO response = new ViaCepResponseDTO();
        response.setCep("01001000");
        response.setLogradouro("Praça da Sé");
        response.setErro(false);

        when(viaCepClient.findByCep("01001000"))
                .thenReturn(response);

        ViaCepResponseDTO result =
                viaCepService.validateCep("01001000");

        assertNotNull(result);
        assertEquals("01001000", result.getCep());
        assertFalse(result.getErro());
    }

    @Test
    void shouldThrowExceptionWhenCepIsInvalid() {

        ViaCepResponseDTO response = new ViaCepResponseDTO();
        response.setErro(true);

        when(viaCepClient.findByCep("00000000"))
                .thenReturn(response);

        assertThrows(
                CepInvalidException.class,
                () -> viaCepService.validateCep("00000000")
        );
    }

    @Test
    void shouldThrowExceptionWhenResponseIsNull() {

        when(viaCepClient.findByCep("99999999"))
                .thenReturn(null);

        assertThrows(
                CepInvalidException.class,
                () -> viaCepService.validateCep("99999999")
        );
    }
}