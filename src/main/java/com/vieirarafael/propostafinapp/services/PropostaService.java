package com.vieirarafael.propostafinapp.services;

import com.vieirarafael.propostafinapp.dto.PropostaRequestDto;
import com.vieirarafael.propostafinapp.dto.PropostaResponseDto;
import com.vieirarafael.propostafinapp.entities.Proposta;
import com.vieirarafael.propostafinapp.mappers.PropostaMapper;
import com.vieirarafael.propostafinapp.repositories.PropostaRepository;
import com.vieirarafael.propostafinapp.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PropostaService {
    private final PropostaRepository propostaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PropostaMapper propostaMapper;

    public PropostaResponseDto criar(PropostaRequestDto propostaRequestDto) {
        var proposta = converter(propostaRequestDto);

        return converter(
                propostaRepository.save(
                        proposta.setUsuario(
                                usuarioRepository.save(
                                        proposta.getUsuario()
                                )
                        )
                )
        );
    }

    private Proposta converter(PropostaRequestDto request) {
        return propostaMapper.convertDtoToProposta(request);
    }

    private PropostaResponseDto converter(Proposta source) {
        return propostaMapper.convertPropostaToDto(source);
    }

}
