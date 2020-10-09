/**
 * 
 */
package com.example.RouletteData.models;

import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author joals
 *
 */
@RedisHash("Bets")
@Data
public class Bet extends Response {
	
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
