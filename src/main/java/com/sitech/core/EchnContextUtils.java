package com.sitech.core;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EchnContextUtils {
	private static Set<Class> allowType = new HashSet<Class>();

	enum KEY {
		request, echnContext, session, response, randomId, sessionId
	};

	private static ThreadLocal<Map> map = new ThreadLocal<Map>();
	static {
		allowType.add(short.class);
		allowType.add(Short.class);
		allowType.add(int.class);
		allowType.add(Integer.class);
		allowType.add(Long.class);
		allowType.add(long.class);
		allowType.add(Float.class);
		allowType.add(float.class);
		allowType.add(Double.class);
		allowType.add(double.class);
		allowType.add(char.class);
		allowType.add(Character.class);
		allowType.add(String.class);
		allowType.add(java.sql.Date.class);
		allowType.add(java.util.Date.class);
	}

	public static String getRoot() {
		HttpServletRequest request = getRequest();
		if (request != null) {
			String result = request.getRealPath("/");
			if (result.endsWith("/"))
				return result;
			else
				return result + File.separator;
		}
		return null;
	}

	public static String getContextPath() {
		HttpServletRequest request = getRequest();
		if (request != null) {
			return request.getContextPath();
		}
		return null;
	}

	public static HttpSession getSession() {
		HttpServletRequest request = getRequest();
		if (request != null) {
			return request.getSession();
		}
		return null;
	}

	public static HttpServletRequest getRequest() {
		Map c = map.get();
		if (c != null)
			return (HttpServletRequest) c.get(KEY.request);
		return null;
	}

	public static void setRequest(HttpServletRequest request) {
		Map c = map.get();
		if (c == null)
			c = new HashMap();
		c.put(KEY.request, request);
		map.set(c);
	}
	
	public static HttpServletResponse getResponse() {
		Map c = map.get();
		if (c != null)
			return (HttpServletResponse) c.get(KEY.response);
		return null;
	}

	public static void setResponse(HttpServletResponse request) {
		Map c = map.get();
		if (c == null)
			c = new HashMap();
		c.put(KEY.response, request);
		map.set(c);
	}


	
	public static String getParameter(String name) throws Exception{
		if (EchnContextUtils.getRequest()==null) {
			throw new Exception("当前环境是没有REQUEST对象，请核实！");
		}
		return EchnContextUtils.getRequest().getParameter(name);
	}
	
//add by wangtlc 方便日志输出追踪/////////////////////////////////////////////////////////////////////////
	public static void setRandomId4CoreInitFilter(String randomId) {
		Map c = map.get();
		if (c == null)
			c = new HashMap();
		c.put(KEY.randomId, randomId);
		map.set(c);
	}
	
	public static String getRandomId4CoreInitFilter() {
		Map c = map.get();
		if (c != null)
			return (String) c.get(KEY.randomId);
		return null;
	}

	public static void setSessionId4CoreInitFilter(String sid) {
		Map c = map.get();
		if (c == null)
			c = new HashMap();
		c.put(KEY.sessionId, sid);
		map.set(c);
	}
	
	public static String getSessionId4CoreInitFilter() {
		Map c = map.get();
		if (c != null)
			return (String) c.get(KEY.sessionId);
		return null;
	}
}
