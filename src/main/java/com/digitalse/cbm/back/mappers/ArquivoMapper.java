package com.digitalse.cbm.back.mappers;

import java.util.ArrayList;
import java.util.List;

import com.digitalse.cbm.back.DTO.DTOsArquivo.ArquivoDTO;
import com.digitalse.cbm.back.entities.Arquivo;

public class ArquivoMapper {
    static public ArquivoDTO toDTO(Arquivo arquivo) {
        if ( arquivo == null ) {
            return null;
        }

        ArquivoDTO arquivoDTO = new ArquivoDTO();

        arquivoDTO.setOcr( arquivo.getOcr() );
        return arquivoDTO;
    }

    static public Arquivo toModel(ArquivoDTO arquivoDTO) {
        if ( arquivoDTO == null ) {
            return null;
        }

        Arquivo arquivo = new Arquivo();

        arquivo.setOcr( arquivoDTO.getOcr() );
        return arquivo;
    }

    static public List<ArquivoDTO> toDTO(List<Arquivo> arquivos) {
        if ( arquivos == null ) {
            return null;
        }

        List<ArquivoDTO> list = new ArrayList<ArquivoDTO>( arquivos.size() );
        for ( Arquivo arquivo : arquivos ) {
            list.add( toDTO( arquivo ) );
        }

        return list;
    }

    static public List<Arquivo> toModel(List<ArquivoDTO> arquivos) {
        if ( arquivos == null ) {
            return null;
        }

        List<Arquivo> list = new ArrayList<Arquivo>( arquivos.size() );
        for ( ArquivoDTO arquivoDTO : arquivos ) {
            list.add( toModel( arquivoDTO ) );
        }

        return list;
    }

}
