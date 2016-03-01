package com.sitech.core.plugins.resteasy.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.plugins.server.servlet.ListenerBootstrap;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.resteasy.spi.Registry;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

public class ResteasyBootstrapOur extends ResteasyBootstrap {
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();

		ListenerBootstrap config = new ListenerBootstrapOur(event.getServletContext());
		deployment = config.createDeployment();
		deployment.start();

		servletContext.setAttribute(ResteasyProviderFactory.class.getName(), deployment.getProviderFactory());
		servletContext.setAttribute(Dispatcher.class.getName(), deployment.getDispatcher());
		servletContext.setAttribute(Registry.class.getName(), deployment.getRegistry());
	}
}
