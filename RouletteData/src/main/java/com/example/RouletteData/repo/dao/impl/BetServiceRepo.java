package com.example.RouletteData.repo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.RouletteCommon.dtos.BetDTO;
import com.example.RouletteCommon.enums.MessageEnum;
import com.example.RouletteCommon.utils.RouletteConstants;
import com.example.RouletteCommon.utils.RouletteLogger;
import com.example.RouletteCommon.utils.RouletteMessages;
import com.example.RouletteData.mappers.BetMapper;
import com.example.RouletteData.models.Bet;
import com.example.RouletteData.models.Roulette;
import com.example.RouletteData.repo.IBetRepo;
import com.example.RouletteData.repo.dao.interfaces.IBetServiceRepo;

@Service
@Transactional
public class BetServiceRepo implements IBetServiceRepo {

	@Autowired
	IBetRepo repository;
	
	@Autowired
	BetMapper betMapper;
	
	@Autowired
	RouletteLogger rouletteLogger;

	@Override
	public BetDTO createWager(BetDTO bet) throws Exception {
		BetDTO response = new BetDTO();
		bet.setBetResult(RouletteConstants.MESSAGE_INFO_PLAYING);
		Bet betEntity = betMapper.betDTOToEntity(bet);
		String messageResponse = "";
		int codResponse = 0;
		String codSeverity = "";
		response = betMapper.betEntityToDTO(repository.save(betEntity));
		if (response != null) {
			messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_SUCCESS_WAGER,
					MessageEnum.MESSAGES, "");
			codSeverity = RouletteConstants.SEVERIDAD_INFO;
			codResponse = RouletteConstants.SUCCESS_RESPONSE;
		} else {
			messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_NOT_FOUND,
					MessageEnum.MESSAGES, "");
			codSeverity = RouletteConstants.SEVERIDAD_ERROR;
			codResponse = RouletteConstants.EXTERNAL_ERROR_RESPONSE;
		}
		response.setCodeResponse(codResponse);
		response.setMessageResponse(messageResponse);
		rouletteLogger.messageLogger(messageResponse, codSeverity, BetServiceRepo.class);
		return response;
	}
	
	@Override
	public List<BetDTO> getBetsByRoulette(BetDTO bet) throws Exception {
		BetDTO betDTO = new BetDTO();
		Iterable<Bet> lstBets = new ArrayList<Bet>();
		List<BetDTO> lstResponse = new ArrayList<BetDTO>();
		lstBets = repository.findAll();
		if (lstBets!=null) {
			for (Bet betEntity : lstBets) {
				if (betEntity.getIdRoulette().equals(bet.getIdRoulette())) {
					lstResponse.add(betMapper.betEntityToDTO(betEntity));
				}
			}
		}
		return lstResponse;
	}
	
	@Override
	public List<BetDTO> closeBetsByRoulette(List<BetDTO> lstBet) throws Exception {
		int winningNumber = (int) (Math.random() * 36);
		String winningColor = selectColor(winningNumber);
		for (BetDTO betDTO : lstBet) {
			if (betDTO.getBetResult().equals(RouletteConstants.MESSAGE_INFO_PLAYING)) {
				if (betDTO.getBetValue().equals(winningColor)) {
					betDTO.setBetResult(RouletteConstants.MESSAGE_INFO_WINNER);
					betDTO.setResultAmount(betDTO.getUserAmount() * RouletteConstants.MULT_BY_COLOR);
				} else if (betDTO.getBetValue().equals(String.valueOf(winningNumber))) {
					betDTO.setBetResult(RouletteConstants.MESSAGE_INFO_WINNER);
					betDTO.setResultAmount(betDTO.getUserAmount() * RouletteConstants.MULT_BY_NUMBER);
				} else {
					betDTO.setBetResult(RouletteConstants.MESSAGE_INFO_LOSER);
					betDTO.setResultAmount(0.0);
				}
			}
		}
		return lstBet;
	}
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	private String selectColor(int number) {
		return number % 2 == 0 ? RouletteConstants.RED_COLOR : RouletteConstants.BLACK_COLOR;
	}
}
