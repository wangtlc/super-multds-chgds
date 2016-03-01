package com.sitech.core.plugins.resteasy;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.io.SegmentedStringWriter;
import org.codehaus.jackson.map.ObjectMapper;

import com.sitech.core.plugins.resteasy.core.ObjectMapperOur;

/**
 * java=>json，工具类供应用侧调用
 * @author wangtl
 *
 */
public class JsonDeailUtil {
	private static Logger logger = Logger.getLogger(JsonDeailUtil.class);

	/**
	 * java=>json，工具类供应用侧调用
	 * 
	 * @param object
	 * @return
	 */
	public static String object2JsonString(Object object) {
		SegmentedStringWriter sw = null;
		JsonGenerator jGenerator = null;
		try {
			ObjectMapper mapper = new ObjectMapperOur();//个性化定制入口
			sw = new SegmentedStringWriter(mapper.getJsonFactory()._getBufferRecycler());//结果集出口
			jGenerator = mapper.getJsonFactory().createJsonGenerator(sw);
			jGenerator.writeObject(object);
//			mapper.writeValueAsString(object);
			return sw.getAndClear();
		} catch (Exception e) {
			logger.error("JAVA=>JSON转换异常！", e);
		} finally {
			if (sw != null) {
				sw.close();
			}
			if (jGenerator != null) {
				try {
					jGenerator.close();
				} catch (IOException e) {
					logger.error("流关闭失败！请检查！", e);
				}
			}
		}
		return null;
	}
}
