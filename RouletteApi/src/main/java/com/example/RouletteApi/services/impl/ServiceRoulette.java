package com.example.RouletteApi.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.RouletteApi.services.interfaces.IServiceRoulette;
import com.example.RouletteCommon.dtos.BetDTO;
import com.example.RouletteCommon.dtos.RouletteDTO;
import com.example.RouletteData.models.Bet;
import com.example.RouletteData.models.Roulette;
import com.example.RouletteData.repo.dao.interfaces.IRouletteServiceRepo;

public class ServiceRoulette implements IServiceRoulette {

	@Autowired
	IRouletteServiceRepo rouletteDAO;

	@Override
	public RouletteDTO createRoulette() throws Exception {
		RouletteDTO response = new RouletteDTO();
		Roulette roulette = new Roulette();
		roulette = rouletteDAO.createRoulette();

		if (roulette.getIdRoulette() > 0) {
			response.setCodeResponse(roulette.getCodeResponse());
			response.setMessageResponse(roulette.getMessageResponse());
		} else {
			response.setCodeResponse(roulette.getCodeResponse());
			response.setMessageResponse(roulette.getMessageResponse());
		}
		return response;
	}

	@Override
	public RouletteDTO openRoulette(Long idRoulette) throws Exception {
		RouletteDTO response = new RouletteDTO();
		Roulette roulette = rouletteDAO.openRoulette(idRoulette);
		
		return response;
	}

	@Override
	public RouletteDTO wagerNumberOrColor(BetDTO bet, Long idRoulette) throws Exception {
		RouletteDTO response = new RouletteDTO();
		Roulette roulette = new Roulette();
		Bet bet2 = new Bet();
		bet2.setUsername(bet.getUsername());
		bet2.setUserAmount(bet.getUserAmount());
		bet2.setBetValue(bet.getBetValue());
		bet2.setResultAmount(bet.getResultAmount());
		bet2.setBetResult(bet.getBetResult());
		roulette = rouletteDAO.wagerNumberOrColor(bet2, idRoulette);
		
		return response;
	}

	@Override
	public RouletteDTO closeRoulette(Long idRoulette) throws Exception {
		RouletteDTO response = new RouletteDTO();
		Roulette roulette = new Roulette();
		return response;
	}

	@Override
	public List<RouletteDTO> getAllRoulettes() throws Exception {
		List<RouletteDTO> lstResponse = new ArrayList<RouletteDTO>();
		
		return lstResponse;
	}

}
