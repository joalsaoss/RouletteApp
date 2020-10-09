/**
 * 
 */
package com.example.RouletteCommon.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.TimeValidator;

/**
 * @author joals
 *
 */
public class RouletteValidations {
	private static DateValidator dateValidator = DateValidator.getInstance();
	private static DoubleValidator doubleValidator = DoubleValidator.getInstance();
	private static EmailValidator emailValidator = EmailValidator.getInstance();
	private static IntegerValidator integerValidator = IntegerValidator.getInstance();
	private static TimeValidator timeValidator = TimeValidator.getInstance();

	static SimpleDateFormat simpleDateFormat;

	/**
	 * 
	 * @param pValue
	 * @return
	 */
	public static boolean isValidDouble(String pValue) {
		return doubleValidator.isValid(pValue);
	}

	/**
	 * 
	 * @param pValue
	 * @return
	 */
	public static boolean isValidDate(String pValue) {
		Date date = dateValidator.validate(pValue, RouletteConstants.FORMAT_DATE_SHORT_WITH_DASH);
		
		if (date != null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param pValue
	 * @return
	 */
	public static boolean isValidDateTime(String pValue) {
		Date date = dateValidator.validate(pValue, RouletteConstants.FORMAT_DATETIME_SHORT_WITH_DASH);
		if (date != null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param pValue
	 * @return
	 */
	public static boolean isValidTime(String pValue) {
		Calendar time = timeValidator.validate(pValue, RouletteConstants.FORMAT_SHORT_TIME);
		if (time != null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param pValue
	 * @return
	 */
	public static boolean isValidLongTime(String pValue) {
		Calendar time = timeValidator.validate(pValue, RouletteConstants.FORMAT_DATETIME_SHORT_WITH_DASH);
		if (time != null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param pValue
	 * @return
	 */
	public static boolean isValidEmail(String pValue) {
		return emailValidator.isValid(pValue);
	}

	/**
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean isIntegerNumber(final String pValue) {
		// return integerValidator.isValid(pValue, "##", Locale.getDefault());
		return integerValidator.isValid(pValue);
	}

	/**
	 * 
	 * @param pValue
	 * @return
	 */
	public static boolean isEmptyString(final Object pValue) {
		if (pValue == null) {
			return true;
		}
		String valueTrim = pValue.toString().trim();
		if ((valueTrim).equals("")) {
			return true;
		}
		if ((valueTrim).equals("null")) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param pValue
	 * @param pSpaces
	 * @return
	 */
	public static boolean isAlphaNumeric(final String pValue, final boolean pSpaces) {
		Pattern pat;
		if (pSpaces) {
			pat = Pattern.compile("[A-Za-z0-9 áéíóúÁÉÍÓÚÑñ]+");
		} else {
			pat = Pattern.compile("[A-Za-z0-9áéíóúÁÉÍÓÚÑñ]+");
		}
		final Matcher mat = pat.matcher(pValue);
		return mat.matches();
	}

	/**
	 * 
	 * @param pValue
	 * @param pSpaces
	 * @return
	 */
	public static boolean isAlphabetical(final String pValue, final boolean pSpaces) {
		Pattern pat;
		if (pSpaces) {
			pat = Pattern.compile("[A-Za-z áéíóúAÉÍÓÚÑñ]+");
		} else {
			pat = Pattern.compile("[A-Za-záéíóúAÉÍÓÚÑñ]+");
		}

		final Matcher matcher = pat.matcher(pValue);
		return matcher.matches();
	}

	/**
	 * 
	 * @param pDate
	 * @return
	 */
	public static String formatDate(Date pDate) {
		simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(pDate);
	}

	/**
	 * 
	 * @param pValue
	 * @return
	 */
	public static boolean isPhone(final String pValue) {
		Pattern pat = Pattern.compile("[()0-9- .*# (Ext) (ext)]+");
		final Matcher matcher = pat.matcher(pValue);
		return matcher.matches();
	}

	/**
	 * 
	 * @param pValue
	 * @return
	 */
	public static boolean isAddress(final String pValue) {
		Pattern pat = Pattern.compile("[()A-Za-z0-9- áéíóúAÉÍÓÚÑñ.*#/]+");
		final Matcher matcher = pat.matcher(pValue);
		return matcher.matches();
	}

	/**
	 * 
	 * @param pValue
	 * @return
	 */
	public static boolean isQuantity(final String pValue) {
		Pattern pat;
		pat = Pattern.compile("[0-9]{1,20}");
		final Matcher mat = pat.matcher(pValue);
		return mat.matches();
	}

	/**
	 * 
	 * @param pValue
	 * @param minlength
	 * @param maxlength
	 * @return
	 */
	public static boolean isValidLength(final String pValue, final int minlength, final int maxlength) {
		boolean result = false;

		if (pValue.length() < minlength) {
			result = true;
		}

		if (pValue.length() > maxlength) {
			result = true;
		}

		return result;
	}

	/**
	 * 
	 * @param pValue
	 * @return
	 */
	public static boolean isAccountnumber(final String pValue) {
		Pattern pat;
		pat = Pattern.compile("[0-9]{1,20}");
		final Matcher mat = pat.matcher(pValue);
		return mat.matches();
	}

	/**
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static String convertDateToString(String format, Date date) {
		DateFormat dDateStart = new SimpleDateFormat(format);
		return dDateStart.format(date);
	}

	/**
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static Date convertStringToDate(String format, String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
