/**
 * 
 */
package com.example.RouletteData.repo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.RouletteCommon.dtos.BetDTO;
import com.example.RouletteCommon.dtos.RouletteDTO;
import com.example.RouletteCommon.enums.MessageEnum;
import com.example.RouletteCommon.utils.RouletteConstants;
import com.example.RouletteCommon.utils.RouletteLogger;
import com.example.RouletteCommon.utils.RouletteMessages;
import com.example.RouletteData.mappers.RouletteMapper;
import com.example.RouletteData.models.Bet;
import com.example.RouletteData.models.Roulette;
import com.example.RouletteData.repo.IRouletteRepo;
import com.example.RouletteData.repo.dao.interfaces.IRouletteServiceRepo;

/**
 * @author joals
 *
 */
@Service
@Transactional
public class RouletteServiceRepo implements IRouletteServiceRepo {

	@Autowired
	private IRouletteRepo repository;

	@Autowired
	RouletteMapper rouletteMapper;

	@Autowired
	RouletteLogger rouletteLogger;

	@Override
	public RouletteDTO createRoulette(RouletteDTO roulette) throws Exception {
		Roulette rouletteEntity = rouletteMapper.rouleteDTOToEntity(roulette);
		roulette = saveRoulette(rouletteEntity);
		String messageResponse = "";
		int codResponse = 0;
		String codSeverity = "";
		if (roulette != null) {
			if (roulette.getCodeResponse() != RouletteConstants.ERROR_ENTITY_NULL) {
				messageResponse = roulette.getCodeResponse() == RouletteConstants.SUCCESS_RESPONSE
						? RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_SUCCESS_CREATE,
								MessageEnum.MESSAGES, "")
						: RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_ERROR_CREATE,
								MessageEnum.MESSAGES, "");
				codSeverity = roulette.getCodeResponse() == RouletteConstants.SUCCESS_RESPONSE
						? RouletteConstants.SEVERIDAD_INFO
						: RouletteConstants.SEVERIDAD_ERROR;
				codResponse = roulette.getCodeResponse() == RouletteConstants.SUCCESS_RESPONSE
						? RouletteConstants.SUCCESS_RESPONSE
						: RouletteConstants.EXTERNAL_ERROR_RESPONSE;
			}
		} else {
			roulette = new RouletteDTO();
			messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_GENERIC_EXCEPCION,
					MessageEnum.MESSAGES, "");
			codSeverity = RouletteConstants.SEVERIDAD_FATAL;
			codResponse = RouletteConstants.EXCEPCION_RESPONSE;
		}
		roulette.setCodeResponse(codResponse);
		roulette.setMessageResponse(messageResponse);
		rouletteLogger.messageLogger(messageResponse, codSeverity, RouletteServiceRepo.class);
		return roulette;
	}

	@Override
	public RouletteDTO getRouletteById(RouletteDTO roulette) throws Exception {
		Optional<Roulette> opRoulette = repository.findById(roulette.getIdRoulette());
		String messageResponse = "";
		int codResponse = 0;
		String codSeverity = "";
		if (opRoulette.isPresent()) {
			roulette = rouletteMapper.rouletteEntityToDTO(opRoulette.get());
			messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_FOUND,
					MessageEnum.MESSAGES, "");
			codSeverity = RouletteConstants.SEVERIDAD_INFO;
			codResponse = RouletteConstants.SUCCESS_RESPONSE;

		} else {
			roulette.setIdRoulette(-1L);
			messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_NOT_FOUND,
					MessageEnum.MESSAGES, "");
			codSeverity = RouletteConstants.SEVERIDAD_ERROR;
			codResponse = RouletteConstants.EXTERNAL_ERROR_RESPONSE;
		}
		roulette.setCodeResponse(codResponse);
		roulette.setMessageResponse(messageResponse);
		rouletteLogger.messageLogger(messageResponse, codSeverity, RouletteServiceRepo.class);
		return roulette;
	}

	@Override
	public RouletteDTO openRoulette(RouletteDTO roulette) throws Exception {
		Optional<Roulette> opRoulette = repository.findById(roulette.getIdRoulette());
		Roulette rouletteEntity = new Roulette();
		String messageResponse = "";
		int codResponse = 0;
		String codSeverity = "";
		if (opRoulette != null) {
			if (opRoulette.isPresent() && !opRoulette.get().getState().equals(RouletteConstants.ROULETTE_OPEN)) {
				rouletteEntity = opRoulette.get();
				rouletteEntity.setState(RouletteConstants.ROULETTE_OPEN);
				roulette = saveRoulette(rouletteEntity);
				if (roulette != null) {
					if (roulette.getCodeResponse() != RouletteConstants.ERROR_ENTITY_NULL) {
						messageResponse = roulette.getCodeResponse() == RouletteConstants.SUCCESS_RESPONSE
								? RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_SUCCESS_OPEN,
										MessageEnum.MESSAGES, "")
								: RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_ERROR_OPEN,
										MessageEnum.MESSAGES, "");
						codSeverity = roulette.getCodeResponse() == RouletteConstants.SUCCESS_RESPONSE
								? RouletteConstants.SEVERIDAD_INFO
								: RouletteConstants.SEVERIDAD_ERROR;
						codResponse = roulette.getCodeResponse() == RouletteConstants.SUCCESS_RESPONSE
								? RouletteConstants.SUCCESS_RESPONSE
								: RouletteConstants.EXTERNAL_ERROR_RESPONSE;
					}
				} else {
					roulette = new RouletteDTO();
					messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_ISNULL,
							MessageEnum.MESSAGES, "");
					codSeverity = RouletteConstants.SEVERIDAD_ERROR;
					codResponse = RouletteConstants.ERROR_ENTITY_NULL;
				}
			} else {
				messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_NOT_FOUND,
						MessageEnum.MESSAGES, "");
				codSeverity = RouletteConstants.SEVERIDAD_ERROR;
				codResponse = RouletteConstants.ERROR_ENTITY_NOT_FOUND;
			}
		} else {
			messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_ERROR_RESPONSE,
					MessageEnum.MESSAGES, "");
			codSeverity = RouletteConstants.SEVERIDAD_ERROR;
			codResponse = RouletteConstants.ERROR_ENTITY_NULL;
		}
		roulette.setCodeResponse(codResponse);
		roulette.setMessageResponse(messageResponse);
		rouletteLogger.messageLogger(messageResponse, codSeverity, RouletteServiceRepo.class);
		return roulette;
	}
	
	@Override
	public RouletteDTO closeRoulette(RouletteDTO roulette) throws Exception {
		Optional<Roulette> opRoulette = repository.findById(roulette.getIdRoulette());
		Roulette rouletteEntity = new Roulette();
		String messageResponse = "";
		int codResponse = 0;
		String codSeverity = "";
		if (opRoulette != null) {
			if (opRoulette.isPresent() && opRoulette.get().getState().equals(RouletteConstants.ROULETTE_OPEN)) {
				rouletteEntity = opRoulette.get();
				rouletteEntity.setState(RouletteConstants.ROULETTE_CLOSED);
				roulette = saveRoulette(rouletteEntity);
				if (roulette != null) {
					if (roulette.getCodeResponse() != RouletteConstants.ERROR_ENTITY_NULL) {
						messageResponse = roulette.getCodeResponse() == RouletteConstants.SUCCESS_RESPONSE
								? RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_SUCCESS_OPEN,
										MessageEnum.MESSAGES, "")
								: RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_ERROR_OPEN,
										MessageEnum.MESSAGES, "");
						codSeverity = roulette.getCodeResponse() == RouletteConstants.SUCCESS_RESPONSE
								? RouletteConstants.SEVERIDAD_INFO
								: RouletteConstants.SEVERIDAD_ERROR;
						codResponse = roulette.getCodeResponse() == RouletteConstants.SUCCESS_RESPONSE
								? RouletteConstants.SUCCESS_RESPONSE
								: RouletteConstants.EXTERNAL_ERROR_RESPONSE;
					}
				} else {
					roulette = new RouletteDTO();
					messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_ISNULL,
							MessageEnum.MESSAGES, "");
					codSeverity = RouletteConstants.SEVERIDAD_ERROR;
					codResponse = RouletteConstants.ERROR_ENTITY_NULL;
				}
			} else {
				messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_NOT_FOUND,
						MessageEnum.MESSAGES, "");
				codSeverity = RouletteConstants.SEVERIDAD_ERROR;
				codResponse = RouletteConstants.ERROR_ENTITY_NOT_FOUND;
			}
		} else {
			messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_ERROR_RESPONSE,
					MessageEnum.MESSAGES, "");
			codSeverity = RouletteConstants.SEVERIDAD_ERROR;
			codResponse = RouletteConstants.ERROR_ENTITY_NULL;
		}
		roulette.setCodeResponse(codResponse);
		roulette.setMessageResponse(messageResponse);
		rouletteLogger.messageLogger(messageResponse, codSeverity, RouletteServiceRepo.class);
		return roulette;
	}

	@Override
	public List<RouletteDTO> getAllRoulettes() throws Exception {
		List<RouletteDTO> response = new ArrayList<RouletteDTO>();
		Iterable<Roulette> lstOpRoulettes;
		List<Roulette> lstRoulettes = new ArrayList<Roulette>();
		String messageResponse = "";
		String codSeverity = "";
		lstOpRoulettes = repository.findAll();
		if (lstOpRoulettes != null) {
			for (Roulette roulette : lstOpRoulettes) {
				lstRoulettes.add(roulette);
			}
			response = rouletteMapper.rouletteEntitiesToDTO(lstRoulettes);
			messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_SUCCESS_GETALL,
					MessageEnum.MESSAGES, "");
			codSeverity = RouletteConstants.SEVERIDAD_INFO;
		} else {
			messageResponse = RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_ERROR_GETALL,
					MessageEnum.MESSAGES, "");
			codSeverity = RouletteConstants.SEVERIDAD_ERROR;
		}
		rouletteLogger.messageLogger(messageResponse, codSeverity, RouletteServiceRepo.class);
		return response;
	}

	/**
	 * 
	 * @param roulette
	 * @return
	 */
	private RouletteDTO saveRoulette(Roulette roulette) {
		RouletteDTO response = new RouletteDTO();
		if (roulette != null) {
			response = rouletteMapper.rouletteEntityToDTO(repository.save(roulette));
			if (response != null) {
				response.setCodeResponse(RouletteConstants.SUCCESS_RESPONSE);
			} else {
				response.setCodeResponse(RouletteConstants.EXTERNAL_ERROR_RESPONSE);
			}
		} else {
			response.setCodeResponse(RouletteConstants.ERROR_ENTITY_NULL);
			response.setMessageResponse(
					RouletteMessages.getMessage(RouletteConstants.MESSAGE_ROULETTE_ISNULL, MessageEnum.MESSAGES, ""));
		}
		return response;
	}
}
