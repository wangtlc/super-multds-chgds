package com.sitech.core.plugins.resteasy.core;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.jboss.resteasy.plugins.server.servlet.ListenerBootstrap;
import org.jboss.resteasy.spi.ResteasyDeployment;

public class ListenerBootstrapOur extends ListenerBootstrap {
	public ListenerBootstrapOur(ServletContext servletContext) {
		super(servletContext);
	}

	public ResteasyDeployment createDeployment() {
		ResteasyDeployment  rd= super.createDeployment();
		rd.setProviderFactory(new ResteasyProviderFactoryOur());
		return rd;
	}

//	public URL[] getScanningUrls() {
//		URL[] urls = findWebInfLibClasspaths(servletContext);
//		URL url = findWebInfClassesPath(servletContext);
//		if (url == null)
//			return urls;
//		URL[] all = new URL[urls.length + 1];
//		int i = 0;
//		for (i = 0; i < urls.length; i++) {
//			all[i] = urls[i];
//		}
//		all[i] = url;
//		return all;
//	}
//
//	public static URL findWebInfClassesPath(ServletContext servletContext) {
//		//		String path = servletContext.getRealPath("Y:/workspace/resteasy-demo/target/classes");
//		//		if (path == null)
//		//			return null;
//		File fp = new File("Y:/workspace/resteasy-demo/target/classes");
//		if (fp.exists() == false)
//			return null;
//		try {
//			return fp.toURL();
//		} catch (MalformedURLException e) {
//			throw new RuntimeException(e);
//		}
//	}

//	public static URL[] findWebInfLibClasspaths(ServletContext servletContext) {
//		LinkedList<File> list = (LinkedList) FileUtils.listFiles(new File("Y:/servers/apache-tomcat-6.0.32/temp/lib-rest"), null, false);
//		List<URL> temp = new ArrayList<URL>();
//		for (int i = 0; i < list.size(); i++) {
//			try {
//				temp.add(list.get(i).toURL());
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
//		}
//		return temp.toArray(new URL[list.size()]);
//	}
}
