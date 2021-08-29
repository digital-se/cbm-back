package com.digitalse.cbm.back.mappers;

import java.util.ArrayList;
import java.util.List;

import com.digitalse.cbm.back.DTO.DTOsDocumento.DocumentoDTO;
import com.digitalse.cbm.back.DTO.DTOsMilitar.MilitarDTO;
import com.digitalse.cbm.back.entities.Arquivo;
import com.digitalse.cbm.back.entities.Documento;
import com.digitalse.cbm.back.entities.Militar;

public class DocumentoMapper {
    static public DocumentoDTO toDTO(Documento documento) {
        if ( documento == null ) {
            return null;
        }

        DocumentoDTO documentoDTO = new DocumentoDTO();

        documentoDTO.setNome( documento.getNome() );
        documentoDTO.setNumeracao( documento.getNumeracao() );
        documentoDTO.setPublico( documento.getPublico() );
        documentoDTO.setTipo( documento.getTipo() );
        documentoDTO.setData( documento.getData() );
        documentoDTO.setDescricao( documento.getDescricao() );
        List<MilitarDTO> list1 = MilitarMapper.toDTOList(documento.getMilitares());
        if ( list1 != null ) {
            documentoDTO.setMilitares( new ArrayList<MilitarDTO>( list1 ) );
        }

        return documentoDTO;
    }

    static public Documento toModel(DocumentoDTO documentoDTO) {
        if ( documentoDTO == null ) {
            return null;
        }

        Documento documento = new Documento();

        documento.setNome( documentoDTO.getNome() );
        documento.setNumeracao( documentoDTO.getNumeracao() );
        documento.setPublico( documentoDTO.getPublico() );
        documento.setTipo( documentoDTO.getTipo() );
        documento.setData( documentoDTO.getData() );
        documento.setDescricao( documentoDTO.getDescricao() );
        documento.setArquivos(new ArrayList<Arquivo>());
        List<Militar> list1 = MilitarMapper.toModelList(documentoDTO.getMilitares());
        if ( list1 != null ) {
            documento.setMilitares( new ArrayList<Militar>( list1 ) );
        }

        return documento;
    }

    static public List<DocumentoDTO> toDTO(List<Documento> documentos) {
        if ( documentos == null ) {
            return null;
        }

        List<DocumentoDTO> list = new ArrayList<DocumentoDTO>( documentos.size() );
        for ( Documento documento : documentos ) {
            list.add( toDTO( documento ) );
        }

        return list;
    }

    static public List<Documento> toModel(List<DocumentoDTO> documentos) {
        if ( documentos == null ) {
            return null;
        }

        List<Documento> list = new ArrayList<Documento>( documentos.size() );
        for ( DocumentoDTO documentoDTO : documentos ) {
            list.add( toModel( documentoDTO ) );
        }

        return list;
    }

}
