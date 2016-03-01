package com.sitech.core.plugins.resteasy.core;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class ObjectMapperOur extends ObjectMapper {
	public void writeValue(JsonGenerator jGenerator, Object value) throws IOException, JsonGenerationException, JsonMappingException {
		commonDeal();
		super.writeValue(jGenerator, value);

	}

	private void commonDeal() {
		//		jgen.writeStringField("name", "mkyong"); // "name" : "mkyong"   
		//		jgen.writeNumberField("age", 29); // "age" : 29   
		disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		/**null过滤开关*/
//		configure(SerializationConfig.Feature.WRITE_NULL_PROPERTIES, false);

		//		jGenerator.writeStartArray(); // [
		//		jGenerator.writeString("msg 1"); // "msg 1"
		//		jGenerator.writeString("msg 2"); // "msg 2"
		//		jGenerator.writeString("msg 3"); // "msg 3"
		//		jGenerator.writeEndArray(); // ]
		//		_serializerProvider=new StdSerializerProviderOur();
		_serializerFactory = new BeanSerializerFactoryOur(null);
	}

}
