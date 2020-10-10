/**
 * 
 */
package com.example.RouletteCommon.dtos;

import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author joals
 *
 */
@Data
public class RouletteDTO extends ResponseDTO {
	
	@JsonProperty("id")
	private Long idRoulette;
	
	@JsonProperty("state")
	private String state;
}
