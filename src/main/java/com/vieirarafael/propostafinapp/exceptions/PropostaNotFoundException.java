package com.vieirarafael.propostafinapp.exceptions;

public class PropostaNotFoundException extends RuntimeException {
    public PropostaNotFoundException(String message) {
        super(message);
    }
}