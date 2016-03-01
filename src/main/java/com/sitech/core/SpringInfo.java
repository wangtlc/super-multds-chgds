package com.sitech.core;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;


/**
 * 
 * Spring 容器初始化
 * 
 * @Package: com.sitech.core.architecture
 * @ClassName: SpringInfo
 * @author shibc shibc@si-tech.com.cn
 * @date 2007-10-16 下午12:10:44
 * @Copyright © SI-TECH 2012. All rights reserved
 * @version: V1.0
 * 
 *           修改日期 修改人 修改目的
 * 
 */
public class SpringInfo {
	public static WebApplicationContext SPRING_CONTEXT = null;

	/**
	 * 初始化springcontext
	 * 
	 * @param servletContext
	 */
	public static void init(ServletContext servletContext) {
		SPRING_CONTEXT = (WebApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	}

	/**
	 * 为了兼容crmpd中配置文件的读取 by shibc
	 */
	public static WebApplicationContext getBeanFactory() {
		return SPRING_CONTEXT;
	}

	/************** 自助终端合并 *********************/

	/**
	 * 获取spring bean
	 * 
	 * @param beanName
	 */
	public static Object getBean(String beanName) {
		return SpringInfo.SPRING_CONTEXT.getBean(beanName);
	}
	//modify by wangtlc 20150609 改为泛型
	public static <T> T getBean(Class<T> beanName){
		return ((T)(SpringInfo.SPRING_CONTEXT.getBean(beanName)));
	}
	
	public static <T> List<T>  getBeans(Class<T> classP){
		 List<T> list=new ArrayList<T>();
		 String[] beanNames= SPRING_CONTEXT.getBeanNamesForType(classP);
		 for (int i = 0; i < beanNames.length; i++) {
			 list.add((T)SPRING_CONTEXT.getBean(beanNames[i]));
		 }
		 return list;
	}
}
