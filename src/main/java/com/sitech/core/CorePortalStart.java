/**
 * 
 *
 */
package com.sitech.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 应用启动初始化核心类，初始化核心包及业务侧需要提前加载的内容
 * 
 * 该类必须在web.xml中配置为监听listener 配置如下：
 * 
 * <!-- 应用启动加载项 --> <listener>
 * <listener-class>com.sitech.core.architecture.CorePortalStart</listener-class>
 * </listener>
 * 
 * 另外，业务侧必须实现IConstantUtil接口，并将实现类配置到web.xml中
 * 
 * <!-- 常量工具实现类 --> <context-param>
 * <param-name>constantUtilClassName</param-name>
 * <param-value>com.sitech.echn.core.ConstantUtil</param-value> </context-param>
 * 
 * @Package: com.sitech.core.architecture
 * @ClassName: CorePortalStart
 * @author keliande keld@si-tech.com.cn
 * @date 2012-7-5 上午10:44:59
 * @Copyright © SI-TECH 2012. All rights reserved
 * @version: V1.0
 * 
 *           修改日期 修改人 修改目的
 * 
 */
public class CorePortalStart implements ServletContextListener {

	static Logger logger = Logger.getLogger(CorePortalStart.class);

	String constantUtilClassName = null;
	/**
	 * 解决掌上客户端与wap营业厅引入核心包出现的问题： 掌上客户端与wap营业厅不基于现有的网厅权限模型。但需要初始化sping。
	 * 但现有的核心包spring初始化和初始化权限均放置在CorePortalStart。
	 * 故在WEB.XML中增加参数配置以决定在CorePortalStart中是否需要初始化权限信息
	 */
	String isNotNeedIniPrivInfo = null;

	String portalInitClassName = null;

	ServletContext servletContext = null;

	/**
	 * 
	 * 信息初始化
	 * 
	 * 注意初始化的顺序，先初始化核心包内容，再初始化业务侧内容
	 * 
	 * @author keliande keld@si-tech.com.cn
	 * @date 2012-7-5 上午11:24:11
	 * @version: V1.0
	 * 
	 * @return true-初始化成功，否则，初始化失败
	 */
	private boolean init() {

		// Spring初始化
		SpringInfo.init(servletContext);

		logger.info("################Spring初始化完成");
		return true;
	}

	/**
	 * TODO
	 * 
	 * @author keliande keld@si-tech.com.cn
	 * @date 2012-7-5 上午10:45:25
	 * @version: V1.0
	 * 
	 * @param arg0
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("################应用正在退出......");

		logger.info("################应用退出结束");
	}

	/**
	 * 初始化
	 * 
	 * @author keliande keld@si-tech.com.cn
	 * @date 2012-7-5 上午10:45:25
	 * @version: V1.0
	 * 
	 * @param arg0
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context = arg0.getServletContext();

		servletContext = arg0.getServletContext();

		logger.info("################核心包信息初始化开始.....");

		if (!init()) {
			logger.info("####portalstart初始化失败，应用退出");
			System.exit(-1);
		}

		logger.info("################核心包信息初始化完成.....");
	}

}
