package com.sitech.ext;

import lombok.extern.log4j.Log4j;

import org.springframework.util.Assert;

/**
 * 多数据源路由容器
 * 
 * @author wangtlc
 * @date 2016-1-21 上午9:57:15
 */
@Log4j
public class DataSourceHolder {

	// 充值A/B库
	private static final ThreadLocal<String> contextHolder4Ecora = new ThreadLocal<String>();
	
	// 菜单权限：用户库/A库备 注:该值只能在实时请求时捕获处理,不能在启动(后期无法检测)或过滤器(性能开销)中处理.
	private static String contextHolder4Sys;

	/**
	 * 应用侧不会直接用到，供DataSource4RouterEcora使用
	 * @author wangtlc 
	 * @date 2016-1-21 上午10:03:15
	 */
	static String getDS4Ecora() {
		return contextHolder4Ecora.get();
	}
	
	/**
	 * 供内部使用，在下单或订单流转时进行切库
	 * @author wangtlc 
	 * @date 2016-1-21 上午10:03:15
	 */
	private static void setDS4Ecora(String ds4Ecora) {
		Assert.notNull(ds4Ecora, "ds4Ecora cannot be null");
		contextHolder4Ecora.set(ds4Ecora);
	}
	
	public static void convertEcora(){
		setDS4Ecora("dataSource_ecora");
		log.info("已经切换至A库");
	}
	public static void convertEcora2(){
		setDS4Ecora("dataSource_ecora2");
		log.info("已经切换至B库");
	}
	
	/**
	 * 应用侧不会直接用到，供DataSource4RouterEcora使用
	 * @author wangtlc 
	 * @date 2016-1-21 上午10:03:15
	 */
	static String getDS4Sys() {
		return contextHolder4Sys;
	}
	
	/**
	 * 应用侧不会直接用到，供DataSource4RouterSys使用，在用户库挂了后进行切库
	 * 对外调用权限：包级别
	 * @author wangtlc 
	 * @date 2016-1-21 上午10:03:15
	 */
	static void setDS4Sys(String ds4Sys) {
		Assert.notNull(ds4Sys, "ds4Sys cannot be null");
		contextHolder4Sys=ds4Sys;
	}
}
