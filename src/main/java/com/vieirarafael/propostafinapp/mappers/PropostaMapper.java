package com.vieirarafael.propostafinapp.mappers;

import com.vieirarafael.propostafinapp.dto.PropostaRequestDto;
import com.vieirarafael.propostafinapp.dto.PropostaResponseDto;
import com.vieirarafael.propostafinapp.entities.Proposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.NumberFormat;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PropostaMapper {
    @Mapping(target = "usuario.nome", source = "nome")
    @Mapping(target = "usuario.sobrenome", source = "sobrenome")
    @Mapping(target = "usuario.cpf", source = "cpf")
    @Mapping(target = "usuario.telefone", source = "telefone")
    @Mapping(target = "usuario.renda", source = "renda")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aprovada", ignore = true)
    @Mapping(target = "integrada", ignore = true)
    @Mapping(target = "observacao", ignore = true)
    Proposta convertDtoToProposta(PropostaRequestDto propostaRequestDto);

    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "sobrenome", source = "usuario.sobrenome")
    @Mapping(target = "telefone", source = "usuario.telefone")
    @Mapping(target = "cpf", source = "usuario.cpf")
    @Mapping(target = "renda", source = "usuario.renda")
    @Mapping(target = "valorSolicitadoFmt", expression = "java(setValorSolicitado(proposta))")
    PropostaResponseDto convertPropostaToDto(Proposta proposta);

    List<PropostaResponseDto> convertPropostaToDto(List<Proposta> proposta);

    default String setValorSolicitado(Proposta proposta) {
        return NumberFormat.getCurrencyInstance().format(proposta.getValorSolicitado());
    }
}
