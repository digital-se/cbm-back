package com.digitalse.cbm.back.mappers;

import java.util.List;

import com.digitalse.cbm.back.DTO.ArquivoDTO;
import com.digitalse.cbm.back.models.Arquivo;

import org.mapstruct.factory.Mappers;

public interface ArquivoMapper {
    ArquivoMapper INSTANCE = Mappers.getMapper(ArquivoMapper.class);

    ArquivoDTO toDTO (Arquivo arquivo);

    Arquivo toModel (ArquivoDTO arquivoDTO);

    List<ArquivoDTO> toDTO (List<Arquivo> arquivos);

    List<Arquivo> toModel (List<ArquivoDTO> arquivos);

}
