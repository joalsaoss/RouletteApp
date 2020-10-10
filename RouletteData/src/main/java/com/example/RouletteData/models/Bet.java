/**
 * 
 */
package com.example.RouletteData.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.example.RouletteCommon.enums.StateBetEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author joals
 *
 */
@RedisHash("Bets")
@Data
public class Bet {
	@Id
	private Long idBet;
	
	@JsonProperty("idUser")
	private String username;
	
	@JsonProperty("idRoulette")
	private Long idRoulette;
	
	@JsonProperty("betValue")
	private String betValue;
	
	@JsonProperty("userAmount")
	private Integer userAmount;
	
	@JsonProperty("betResult")
	private String betResult;
	
	@JsonProperty("resultAmount")
	private Double resultAmount;
	
	@JsonProperty("stateBet")
	private StateBetEnum stateBet;

}
