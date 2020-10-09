/**
 * 
 */
package com.example.RouletteCommon.utils;

/**
 * @author joals
 *
 */
public class RouletteConstants {
	/**
	 * Log severity
	 */
	public static final String SEVERIDAD_DEBUG = "debug";
	public static final String SEVERIDAD_ERROR = "error";
	public static final String SEVERIDAD_FATAL = "fatal";
	public static final String SEVERIDAD_INFO = "info";
	public static final String SEVERIDAD_TRACE = "trace";
	public static final String SEVERIDAD_WARN = "warn";

	/**
	 * Endpoints
	 */
	public static final String GET_URL_API = "/roulette";
	public static final String CREATE_ROULETTE = "/create-roulette";
	public static final String OPEN_ROULETTE = "/open-roulette";
	public static final String WAGER = "/wager";
	public static final String GET_ROULETTES = "/get-roulettes";
	public static final String CLOSE_ROULETTE = "/close-roulette";
	
	public static final String MESSAGE_CREATED = "Se ha creado la ruleta";
	public static final String ROULETTE_OPEN = "abierta";
	public static final String ROULETTE_CLOSED = "cerrada";
	
	public static final String OK = "Solicitud realizada con exito";
	public static final String FAILED = "Solicitud realizada sin exito";
	
	public static final String RED_COLOR = "red";
	public static final String BLACK_COLOR = "black";
	
	public static final String MESSAGE_INFO_WINNER = "winner";
	public static final String MESSAGE_INFO_LOSER = "loser";

	/**
	 * Expceptions messages
	 */
	public static final String MESSAGE_GENERIC_EXCEPCION = "message_generic_exception";

	/**
	 * Response messages
	 */
	public static final String MESSAGE_SUCCESS_RESPONSE = "message_success_response";
	public static final String MESSAGE_ERROR_RESPONSE = "message_error_response";
	public static final String MESSAGE_EXCEPTION = "message_exception";
	public static final String MESSAGE_NOT_FOUND = "Mensaje no encontrado";

	public static final String MESSAGE_ROULETTE_SUCCESS_CREATE = "message_roulette_success_create";
	public static final String MESSAGE_ROULETTE_SUCCESS_OPEN = "message_roulette_success_open";
	public static final String MESSAGE_ROULETTE_SUCCESS_WAGER = "message_roulette_success_wager";
	public static final String MESSAGE_ROULETTE_SUCCESS_CLOSE = "message_roulette_success_close";
	public static final String MESSAGE_ROULETTE_SUCCESS_GETALL = "message_roulette_success_getall";
	
	public static final String MESSAGE_ROULETTE_NOT_OPEN = "message_roulette_not_open";
	public static final String MESSAGE_ROULETTE_NOT_FOUND = "message_roulette_not_found";
	
	public static final String MESSAGE_ROULETTE_ERROR_CREATE = "message_roulette_error_create";
	public static final String MESSAGE_ROULETTE_ERROR_OPEN = "message_roulette_error_open";
	public static final String MESSAGE_ROULETTE_ERROR_WAGER = "message_roulette_error_wager";
	public static final String MESSAGE_ROULETTE_ERROR_CLOSE = "message_roulette_error_close";
	public static final String MESSAGE_ROULETTE_ERROR_GETALL = "message_roulette_error_getall";
	/**
	 * Response HTTP
	 */
	public static final int SUCCESS_RESPONSE = 200;
	public static final int EXTERNAL_ERROR_RESPONSE = 400;
	public static final int EXCEPCION_RESPONSE = 500;

	/**
	 * Datetime formats
	 */
	public static final String FORMAT_DATETIME_SHORT_WITH_DASH = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_DATE_SHORT_WITH_DASH = "yyyy-MM-dd";
	public static final String FORMAT_DATETIME_SHORT_WITH_SLASH = "yyyy/MM/dd HH:mm:ss";
	public static final String FORMAT_DATE_SHORT_WITH_SLASH = "yyyy/MM/dd";
	public static final String FORMAT_DATE_CREDITCARD_WITH_SLASH = "yyyy/MM";
	public static final String FORMAT_TIME = "HH:mm:ss";
	public static final String FORMAT_SHORT_TIME = "HH:mm";
}
