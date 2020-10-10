package com.example.RouletteData.mappers;

import com.example.RouletteCommon.dtos.RouletteDTO;
import com.example.RouletteData.models.Roulette;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-10T16:38:21-0500",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class RouletteMapperImpl implements RouletteMapper {

    @Override
    public RouletteDTO rouletteEntityToDTO(Roulette roulette) {
        if ( roulette == null ) {
            return null;
        }

        RouletteDTO rouletteDTO = new RouletteDTO();

        rouletteDTO.setIdRoulette( roulette.getIdRoulette() );
        rouletteDTO.setState( roulette.getState() );

        return rouletteDTO;
    }

    @Override
    public Roulette rouleteDTOToEntity(RouletteDTO rouletteDTO) {
        if ( rouletteDTO == null ) {
            return null;
        }

        Roulette roulette = new Roulette();

        roulette.setIdRoulette( rouletteDTO.getIdRoulette() );
        roulette.setState( rouletteDTO.getState() );

        return roulette;
    }

    @Override
    public List<RouletteDTO> rouletteEntitiesToDTO(List<Roulette> roulette) {
        if ( roulette == null ) {
            return null;
        }

        List<RouletteDTO> list = new ArrayList<RouletteDTO>( roulette.size() );
        for ( Roulette roulette1 : roulette ) {
            list.add( rouletteEntityToDTO( roulette1 ) );
        }

        return list;
    }

    @Override
    public List<Roulette> rouletteDTOToEntities(List<RouletteDTO> rouletteDTO) {
        if ( rouletteDTO == null ) {
            return null;
        }

        List<Roulette> list = new ArrayList<Roulette>( rouletteDTO.size() );
        for ( RouletteDTO rouletteDTO1 : rouletteDTO ) {
            list.add( rouleteDTOToEntity( rouletteDTO1 ) );
        }

        return list;
    }
}
