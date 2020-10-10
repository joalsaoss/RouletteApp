/**
 * 
 */
package com.example.RouletteData.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author joals
 *
 */
@RedisHash("Roulette")
@Data
public class Roulette {
	@Id
	private Long idRoulette;
	
	@JsonProperty("state")
	private String state;	
}
