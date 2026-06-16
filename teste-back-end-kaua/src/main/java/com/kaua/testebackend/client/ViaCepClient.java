package com.kaua.testebackend.client;

import com.kaua.testebackend.dto.ViaCepResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ViaCepClient {

    private final RestClient restClient;

    public ViaCepClient(){
        this.restClient = RestClient.builder()
                .baseUrl("https://viacep.com.br/ws")
                .build();
    }

    public ViaCepResponseDTO findByCep(String cep){

        return restClient.get()
                .uri("/{cep}/json", cep)
                .retrieve()
                .body(ViaCepResponseDTO.class);

    }

}
