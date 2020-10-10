/**
 * 
 */
package com.example.RouletteApi.controllers.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.RouletteApi.controllers.interfaces.IRouletteController;
import com.example.RouletteApi.services.interfaces.IServiceRoulette;
import com.example.RouletteCommon.dtos.BetDTO;
import com.example.RouletteCommon.dtos.RouletteDTO;
import com.example.RouletteCommon.enums.MessageEnum;
import com.example.RouletteCommon.utils.RouletteConstants;
import com.example.RouletteCommon.utils.RouletteLogger;
import com.example.RouletteCommon.utils.RouletteMessages;

/**
 * @author joals
 *
 */
@Controller
@RequestMapping(RouletteConstants.GET_URL_API)
public class RouletteController implements IRouletteController {

	@Autowired
	private IServiceRoulette service;

	@Autowired
	RouletteLogger rouletteLogger;

	@Override
	@PostMapping(RouletteConstants.CREATE_ROULETTE)
	public ResponseEntity<RouletteDTO> createRoulette() {
		RouletteDTO response = new RouletteDTO();
		try {
			response = service.createRoulette();
		} catch (Exception e) {
			response.setCodeResponse(RouletteConstants.EXCEPCION_RESPONSE);
			response.setMessageResponse(
					RouletteMessages.getMessage(RouletteConstants.MESSAGE_GENERIC_EXCEPCION, MessageEnum.MESSAGES, ""));
			e.printStackTrace();
		}
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping(RouletteConstants.OPEN_ROULETTE + "/{idRoulette}")
	public ResponseEntity<RouletteDTO> openRoulette(@PathVariable Long idRoulette) {
		RouletteDTO response = new RouletteDTO();
		try {
			response = service.openRoulette(idRoulette);
		} catch (Exception e) {
			response.setCodeResponse(RouletteConstants.EXCEPCION_RESPONSE);
			response.setMessageResponse(
					RouletteMessages.getMessage(RouletteConstants.MESSAGE_GENERIC_EXCEPCION, MessageEnum.MESSAGES, ""));
			e.printStackTrace();
		}
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping(RouletteConstants.WAGER + "/{idRoulette}")
	public ResponseEntity<BetDTO> wagerNumberOrColor(@RequestBody BetDTO bet,
			@RequestHeader(value = "username") String username, @PathVariable Long idRoulette) {
		BetDTO response = new BetDTO();
		try {
			bet.setIdRoulette(idRoulette);
			bet.setUsername(username);
			response = service.wagerNumberOrColor(bet);
		} catch (Exception e) {
			response.setCodeResponse(RouletteConstants.EXCEPCION_RESPONSE);
			response.setMessageResponse(
					RouletteMessages.getMessage(RouletteConstants.MESSAGE_GENERIC_EXCEPCION, MessageEnum.MESSAGES, ""));
			e.printStackTrace();
		}
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping(RouletteConstants.CLOSE_ROULETTE + "/{idRoulette}")
	public ResponseEntity<List<BetDTO>> closeRoulette(@PathVariable Long idRoulette) {
		List<BetDTO> response = new ArrayList<BetDTO>();
		BetDTO bet = new BetDTO();
		bet.setIdRoulette(idRoulette);
		try {
			response = service.closeRoulette(bet);
		} catch (Exception e) {
			bet.setCodeResponse(RouletteConstants.EXCEPCION_RESPONSE);
			bet.setMessageResponse(
					RouletteMessages.getMessage(RouletteConstants.MESSAGE_GENERIC_EXCEPCION, MessageEnum.MESSAGES, ""));
			response.add(bet);
			e.printStackTrace();
		}
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping(RouletteConstants.GET_ROULETTES)
	public ResponseEntity<List<RouletteDTO>> getAllRoulettes() {
		List<RouletteDTO> response = new ArrayList<RouletteDTO>();
		try {
			response = service.getAllRoulettes();
		} catch (Exception e) {
			RouletteDTO rouletteDTO = new RouletteDTO();
			rouletteDTO.setCodeResponse(RouletteConstants.EXCEPCION_RESPONSE);
			rouletteDTO.setMessageResponse(
					RouletteMessages.getMessage(RouletteConstants.MESSAGE_GENERIC_EXCEPCION, MessageEnum.MESSAGES, ""));
			response.add(rouletteDTO);
			// TODO: handle exception
		}
		return ResponseEntity.ok(response);
	}
}
