package com.vieirarafael.propostafinapp.agendador;

import com.vieirarafael.propostafinapp.repositories.PropostaRepository;
import com.vieirarafael.propostafinapp.services.NotificacaoRabbitMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class PropostaNaoIntegrada {
    private final PropostaRepository propostaRepository;
    private final NotificacaoRabbitMqService notificacaoRabbitMqService;
    private final String exchange;

    public PropostaNaoIntegrada(PropostaRepository propostaRepository, NotificacaoRabbitMqService notificacaoRabbitMqService,
                                @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.exchange = exchange;
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitMqService = notificacaoRabbitMqService;
    }

    public void buscarPropostasNaoIntegradas() {
        propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {
            try {
                notificacaoRabbitMqService.notificar(proposta, exchange);
                proposta.setIntegrada(true);
                propostaRepository.save(proposta);
            } catch (RuntimeException e) {
                log.error("Failed to notify or update proposta: {}", e.getMessage(), e);
            }
        });
    }
}
