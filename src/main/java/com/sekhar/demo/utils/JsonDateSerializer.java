package com.sekhar.demo.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Json Date Serializer
 * 
 * @author Sekhar
 *
 */
@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

	/**
	 * Format Simple Date Format.
	 */

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
	 */
	public final void serialize(final Date date, final JsonGenerator gen, final SerializerProvider provider)
			throws IOException {
		String formattedDate = new SimpleDateFormat(Global.JSON_DATE_FORMAT).format(date);
		gen.writeString(formattedDate);
	}
}
