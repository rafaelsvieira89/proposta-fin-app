package com.vieirarafael.propostafinapp.controllers;

import com.vieirarafael.propostafinapp.dto.PropostaRequestDto;
import com.vieirarafael.propostafinapp.dto.PropostaResponseDto;
import com.vieirarafael.propostafinapp.exceptions.PropostaNotFoundException;
import com.vieirarafael.propostafinapp.services.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private final PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criarProposta(@RequestBody PropostaRequestDto request) {

        PropostaResponseDto response = propostaService.criar(request);

        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PropostaResponseDto> getProposta(@PathVariable Long id) {
        return ResponseEntity.ok(propostaService.get(id));
    }
    
    @GetMapping
    public ResponseEntity<List<PropostaResponseDto>> getAllProposta() {
        return ResponseEntity.ok(propostaService.getAll());
    }

    

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PropostaNotFoundException.class)
    public ResponseEntity<String> handlePropostaNotFoundException(PropostaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
