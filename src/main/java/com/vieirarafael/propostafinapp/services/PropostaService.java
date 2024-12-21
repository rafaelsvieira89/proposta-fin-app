package com.vieirarafael.propostafinapp.services;

import com.vieirarafael.propostafinapp.dto.PropostaRequestDto;
import com.vieirarafael.propostafinapp.dto.PropostaResponseDto;
import com.vieirarafael.propostafinapp.entities.Proposta;
import com.vieirarafael.propostafinapp.exceptions.PropostaNotFoundException;
import com.vieirarafael.propostafinapp.mappers.PropostaMapper;
import com.vieirarafael.propostafinapp.repositories.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {
    private final PropostaRepository propostaRepository;
    private final PropostaMapper propostaMapper;
    private final NotificacaoService notificacaoService;
    private final String exchange;

    public PropostaService(PropostaRepository propostaRepository, PropostaMapper propostaMapper, NotificacaoService notificacaoService, @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.propostaMapper = propostaMapper;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;
    }

    public PropostaResponseDto criar(PropostaRequestDto propostaRequestDto) {

        var proposta = propostaRepository.save(converter(propostaRequestDto));

        notificarRabbitMq(proposta);

        return converter(proposta);
    }

    private void notificarRabbitMq(Proposta proposta) {
        try {
            notificacaoService.notificar(proposta, exchange);
        } catch (RuntimeException e) {
            //TODO criar job para pegar as propostas nao enviadas e enviar para o RabbitMQ novamente em outro momento
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }

    private Proposta converter(PropostaRequestDto request) {
        return propostaMapper.convertDtoToProposta(request);
    }

    private PropostaResponseDto converter(Proposta source) {
        return propostaMapper.convertPropostaToDto(source);
    }

    public PropostaResponseDto get(Long id) {
        return converter(propostaRepository.findById(id).orElseThrow(() -> new PropostaNotFoundException("Proposta com ID " + id + " não encontrada.")));
    }

    public List<PropostaResponseDto> getAll() {
        return propostaMapper.convertPropostaToDto(propostaRepository.findAll());
    }
}
