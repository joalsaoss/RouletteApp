/**
 * 
 */
package com.example.RouletteCommon.enums;

/**
 * @author joals
 *
 */
public enum MessageEnum {
	/**
	 * File normal messages
	 */
	MESSAGES("messages.messages"),

	/**
	 * File with errors messages
	 */
	ERRORS("messages.errors");

	/**
	 * Package name of messages
	 */
	private String packageName;

	/**
	 * 
	 * @param packageName
	 */
	MessageEnum(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * 
	 * @return
	 */
	public String getPackageName() {
		return packageName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return packageName;
	}
}
