package com.sekhar.demo.utils;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Constant Variables
 * 
 * @author Kent
 *
 */
public interface Global {
	
	/**
	 * Constant variables for web return views
	 */
	final static View JSON_VIEW = new MappingJackson2JsonView();
	
	/**
	 * Constant variables for web return data labels
	 */
	final static String WEB_DATA_LABEL = "data";
	final static String WEB_DRAW_LABEL = "draw";
	final static String WEB_RECORD_TOTAL_LABEL = "recordsTotal";
	final static String WEB_RECORD_FILTERED_LABEL = "recordsFiltered";
	final static String WEB_ERROR_LABEL = "errorMessage";
	
	/**
	 * Date format for DateUtils to parseDate
	 * e.g. DateUtils.parseDate("12/12/2012", Global.dateFormat[0]) 
	 */
	final static String[] dateFormat = { "dd/MM/yyyy", "dd-MM-yyyy", "yyyy/MM/dd", "yyyy-MM-dd" };
	
	final static String JSON_DATE_FORMAT = "dd/MM/yyyy";
	
	final static int VARCHAR255 = 255;
	final static int VARCHAR128 = 128;
	final static int VARCHAR64 = 64;
	final static int VARCHAR32 = 32;
	final static int VARCHAR16 = 16;
	final static int VARCHAR8 = 8;
	final static int VARCHAR4 = 4;
	
	final static String MAP_KEY = "key";
	final static String MAP_VALUE = "value";
	final static String MAP_VALUE2 = "value2";
	
	
	/**
	 * System Config Details 
	 * 
	 */
	
	final static String BUSSINESS_DATE = "BDATE";
	
	final static String GST_DATE = "GSTDATE";
	
	
}
