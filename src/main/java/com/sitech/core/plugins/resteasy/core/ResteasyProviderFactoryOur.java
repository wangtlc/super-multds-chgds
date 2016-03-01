package com.sitech.core.plugins.resteasy.core;

import javax.ws.rs.ext.MessageBodyWriter;

import org.jboss.resteasy.plugins.providers.jackson.ResteasyJacksonProvider;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

public class ResteasyProviderFactoryOur extends ResteasyProviderFactory {
	protected void addMessageBodyWriter(MessageBodyWriter provider, Class<?> providerClass, boolean isBuiltin){
		super.addMessageBodyWriter(provider, providerClass, isBuiltin);
		if (provider instanceof ResteasyJacksonProvider) {
			((ResteasyJacksonProvider) provider).setMapper(new ObjectMapperOur());
		}
//		if (provider.getClass().getName().equals("org.codehaus.jackson.jaxrs.JacksonJsonProvider")) {
//		}
//		if (provider.getClass().getName().equals("org.jboss.resteasy.plugins.providers.jackson.ResteasyJacksonProvider")) {
//			((JacksonJsonProvider) provider).setMapper(new ObjectMapperOur());
//		}
	}
}
