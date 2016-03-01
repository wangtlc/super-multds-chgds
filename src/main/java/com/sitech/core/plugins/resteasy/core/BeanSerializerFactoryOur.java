package com.sitech.core.plugins.resteasy.core;

import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.ser.BeanSerializerBuilder;
import org.codehaus.jackson.map.ser.BeanSerializerFactory;
import org.codehaus.jackson.type.JavaType;

public class BeanSerializerFactoryOur extends BeanSerializerFactory {

	protected BeanSerializerFactoryOur(Config config) {
		super(config);
	}

	public JsonSerializer<Object> createSerializer(SerializationConfig config, JavaType origType, BeanProperty property) throws JsonMappingException {
		//		return new BeanSerializerOur(null);
		return super.createSerializer(config, origType, property);
	}

	protected BeanSerializerBuilder constructBeanSerializerBuilder(BasicBeanDescription beanDesc) {
		return new BeanSerializerBuilderOur(beanDesc);
	}
}
