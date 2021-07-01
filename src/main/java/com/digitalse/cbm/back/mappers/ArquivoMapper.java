package com.digitalse.cbm.back.mappers;

import java.util.List;

import com.digitalse.cbm.back.DTO.ArquivoDTO;
import com.digitalse.cbm.back.entities.Arquivo;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArquivoMapper {
    ArquivoMapper INSTANCE = Mappers.getMapper(ArquivoMapper.class);

    ArquivoDTO toDTO(Arquivo arquivo);

    Arquivo toModel(ArquivoDTO arquivoDTO);

    List<ArquivoDTO> toDTO(List<Arquivo> arquivos);

    List<Arquivo> toModel(List<ArquivoDTO> arquivos);

}
