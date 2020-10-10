/**
 * 
 */
package com.example.RouletteData.repo.dao.interfaces;

import java.util.List;

import com.example.RouletteCommon.dtos.RouletteDTO;

/**
 * @author joals
 *
 */
public interface IRouletteServiceRepo {

	/**
	 * 
	 * @param roulette
	 * @return
	 * @throws Exception
	 */
	public RouletteDTO createRoulette(RouletteDTO roulette) throws Exception;

	/**
	 * 
	 * @param roulette
	 * @return
	 * @throws Exception
	 */
	public RouletteDTO getRouletteById(RouletteDTO roulette) throws Exception;
	
	/**
	 * 
	 * @param roulette
	 * @return
	 * @throws Exception
	 */
	public RouletteDTO openRoulette(RouletteDTO roulette) throws Exception;

	/**
	 * 
	 * @param roulette
	 * @return
	 * @throws Exception
	 */
	public RouletteDTO closeRoulette(RouletteDTO roulette) throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<RouletteDTO> getAllRoulettes() throws Exception;

}
