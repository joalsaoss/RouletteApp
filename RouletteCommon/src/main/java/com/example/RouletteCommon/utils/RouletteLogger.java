/**
 * 
 */
package com.example.RouletteCommon.utils;

import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author joals
 *
 */
@Component
public class RouletteLogger {

	public static final RouletteLogger ROULETTE_LOGGER = new RouletteLogger();

	/**
	 * 
	 */
	public RouletteLogger() {
		super();
	}
	
	public void messageLogger(String message, String severity, Class<?> claseName) {
		Logger logger = LogManager.getLogger(claseName);

		switch (severity) {
		case RouletteConstants.SEVERIDAD_DEBUG:
			logger.debug(message);
			break;
		case RouletteConstants.SEVERIDAD_ERROR:
			logger.error(message);
			break;
		case RouletteConstants.SEVERIDAD_FATAL:
			logger.fatal(message);
			break;
		case RouletteConstants.SEVERIDAD_INFO:
			logger.info(message);
			break;
		case RouletteConstants.SEVERIDAD_TRACE:
			logger.trace(message);
			break;
		case RouletteConstants.SEVERIDAD_WARN:
			logger.warn(message);
			break;
		default:
			logger.debug(message);
			break;
		}
	}	
}
