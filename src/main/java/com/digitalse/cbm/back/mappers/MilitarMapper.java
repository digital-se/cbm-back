package com.digitalse.cbm.back.mappers;

import java.util.ArrayList;
import java.util.List;

import com.digitalse.cbm.back.DTO.DTOsMilitar.MilitarDTO;
import com.digitalse.cbm.back.entities.Militar;

public class MilitarMapper {
    static public MilitarDTO toDTO(Militar militar) {
        if (militar == null ) {
            return null;
        }

        MilitarDTO militardto = new MilitarDTO();

        militardto.setMatricula( militar.getMatricula() );

        return militardto;
    }

    static public Militar toModel(MilitarDTO militardto) {
        if (militardto == null ) {
            return null;
        }

        Militar militar = new Militar();

        militar.setMatricula( militardto.getMatricula() );

        return militar;
    }

    static public List<MilitarDTO> toDTOList(List<Militar> militares) {
        if ( militares == null ) {
            return null;
        }

        List<MilitarDTO> list = new ArrayList<MilitarDTO>(militares.size());
        for ( Militar militar : militares ) {
            list.add( toDTO( militar ) );
        }

        return list;
    }

    static public List<Militar> toModelList(List<MilitarDTO> militaresdto) {
        if ( militaresdto == null ) {
            return null;
        }

        List<Militar> list = new ArrayList<Militar>(militaresdto.size());
        for ( MilitarDTO militardto : militaresdto ) {
            list.add( toModel( militardto ) );
        }

        return list;
    }
}
