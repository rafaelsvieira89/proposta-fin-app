package com.vieirarafael.propostafinapp.controllers;

import com.vieirarafael.propostafinapp.dto.PropostaRequestDto;
import com.vieirarafael.propostafinapp.dto.PropostaResponseDto;
import com.vieirarafael.propostafinapp.services.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private final PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criarProposta(@RequestBody PropostaRequestDto request) {

        return ResponseEntity.ok()
                .body(propostaService.criar(request))
                ;
    }
}
