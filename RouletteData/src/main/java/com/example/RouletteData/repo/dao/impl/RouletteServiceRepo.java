/**
 * 
 */
package com.example.RouletteData.repo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.RouletteCommon.dtos.BetDTO;
import com.example.RouletteCommon.dtos.RouletteDTO;
import com.example.RouletteCommon.enums.MessageEnum;
import com.example.RouletteCommon.utils.RouletteConstants;
import com.example.RouletteCommon.utils.RouletteMessages;
import com.example.RouletteData.models.Bet;
import com.example.RouletteData.models.Roulette;
import com.example.RouletteData.repo.IRouletteRepo;
import com.example.RouletteData.repo.dao.interfaces.IRouletteServiceRepo;

/**
 * @author joals
 *
 */
@Component
public class RouletteServiceRepo implements IRouletteServiceRepo {

	@Autowired
	private IRouletteRepo repository;

	@Override
	public Roulette createRoulette() throws Exception {
		Roulette roulette = new Roulette();
		roulette.setState(RouletteConstants.ROULETTE_CLOSED);
		roulette = repository.save(roulette);
		if (roulette!=null) {
			roulette.setCodeResponse(RouletteConstants.SUCCESS_RESPONSE);
			roulette.setMessageResponse(RouletteMessages
					.getMessage(RouletteConstants.MESSAGE_ROULETTE_SUCCESS_CREATE,
					MessageEnum.MESSAGES, ""));
		} else {
			roulette = new Roulette();
			roulette.setCodeResponse(RouletteConstants.EXTERNAL_ERROR_RESPONSE);
			roulette.setMessageResponse(RouletteMessages
					.getMessage(RouletteConstants.MESSAGE_ROULETTE_ERROR_CREATE,
					MessageEnum.MESSAGES, ""));
		}
		return roulette;
	}

	@Override
	public Roulette openRoulette(Long idRoulette) throws Exception {
		Optional<Roulette> opRoulette = repository.findById(idRoulette);
		Roulette roulette = new Roulette();

		if (opRoulette!=null) {
			if (opRoulette.isPresent() 
					&& !opRoulette.get().getState().equals(RouletteConstants.OPEN_ROULETTE)) {
				roulette = opRoulette.get();
				roulette.setState(RouletteConstants.OPEN_ROULETTE);
				repository.save(roulette);
				roulette.setCodeResponse(RouletteConstants.SUCCESS_RESPONSE);
				roulette.setMessageResponse(RouletteMessages
						.getMessage(RouletteConstants.MESSAGE_ROULETTE_SUCCESS_OPEN,
						MessageEnum.MESSAGES, ""));
			}
		} else {
			roulette.setCodeResponse(RouletteConstants.EXTERNAL_ERROR_RESPONSE);
			roulette.setMessageResponse(RouletteMessages
					.getMessage(RouletteConstants.MESSAGE_ROULETTE_NOT_FOUND,
					MessageEnum.MESSAGES, ""));
		}
		return roulette;
	}

	@Override
	public Roulette wagerNumberOrColor(Bet bet, Long idRoulette) throws Exception {
		Roulette roulette = new Roulette();
		List<Bet> lstBets = new ArrayList<>();
		Optional<Roulette> opRoulette = repository.findById(idRoulette);
		
		if (opRoulette!=null) {
			if (opRoulette.isPresent()) {
				roulette = opRoulette.get();
				if (roulette.getState().equals(RouletteConstants.OPEN_ROULETTE)) {
					lstBets = roulette.getBets();
					lstBets.add(bet);
					roulette.setBets(lstBets);
					roulette = repository.save(roulette);
					roulette.setCodeResponse(RouletteConstants.SUCCESS_RESPONSE);
					roulette.setMessageResponse(RouletteMessages
							.getMessage(RouletteConstants.MESSAGE_ROULETTE_SUCCESS_WAGER,
							MessageEnum.MESSAGES, ""));
				} else {
					roulette.setCodeResponse(RouletteConstants.EXTERNAL_ERROR_RESPONSE);
					roulette.setMessageResponse(RouletteMessages
							.getMessage(RouletteConstants.MESSAGE_ROULETTE_NOT_OPEN,
							MessageEnum.MESSAGES, ""));
				}
			} else {
				roulette.setCodeResponse(RouletteConstants.EXTERNAL_ERROR_RESPONSE);
				roulette.setMessageResponse(RouletteMessages
						.getMessage(RouletteConstants.MESSAGE_ROULETTE_NOT_FOUND,
						MessageEnum.MESSAGES, ""));
			}
		}
		return roulette;
	}

