package com.vieirarafael.propostafinapp.services;

import com.vieirarafael.propostafinapp.dto.PropostaRequestDto;
import com.vieirarafael.propostafinapp.dto.PropostaResponseDto;
import com.vieirarafael.propostafinapp.entities.Proposta;
import com.vieirarafael.propostafinapp.exceptions.PropostaNotFoundException;
import com.vieirarafael.propostafinapp.mappers.PropostaMapper;
import com.vieirarafael.propostafinapp.repositories.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PropostaService {
    private final PropostaRepository propostaRepository;
    private final PropostaMapper propostaMapper;

    public PropostaResponseDto criar(PropostaRequestDto propostaRequestDto) {
        var proposta = converter(propostaRequestDto);

        return converter(
                propostaRepository.save(
                        proposta
                )
        );
    }

    private Proposta converter(PropostaRequestDto request) {
        return propostaMapper.convertDtoToProposta(request);
    }

    private PropostaResponseDto converter(Proposta source) {
        return propostaMapper.convertPropostaToDto(source);
    }

    public PropostaResponseDto get(Long id) {
        return converter(propostaRepository.findById(id)
                .orElseThrow(() -> new PropostaNotFoundException("Proposta com ID " + id + " não encontrada.")));
    }

    public List<PropostaResponseDto> getAll() {
        return propostaMapper.convertPropostaToDto(propostaRepository.findAll());
    }
}
