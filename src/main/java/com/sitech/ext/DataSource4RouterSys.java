package com.sitech.ext;

import java.sql.Connection;
import java.sql.SQLException;

import lombok.extern.log4j.Log4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 系统菜单数据源路由类
 * 该类应用侧不会直接用到，用于SPRING配置文件中替换默认DATASOURCE
 * @author wangtlc 
 * @date 2016-1-21 上午10:01:23
 */
@Log4j
public class DataSource4RouterSys extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceHolder.getDS4Sys();
	}
	
	
	public Connection getConnection() throws SQLException {
		try {
			return determineTargetDataSource().getConnection();
		} catch (SQLException e) {
			convertDS4sys();
			throw e;
		}
	}

	public Connection getConnection(String username, String password) throws SQLException {
		try {
			return determineTargetDataSource().getConnection(username, password);
		} catch (SQLException e) {
			convertDS4sys();
			throw e;
		}
	}
	
	private void convertDS4sys() {
		if (StringUtils.isBlank(DataSourceHolder.getDS4Sys())) {
			DataSourceHolder.setDS4Sys("dataSource_ecora");//默认为用户库，由SPRING配置决定，不再硬编码
			log.warn("【用户库获取连接失败，切换为充值库,在下次请求生效】");
		}else if(StringUtils.equals(DataSourceHolder.getDS4Sys(), "dataSource_usrdb")){
			DataSourceHolder.setDS4Sys("dataSource_ecora");
			log.warn("【用户库获取连接失败，切换为充值库,在下次请求生效】");
		}else if (StringUtils.equals(DataSourceHolder.getDS4Sys(), "dataSource_ecora")) {
			DataSourceHolder.setDS4Sys("dataSource_usrdb");
			log.warn("【充值库获取连接失败，切换为用户库,在下次请求生效】");
		}
	}	
}