	@Override
	public Roulette closeRoulette(Long idRoulette) throws Exception {
		Optional<Roulette> opRoulette = repository.findById(idRoulette);
		Roulette roulette = new Roulette();
		
		if (opRoulette!=null) {
			if (opRoulette.isPresent()) {
				List<Bet> lstBet = new ArrayList<Bet>();
				roulette = opRoulette.get();
				for (Bet bet : roulette.getBets()) {
					int winningNumber = (int) (Math.random() * 36);
					String winningColor = selectColor(winningNumber);
					lstBet.add(validatePrice(bet, winningColor, winningNumber));
				}
				roulette.setState(RouletteConstants.ROULETTE_CLOSED);
				roulette.setBets(new ArrayList<>());
				roulette = repository.save(roulette);
				roulette.setBets(lstBet);
				roulette.setCodeResponse(RouletteConstants.SUCCESS_RESPONSE);
				roulette.setMessageResponse(RouletteMessages
						.getMessage(RouletteConstants.MESSAGE_ROULETTE_SUCCESS_CLOSE,
						MessageEnum.MESSAGES, ""));
			} else {
				roulette.setCodeResponse(RouletteConstants.EXTERNAL_ERROR_RESPONSE);
				roulette.setMessageResponse(RouletteMessages
						.getMessage(RouletteConstants.MESSAGE_ROULETTE_NOT_OPEN,
						MessageEnum.MESSAGES, ""));
			}
		} else {
			roulette.setCodeResponse(RouletteConstants.EXCEPCION_RESPONSE);
			roulette.setMessageResponse(RouletteMessages
					.getMessage(RouletteConstants.MESSAGE_GENERIC_EXCEPCION,
					MessageEnum.MESSAGES, ""));
		}
		return roulette;
	}

	@Override
	public List<Roulette> getAllRoulettes() throws Exception {
		Iterable<Roulette> lstRoulettes;
		List<Roulette> lstResponse = new ArrayList<Roulette>();
		lstRoulettes = repository.findAll();
		if (lstRoulettes != null) {
			for (Roulette roulette : lstRoulettes) {
				lstResponse.add(roulette);
			}
		}
		return lstResponse;
	}
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	private String selectColor(int number) {
		return number % 2 == 0 
				? RouletteConstants.RED_COLOR 
						: RouletteConstants.BLACK_COLOR;
	}

	/**
	 * 
	 * @param betValue
	 * @param winningNumber
	 * @param winningColor
	 * @return
	 */
	private boolean validateWIn(String betValue, int winningNumber, String winningColor) {
		return (betValue.equals(winningColor) 
				|| betValue.equals(String.valueOf(winningNumber)));
	}

	/**
	 * 
	 * @param bet
	 * @param winningColor
	 * @param winningNumber
	 * @return
	 */
	private Bet validatePrice(Bet bet, String winningColor, int winningNumber) {
		if(bet.getBetValue().equals(winningColor) 
				&& validateWIn(bet.getBetValue(), winningNumber, winningColor)) {
			bet.setBetResult(RouletteConstants.MESSAGE_INFO_WINNER);
			bet.setResultAmount(bet.getUserAmount() * 1.8);
			
			return bet;
		}
		else if(bet.getBetValue().equals(String.valueOf(winningNumber)) 
				&& validateWIn(bet.getBetValue(), winningNumber, winningColor)) {
			bet.setBetResult(RouletteConstants.MESSAGE_INFO_WINNER);
			bet.setResultAmount(Double.valueOf(bet.getUserAmount())* 5);
			
			return bet;
		}
		else {
			bet.setBetResult(RouletteConstants.MESSAGE_INFO_LOSER);
			bet.setResultAmount(0.0);

			return bet;
		}
	}
}
