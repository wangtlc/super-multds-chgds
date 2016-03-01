package com.sitech.core.plugins.resteasy.core.serializer;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sitech.core.architecture.bean.RetInfo;

/**
 * JAVA=>JSON处理器
 * @author wangtl
 *
 */
public class SerializerFactory {
	private static Logger logger = Logger.getLogger(SerializerFactory.class);
	//处理器存储
	private static Map<String, ISerializer> serializerInstances = new HashMap<String, ISerializer>();
	
	static {
		registerSerializer(RetInfo.class.getName(), "com.sitech.core.plugins.resteasy.core.serializer.Serializer4RetInfo");
//		registerSerializer(LogInInfo.class.getName(), "com.sitech.core.plugins.resteasy.serializer.Serializer4LoginInfo");
//		registerSerializer(AuthInfo.class.getName(), "com.sitech.core.plugins.resteasy.serializer.Serializer4AuthInfo");
	}
	
	/**
	 * 配置化注册
	 * @param voType
	 * @param clsType
	 */
	public static void registerSerializer(String voType, String clsType) {
		try {
			serializerInstances.put(voType, (ISerializer)Class.forName(clsType).newInstance());
			logger.info("成功注册："+clsType);
		}catch (Exception e) {
			logger.error("初始化处理器失败!", e);
		}
	}
	
	/**
	 * 实例注册
	 * @param voType
	 * @param object
	 */
	public static void registerSerializer(String voType, Object object) {
		try {
			serializerInstances.put(voType, (ISerializer)object);
		}catch (Exception e) {
			logger.error("初始化处理器失败!", e);
		}
	}
	
	/**
	 * 获取处理器
	 * @param bean
	 * @return
	 */
	public static ISerializer getSerializer(Object bean) {
		return serializerInstances.get(bean.getClass().getName());
	}

}
