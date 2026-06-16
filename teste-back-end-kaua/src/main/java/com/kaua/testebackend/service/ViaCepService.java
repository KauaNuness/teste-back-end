package com.kaua.testebackend.service;

import com.kaua.testebackend.client.ViaCepClient;
import com.kaua.testebackend.dto.ViaCepResponseDTO;
import com.kaua.testebackend.exception.CepInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient viaCepClient;

    public ViaCepResponseDTO validateCep(String cep){

        ViaCepResponseDTO response =
                viaCepClient.findByCep(cep);

        if (response == null ||
        Boolean.TRUE.equals(response.getErro())){

            throw new CepInvalidException("CEP Inválido");
        }

        return response;
    }

}
