package com.hoaxify.ws.configuration;
/*
 * Created by Oray Kurt
 * Date: 08-Jun-20
 * Time: 8:34 AM
 */

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class PageSerializer extends JsonSerializer<Page<?>> {
	@Override
	public void serialize(Page<?> object, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject();

		jsonGenerator.writeFieldName("content");
		serializerProvider.defaultSerializeValue(object.getContent(),jsonGenerator);

		jsonGenerator.writeObjectField("pageable", object.getPageable());
		jsonGenerator.writeBooleanField("last", object.isLast());
		jsonGenerator.writeNumberField("totalPages", object.getTotalPages());
		jsonGenerator.writeNumberField("totalElements", object.getTotalElements());
		jsonGenerator.writeNumberField("size", object.getSize());
		jsonGenerator.writeNumberField("number", object.getNumber());
		jsonGenerator.writeObjectField("sort", object.getSort());
		jsonGenerator.writeNumberField("numberOfElements", object.getNumberOfElements());
		jsonGenerator.writeBooleanField("first", object.isFirst());
		jsonGenerator.writeBooleanField("empty", object.isEmpty());

		jsonGenerator.writeEndObject();
	}
}
