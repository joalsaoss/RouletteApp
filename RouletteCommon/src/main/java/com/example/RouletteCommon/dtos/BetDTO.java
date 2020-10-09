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
