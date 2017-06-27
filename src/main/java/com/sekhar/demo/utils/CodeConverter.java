package com.sekhar.demo.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author Sekhar
 *
 */
public final class CodeConverter {

	/**
	 * @param prefix
	 * @param seq
	 * @return
	 */
	public static final String convertCode(String prefix, String seq) {
		return prefix + StringUtils.leftPad(seq, 5, '0');
	}

	/**
	 * @param prefix
	 * @param seq
	 * @return
	 */
	public static final String convertCode(String prefix, int seq) {
		return prefix + StringUtils.leftPad("" + seq, 5, '0');
	}

	public static final String convert(String prefix, int seq, int n, char replaceWith) {
		return prefix + StringUtils.leftPad("" + seq, n, replaceWith);
	}

}
