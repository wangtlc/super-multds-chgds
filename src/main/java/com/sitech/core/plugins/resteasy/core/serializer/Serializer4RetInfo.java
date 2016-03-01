package com.sitech.core.plugins.resteasy.core.serializer;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;

import com.sitech.core.architecture.bean.RetInfo;
/**
 * RetInfo处理器
 * @author wangtl
 *
 */
public class Serializer4RetInfo implements ISerializer{
	private static Logger logger = Logger.getLogger(Serializer4RetInfo.class);
	@Override
	public Set<String> preDeal(Object bean, JsonGenerator jgen, SerializerProvider provider) throws Exception {
		Set<String> ignoreNameSet=null;
		if (bean instanceof RetInfo) {
			ignoreNameSet=new HashSet<String>();
			ignoreNameSet.add("optTime");
			ignoreNameSet.add("result");
			ignoreNameSet.add("result4Boolean");
			//进行回值处理
			RetInfo temp=(RetInfo)bean;
			jgen.writeObjectField("data", temp.getObject());
			//将object成员去掉
//			temp.setObject(null);
			//将list置空
			temp.setList(null);
		}
		return ignoreNameSet;
	}

}
