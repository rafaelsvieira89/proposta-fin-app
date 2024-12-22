package com.vieirarafael.propostafinapp.listener;

import com.vieirarafael.propostafinapp.entities.Proposta;
import com.vieirarafael.propostafinapp.repositories.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class PropostaConcluidaListener {
    private final PropostaRepository propostaRepository;


    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void processar(Proposta mensagem) {
        propostaRepository.save(mensagem);
    }
}
