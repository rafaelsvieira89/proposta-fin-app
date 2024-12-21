package com.vieirarafael.propostafinapp.mappers;

import com.vieirarafael.propostafinapp.dto.PropostaRequestDto;
import com.vieirarafael.propostafinapp.entities.Proposta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PropostaMapper {
    Proposta convertDtoToProposta(PropostaRequestDto propostaRequestDto);
}
