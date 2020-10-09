/**
 * 
 */
package com.example.RouletteData.repo.dao.interfaces;

import java.util.List;

import com.example.RouletteCommon.dtos.BetDTO;
import com.example.RouletteCommon.dtos.RouletteDTO;
import com.example.RouletteData.models.Bet;
import com.example.RouletteData.models.Roulette;

/**
 * @author joals
 *
 */
public interface IRouletteServiceRepo {

	/**
	 * 
	 * @return
	 */
	public Roulette createRoulette() throws Exception;

	/**
	 * 
	 * @param idRoulette
	 * @return
	 * @throws Exception
	 */
	public Roulette openRoulette(Long idRoulette) throws Exception;

	/**
	 * 
	 * @param bet
	 * @param idRoulette
	 * @return
	 * @throws Exception
	 */
	public Roulette wagerNumberOrColor(Bet bet, Long idRoulette) throws Exception;

	/**
	 * 
	 * @param idRoulette
	 * @return
	 * @throws Exception
	 */
	public Roulette closeRoulette(Long idRoulette) throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Roulette> getAllRoulettes() throws Exception;

}
