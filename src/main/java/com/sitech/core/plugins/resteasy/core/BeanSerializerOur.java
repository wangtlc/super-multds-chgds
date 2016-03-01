package com.sitech.core.plugins.resteasy.core;

import java.io.IOException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.AnyGetterWriter;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;
import org.codehaus.jackson.map.ser.impl.UnwrappingBeanSerializer;
import org.codehaus.jackson.map.ser.std.BeanSerializerBase;
import org.codehaus.jackson.type.JavaType;

import com.sitech.core.architecture.bean.RetInfo;
import com.sitech.core.plugins.resteasy.core.serializer.ISerializer;
import com.sitech.core.plugins.resteasy.core.serializer.SerializerFactory;

public class BeanSerializerOur extends BeanSerializerBase {
	private static Logger logger = Logger.getLogger(BeanSerializerOur.class);

	/*
	 * /********************************************************** /* Life-cycle: constructors /**********************************************************
	 */

	/**
	 * @param type
	 *            Nominal type of values handled by this serializer
	 * @param properties
	 *            Property writers used for actual serialization
	 */
	public BeanSerializerOur(JavaType type, BeanPropertyWriter[] properties, BeanPropertyWriter[] filteredProperties, AnyGetterWriter anyGetterWriter, Object filterId) {
		super(type, properties, filteredProperties, anyGetterWriter, filterId);
	}

	public BeanSerializerOur(Class<?> rawType, BeanPropertyWriter[] properties, BeanPropertyWriter[] filteredProperties, AnyGetterWriter anyGetterWriter, Object filterId) {
		super(rawType, properties, filteredProperties, anyGetterWriter, filterId);
	}

	/**
	 * Copy-constructor that is useful for sub-classes that just want to copy all super-class properties without modifications.
	 * 
	 * @since 1.7
	 */
	protected BeanSerializerOur(BeanSerializerOur src) {
		super(src);
	}

	/*
	 * /********************************************************** /* Life-cycle: factory methods, fluent factories /**********************************************************
	 */

	@Override
	public JsonSerializer<Object> unwrappingSerializer() {
		return new UnwrappingBeanSerializer(this);
	}

	/*
	 * /********************************************************** /* JsonSerializer implementation that differs between impls /**********************************************************
	 */

	/**
	 * Main serialization method that will delegate actual output to configured {@link BeanPropertyWriter} instances.
	 */
	@Override
	public final void serialize(Object bean, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
		jgen.writeStartObject();
		Set<String> ignoreNameSet=null;
		try {
			ISerializer iSerializer = SerializerFactory.getSerializer(bean);
			if (iSerializer != null) {
				ignoreNameSet = iSerializer.preDeal(bean, jgen, provider);
				if (bean instanceof RetInfo) {
					RetInfo temp = (RetInfo) bean;
//					temp.setRetCode("3018");
//					if (RetCodeConstant.ESB_CODE_3018.equals(temp.getRetCode())) {
//						// 3018，用户session销毁，需要用户重新登录
//						bean = LogOutUtil.userWaslogouted(EchnContextUtils.getRequest());
//					}
				}
			}
		} catch (Exception e) {
			logger.error("前置处理失败!", e);
		}

		if (_propertyFilterId != null) {
			serializeFieldsFiltered(bean, jgen, provider);
		} else {
			serializeFields(bean, jgen, provider,ignoreNameSet);
		}
		jgen.writeEndObject();
	}

	/*
	 * /********************************************************** /* Standard methods /**********************************************************
	 */
	protected void serializeFields(Object bean, JsonGenerator jgen, SerializerProvider provider,Set ignoreNameSet) throws IOException, JsonGenerationException {
		final BeanPropertyWriter[] props;
		if (_filteredProps != null && provider.getSerializationView() != null) {
			props = _filteredProps;
		} else {
			props = _props;
		}
		int i = 0;
		try {
			for (final int len = props.length; i < len; ++i) {
				BeanPropertyWriter prop = props[i];
				if (prop != null) { // can have nulls in filtered list
					if (ignoreNameSet!=null && ignoreNameSet.contains(prop.getName())) {
						logger.debug("滤过"+prop.getName());
					}else {
						prop.serializeAsField(bean, jgen, provider);
					}
				}
			}
			if (_anyGetterWriter != null) {
				_anyGetterWriter.getAndSerialize(bean, jgen, provider);
			}
		} catch (Exception e) {
			String name = (i == props.length) ? "[anySetter]" : props[i].getName();
			wrapAndThrow(provider, e, bean, name);
		} catch (StackOverflowError e) {
			/*
			 * 04-Sep-2009, tatu: Dealing with this is tricky, since we do not have many stack frames to spare... just one or two; can't make many calls.
			 */
			JsonMappingException mapE = new JsonMappingException("Infinite recursion (StackOverflowError)", e);
			String name = (i == props.length) ? "[anySetter]" : props[i].getName();
			mapE.prependPath(new JsonMappingException.Reference(bean, name));
			throw mapE;
		}
	}

	@Override
	public String toString() {
		return "BeanSerializer for " + handledType().getName();
	}

}
