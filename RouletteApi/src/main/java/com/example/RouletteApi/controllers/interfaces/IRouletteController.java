/**
 * 
 */
package com.example.RouletteApi.controllers.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.example.RouletteCommon.dtos.BetDTO;
import com.example.RouletteCommon.dtos.RouletteDTO;

/**
 * @author joals
 *
 */
public interface IRouletteController {
	/**
	 * 
	 * @return
	 */
	public ResponseEntity<RouletteDTO> createRoulette();
	
	/**
	 * 
	 * @param idRoulette
	 * @return
	 */
	public ResponseEntity<RouletteDTO> openRoulette(Long idRoulette);
	
	/**
	 * 
	 * @param bet
	 * @param username
	 * @param idRoulette
	 * @return
	 */
	public ResponseEntity<BetDTO> wagerNumberOrColor(BetDTO bet, String username, Long idRoulette);
	
	/**
	 * 
	 * @param idRoulette
	 * @return
	 */
	public ResponseEntity<List<BetDTO>> closeRoulette(Long idRoulette);
	
	/**
	 * 
	 * @return
	 */
	public ResponseEntity<List<RouletteDTO>> getAllRoulettes();
}
