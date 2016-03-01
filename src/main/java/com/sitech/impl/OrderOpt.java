package com.sitech.impl;

import java.util.Random;

import lombok.extern.log4j.Log4j;

import com.sitech.ext.DataSourceHolder;

@Log4j
public class OrderOpt {

	//仅做测试，一次A库一次B库
	private static boolean db_a=false;
	
	public static String getNewOrderId() {
		if(db_a){//TODO 尽量减小同一时刻路由进同库中的概率。避免库资源争用。
			db_a=false;
			log.info("切换为A库");
			DataSourceHolder.convertEcora();
			return new Random().nextInt()+"_a";
		}else {
			db_a=true;
			log.info("切换为B库");
			DataSourceHolder.convertEcora2();
			return new Random().nextInt()+"_b";
		}
	}
	public static String getYYYYMM(String orderId){
		if (orderId.endsWith("a")) {//A库
			log.info("切换为A库");
			DataSourceHolder.convertEcora();
		}else {//B库
			log.info("切换为B库");
			DataSourceHolder.convertEcora2();
		}
		return null;
	}

}
