package com.sitech.core.plugins.resteasy.core.serializer;

import java.util.Set;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;

public interface ISerializer {
	/**
	 * JAVA=>JSON前置处理器
	 * @param bean
	 * @param jgen
	 * @param provider
	 * @return  不需要输出的BEAN成员
	 * @throws Exception
	 */
	public Set<String> preDeal(Object bean, JsonGenerator jgen, SerializerProvider provider) throws Exception;
}
