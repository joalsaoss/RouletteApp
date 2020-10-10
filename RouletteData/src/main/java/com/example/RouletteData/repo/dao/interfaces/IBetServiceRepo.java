/**
 * 
 */
package com.example.RouletteData.repo.dao.interfaces;

import java.util.List;

import com.example.RouletteCommon.dtos.BetDTO;

/**
 * @author joals
 *
 */
public interface IBetServiceRepo {
	
	/**
	 * 
	 * @param bet
	 * @return
	 * @throws Exception
	 */
	public BetDTO createWager(BetDTO bet) throws Exception;
	
	/**
	 * 
	 * @param lstBet
	 * @return
	 * @throws Exception
	 */
	public List<BetDTO> closeBetsByRoulette(List<BetDTO> lstBet) throws Exception;
	
	/**
	 * 
	 * @param bet
	 * @return
	 * @throws Exception
	 */
	public List<BetDTO> getBetsByRoulette(BetDTO bet) throws Exception;

}
