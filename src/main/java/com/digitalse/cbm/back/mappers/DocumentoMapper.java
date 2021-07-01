package com.digitalse.cbm.back.mappers;

import com.digitalse.cbm.back.DTO.DocumentoDTO;
import com.digitalse.cbm.back.models.Documento;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface DocumentoMapper {
    DocumentoMapper INSTANCE = Mappers.getMapper(DocumentoMapper.class);

    DocumentoDTO toDTO (Documento documento);

    Documento toModel (DocumentoDTO documentoDTO);
}
