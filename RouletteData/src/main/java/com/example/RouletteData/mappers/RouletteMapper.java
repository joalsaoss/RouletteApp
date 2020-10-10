/**
 * 
 */
package com.example.RouletteData.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.annotation.Id;

import com.example.RouletteCommon.dtos.RouletteDTO;
import com.example.RouletteData.models.Bet;
import com.example.RouletteData.models.Roulette;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author joals
 *
 */
@Mapper
public interface RouletteMapper {
	/**
	 * 
	 * @param roulette
	 * @return
	 */
	@Mapping(source="roulette.idRoulette", target="idRoulette")
	@Mapping(source="roulette.state", target="state")
	RouletteDTO rouletteEntityToDTO(Roulette roulette);
	
	/**
	 * 
	 * @param rouletteDTO
	 * @return
	 */
	@Mapping(source="rouletteDTO.idRoulette", target="idRoulette")
	@Mapping(source="rouletteDTO.state", target="state")
	Roulette rouleteDTOToEntity(RouletteDTO rouletteDTO);
	
	/**
	 * 
	 * @param roulette
	 * @return
	 */
	List<RouletteDTO> rouletteEntitiesToDTO(List<Roulette> roulette);
	
	/**
	 * 
	 * @param rouletteDTO
	 * @return
	 */
	List<Roulette> rouletteDTOToEntities(List<RouletteDTO> rouletteDTO);
	
}
