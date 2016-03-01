package com.sitech.ext;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 充值中心数据源路由类
 * 该类应用侧不会直接用到，用于SPRING配置文件中替换默认DATASOURCE
 * @author wangtlc 
 * @date 2016-1-21 上午10:01:23
 */
public class DataSource4RouterEcora extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceHolder.getDS4Ecora();
	}
}
