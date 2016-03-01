package com.sitech.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sitech.core.EchnContextUtils;

/**
 * Servlet Filter implementation class MyFilter
 */
public class CoreInitFilter implements Filter {
	
	
	public void destroy() {

	}

	/**
	 * 创建echnContext，在操作日志AOP记录时直接取，就不用在ACTION中创建了
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		

		EchnContextUtils.setRequest((HttpServletRequest) request);
		EchnContextUtils.setResponse((HttpServletResponse) response);



		chain.doFilter(request, response);//进行方法调用;
		
	}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
}
