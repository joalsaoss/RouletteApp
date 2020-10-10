/**
 * 
 */
package com.example.RouletteData.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.annotation.Id;

import com.example.RouletteCommon.dtos.BetDTO;
import com.example.RouletteData.models.Bet;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author joals
 *
 */
@Mapper
public interface BetMapper {
	/**
	 * 
	 * @param bet
	 * @return
	 */
	@Mapping(source = "bet.idBet", target = "idBet")
	@Mapping(source = "bet.username", target = "username")
	@Mapping(source = "bet.idRoulette", target = "idRoulette")
	@Mapping(source = "bet.betValue", target = "betValue")
	@Mapping(source = "bet.userAmount", target = "userAmount")
	@Mapping(source = "bet.betResult", target = "betResult")
	@Mapping(source = "bet.resultAmount", target = "resultAmount")
	BetDTO betEntityToDTO(Bet bet);
	
	/**
	 * 
	 * @param betDTO
	 * @return
	 */
	@Mapping(source = "betDTO.idBet", target = "idBet")
	@Mapping(source = "betDTO.username", target = "username")
	@Mapping(source = "betDTO.idRoulette", target = "idRoulette")
	@Mapping(source = "betDTO.betValue", target = "betValue")
	@Mapping(source = "betDTO.userAmount", target = "userAmount")
	@Mapping(source = "betDTO.betResult", target = "betResult")
	@Mapping(source = "betDTO.resultAmount", target = "resultAmount")
	Bet betDTOToEntity(BetDTO betDTO);
	
	/**
	 * 
	 * @param bet
	 * @return
	 */
	List<BetDTO> betEntitiesToDTO(List<Bet> bet);
	
	/**
	 * 
	 * @param betDTO
	 * @return
	 */
	List<Bet> betDTOToEntities(List<BetDTO> betDTO);
	}
