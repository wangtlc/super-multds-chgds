package com.sitech.impl;

import java.util.List;

import lombok.extern.log4j.Log4j;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sitech.core.SpringInfo;
import com.sitech.ext.JdbcTemplate4MultDS;

@Log4j
@Service
public class UniImpl {
	
	public void createOrder() {
		JdbcTemplate jdbcTemplate_router_ecora =(JdbcTemplate)SpringInfo.getBean("jdbcTemplate_router_ecora");
		String orderId=OrderOpt.getNewOrderId();
		log.info("生成的订单号为："+orderId);
		jdbcTemplate_router_ecora.update("insert into user (name) values('"+orderId+"')");
	}

	public String getOrder(String orderId) {
		OrderOpt.getYYYYMM(orderId);
		JdbcTemplate jdbcTemplate_router_ecora =(JdbcTemplate)SpringInfo.getBean("jdbcTemplate_router_ecora");
		jdbcTemplate_router_ecora.update("insert into user (name) values('"+orderId+"_liucheng')");
		return orderId;
		
	}

	public List multds() {
		JdbcTemplate4MultDS jdbcTemplate4MultDS =(JdbcTemplate4MultDS)SpringInfo.getBean("jdbcTemplate");
		return jdbcTemplate4MultDS.queryForList4MultDS("select name from user");
	}

	public Object dberror() {
		JdbcTemplate jdbcTemplate =(JdbcTemplate)SpringInfo.getBean("jdbcTemplate_router_sys");
		return jdbcTemplate.queryForList("select name from user");
	}

}
