package com.vieirarafael.propostafinapp.services;

import com.vieirarafael.propostafinapp.dto.PropostaRequestDto;
import com.vieirarafael.propostafinapp.dto.PropostaResponseDto;
import com.vieirarafael.propostafinapp.entities.Proposta;
import com.vieirarafael.propostafinapp.entities.Usuario;
import com.vieirarafael.propostafinapp.repositories.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PropostaService {
    private final PropostaRepository propostaRepository;

    public PropostaResponseDto criar(PropostaRequestDto propostaRequestDto) {
        propostaRepository.save(converter(propostaRequestDto));

        return new PropostaResponseDto().setId(1L);
    }

    private Proposta converter(PropostaRequestDto request) {
        var proposta = new Proposta()
                .setValorSolicitado(request.getValorSolicitado())
                .setPrazoPagamento(request.getPrazoPagamento())
                .setUsuario(new Usuario()
                        .setNome(request.getNome())
                        .setCpf(request.getCpf())
                        .setTelefone(request.getTelefone())
                        .setRenda(request.getRenda())
                        .setSobrenome(request.getSobrenome())
                );
        return proposta;
    }

    private PropostaResponseDto converter(Proposta source) {
        return new PropostaResponseDto()
                .setId(source.getId())
                .setNome(source.getUsuario().getNome())
                .setSobrenome(source.getUsuario().getSobrenome())
                .setCpf(source.getUsuario().getCpf())
                .setTelefone(source.getUsuario().getTelefone())
                .setRenda(source.getUsuario().getRenda())
                .setValorSolicitado(source.getValorSolicitado())
                .setPrazoPagamento(source.getPrazoPagamento())
                ;
    }

}
