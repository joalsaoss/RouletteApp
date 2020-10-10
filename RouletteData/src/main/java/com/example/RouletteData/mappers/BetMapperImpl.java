package com.example.RouletteData.mappers;

import com.example.RouletteCommon.dtos.BetDTO;
import com.example.RouletteData.models.Bet;
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
public class BetMapperImpl implements BetMapper {

    @Override
    public BetDTO betEntityToDTO(Bet bet) {
        if ( bet == null ) {
            return null;
        }

        BetDTO betDTO = new BetDTO();

        betDTO.setIdBet( bet.getIdBet() );
        betDTO.setBetResult( bet.getBetResult() );
        betDTO.setBetValue( bet.getBetValue() );
        betDTO.setIdRoulette( bet.getIdRoulette() );
        betDTO.setUserAmount( bet.getUserAmount() );
        betDTO.setResultAmount( bet.getResultAmount() );
        betDTO.setUsername( bet.getUsername() );

        return betDTO;
    }

    @Override
    public Bet betDTOToEntity(BetDTO betDTO) {
        if ( betDTO == null ) {
            return null;
        }

        Bet bet = new Bet();

        bet.setIdBet( betDTO.getIdBet() );
        bet.setBetResult( betDTO.getBetResult() );
        bet.setBetValue( betDTO.getBetValue() );
        bet.setIdRoulette( betDTO.getIdRoulette() );
        bet.setUserAmount( betDTO.getUserAmount() );
        bet.setResultAmount( betDTO.getResultAmount() );
        bet.setUsername( betDTO.getUsername() );

        return bet;
    }

    @Override
    public List<BetDTO> betEntitiesToDTO(List<Bet> bet) {
        if ( bet == null ) {
            return null;
        }

        List<BetDTO> list = new ArrayList<BetDTO>( bet.size() );
        for ( Bet bet1 : bet ) {
            list.add( betEntityToDTO( bet1 ) );
        }

        return list;
    }

    @Override
    public List<Bet> betDTOToEntities(List<BetDTO> betDTO) {
        if ( betDTO == null ) {
            return null;
        }

        List<Bet> list = new ArrayList<Bet>( betDTO.size() );
        for ( BetDTO betDTO1 : betDTO ) {
            list.add( betDTOToEntity( betDTO1 ) );
        }

        return list;
    }
}
