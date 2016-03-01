package com.sitech.core.plugins.resteasy.core;

import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;
import org.codehaus.jackson.map.ser.BeanSerializerBuilder;

public class BeanSerializerBuilderOur extends BeanSerializerBuilder {
	private final static BeanPropertyWriter[] NO_PROPERTIES = new BeanPropertyWriter[0];

	protected BeanSerializerBuilderOur(BasicBeanDescription src) {
		super(src);
	}

	public JsonSerializer<?> build() {
		BeanPropertyWriter[] properties;
		// No properties or any getter? No real serializer; caller gets to handle
		if (_properties == null || _properties.isEmpty()) {
			if (_anyGetter == null) {
				return null;
			}
			properties = NO_PROPERTIES;
		} else {
			properties = _properties.toArray(new BeanPropertyWriter[_properties.size()]);

		}
		return new BeanSerializerOur(_beanDesc.getType(), properties, _filteredProperties, _anyGetter, _filterId);
	}

}
