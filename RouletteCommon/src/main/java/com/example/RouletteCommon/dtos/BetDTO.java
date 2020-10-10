/**
 * 
 */
package com.example.RouletteCommon.dtos;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author joals
 *
 */
@Data
public class BetDTO extends ResponseDTO {
	
	@JsonProperty("id")
	private Long idBet;
	
	@JsonProperty("idRoulette")
	private Long idRoulette;
	
	@JsonProperty("idUser")
	private String username;
	
	@JsonProperty("betValue")
	private String betValue;
	
	@JsonProperty("userAmount")
	private Integer userAmount;
	
	@JsonProperty("betResult")
	private String betResult;
	
	@JsonProperty("resultAmount")
	private Double resultAmount;
}
