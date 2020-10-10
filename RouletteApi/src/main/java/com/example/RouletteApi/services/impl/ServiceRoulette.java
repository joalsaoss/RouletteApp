package com.example.RouletteApi.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RouletteApi.services.interfaces.IServiceRoulette;
import com.example.RouletteCommon.dtos.BetDTO;
import com.example.RouletteCommon.dtos.RouletteDTO;
import com.example.RouletteCommon.enums.MessageEnum;
import com.example.RouletteCommon.utils.RouletteConstants;
import com.example.RouletteCommon.utils.RouletteLogger;
import com.example.RouletteCommon.utils.RouletteMessages;
import com.example.RouletteData.repo.dao.impl.BetServiceRepo;
import com.example.RouletteData.repo.dao.interfaces.IBetServiceRepo;
import com.example.RouletteData.repo.dao.interfaces.IRouletteServiceRepo;

@Service
public class ServiceRoulette implements IServiceRoulette {

	@Autowired
	IRouletteServiceRepo rouletteDAO;

	@Autowired
	IBetServiceRepo betDAO;

	@Autowired
	IBetServiceRepo betRepo;

	@Autowired
	RouletteLogger rouletteLogger;

	@Override
	public RouletteDTO createRoulette() throws Exception {
		RouletteDTO response = new RouletteDTO();
		response.setState(RouletteConstants.ROULETTE_CLOSED);
		response = rouletteDAO.createRoulette(response);
		return response;
	}

	@Override
	public RouletteDTO openRoulette(Long idRoulette) throws Exception {
		RouletteDTO response = new RouletteDTO();
		response.setIdRoulette(idRoulette);
		response.setState(RouletteConstants.OPEN_ROULETTE);
		response = rouletteDAO.openRoulette(response);
		return response;
	}

	@Override
	public BetDTO wagerNumberOrColor(BetDTO bet) throws Exception {
		BetDTO response = new BetDTO();
		RouletteDTO roulette = new RouletteDTO();
		roulette.setIdRoulette(bet.getIdRoulette());
		String messageResponse = "";
		int codResponse = 0;
		String codSeverity = "";
		roulette = rouletteDAO.getRouletteById(roulette);
		if (roulette.getIdRoulette() != -1L) {
			if (roulette.getState().equals(RouletteConstants.ROULETTE_OPEN)) {
				response = betDAO.createWager(bet);
				return response;
			} else {
				messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_NOT_OPEN,
						MessageEnum.MESSAGES, "");
				codSeverity = RouletteConstants.SEVERIDAD_ERROR;
				codResponse = RouletteConstants.ERROR_ROULETTE_NOT_OPEN;
			}
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
	public List<BetDTO> closeRoulette(BetDTO bet) throws Exception {
		String messageResponse = "";
		int codResponse = 0;
		String codSeverity = "";
		List<BetDTO> lstResponse = new ArrayList<BetDTO>();
		RouletteDTO response = new RouletteDTO();
		response.setIdRoulette(bet.getIdRoulette());
		response = rouletteDAO.getRouletteById(response);
		if (response != null) {
			if (response.getState().equals(RouletteConstants.ROULETTE_OPEN)) {
				lstResponse = closeBets(response.getIdRoulette());
				response.setState(RouletteConstants.ROULETTE_CLOSED);
				response = rouletteDAO.closeRoulette(response);
				return lstResponse;
			} else {
				messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_NOT_OPEN,
						MessageEnum.MESSAGES, "");
				codSeverity = RouletteConstants.SEVERIDAD_ERROR;
				codResponse = RouletteConstants.ERROR_ROULETTE_NOT_OPEN;
			}
		} else {
			messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_NOT_FOUND,
					MessageEnum.MESSAGES, "");
			codSeverity = RouletteConstants.SEVERIDAD_ERROR;
			codResponse = RouletteConstants.EXTERNAL_ERROR_RESPONSE;
		}
		bet.setCodeResponse(codResponse);
		bet.setMessageResponse(messageResponse);
		lstResponse.add(bet);
		rouletteLogger.messageLogger(messageResponse, codSeverity, BetServiceRepo.class);
		return lstResponse;
	}

	@Override
	public List<RouletteDTO> getAllRoulettes() throws Exception {
		List<RouletteDTO> lstResponse = new ArrayList<RouletteDTO>();
		lstResponse = rouletteDAO.getAllRoulettes();
		return lstResponse;
	}

	/**
	 * 
	 * @param idRoulette
	 * @return
	 * @throws Exception 
	 */
	private List<BetDTO> closeBets(Long idRoulette) throws Exception {
		List<BetDTO> lstResponse = new ArrayList<BetDTO>();
		BetDTO bet = new BetDTO();
		bet.setIdRoulette(idRoulette);
		lstResponse = betDAO.getBetsByRoulette(bet);
		lstResponse = betDAO.closeBetsByRoulette(lstResponse);
		return lstResponse;
	}
}
