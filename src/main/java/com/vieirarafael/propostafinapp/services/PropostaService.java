package com.vieirarafael.propostafinapp.services;

import com.vieirarafael.propostafinapp.dto.PropostaRequestDto;
import com.vieirarafael.propostafinapp.dto.PropostaResponseDto;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {
    public PropostaResponseDto criar(PropostaRequestDto propostaRequestDto) {
        return new PropostaResponseDto().setId(1L);
    }
}
