/**
 * 
 */
package com.example.RouletteApi.services.interfaces;

import java.util.List;

import com.example.RouletteCommon.dtos.BetDTO;
import com.example.RouletteCommon.dtos.RouletteDTO;

/**
 * @author joals
 *
 */
public interface IServiceRoulette {

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public RouletteDTO createRoulette() throws Exception;

	/**
	 * 
	 * @param idRoulette
	 * @return
	 * @throws Exception
	 */
	public RouletteDTO openRoulette(Long idRoulette) throws Exception;

	/**
	 * 
	 * @param bet
	 * @param idRoulette
	 * @return
	 * @throws Exception
	 */
	public RouletteDTO wagerNumberOrColor(BetDTO bet, Long idRoulette) throws Exception;

	/**
	 * 
	 * @param idRoulette
	 * @return
	 * @throws Exception
	 */
	public RouletteDTO closeRoulette(Long idRoulette) throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<RouletteDTO> getAllRoulettes() throws Exception;

}
