package com.sitech.core.architecture.bean;

import java.util.List;

/**
 * 
 * 返回结果实体类
 * 
 * @Package: com.sitech.core.architecture.bean
 * @ClassName: RetInfo
 * @author keliande keld@si-tech.com.cn
 * @date 2012-6-7 下午3:44:44
 * @Copyright © SI-TECH 2012. All rights reserved
 * @version: V1.0
 * 
 *           修改日期 修改人 修改目的
 * 
 */
public final class RetInfo implements java.io.Serializable {
	/**
	 * <li>0-成功</li> <li>1-失败</li>
	 */
	private String result;

	/**
	 * 失败时返回码，根据具体业务自定义
	 */
	private String retCode;

	/**
	 * 失败原因
	 */
	private String retMsg;
	
	/**
	 * 返回信息 
	 * added by liuxw@20130822 for cmcc-zb-融合分支
	 */
	private String detail_msg;
	/**
	 * 返回信息
	 * added by liuxw@20130822 for cmcc-zb-融合分支
	 */
	private String user_msg;
	/**
	 * 返回信息
	 * added by liuxw@20130822 for cmcc-zb-融合分支
	 */
	private String prompt_msg;
	/**
	 * 返回对象
	 */
	private Object object;

	/**
	 * 返回列表,建议使用object保存list
	 */
	@Deprecated
	private List list;

	/**
     * 
     */
	private String uuid;

	/**
	 * 服务调用时间
	 */
	private long optTime;
	
	/**
	 * 结果对应时间戳,此时间是由省BOSS返回给平台，代表查询返回内容的时间点。YYYYMMDDHH24MISS 
	 * added by liuxw@20130822 for cmcc-zb
	**/
	private String sOperTime;
	
	public String getDetail_msg() {
		return detail_msg;
	}

	public void setDetail_msg(String detail_msg) {
		this.detail_msg = detail_msg;
	}

	public String getUser_msg() {
		return user_msg;
	}

	public void setUser_msg(String user_msg) {
		this.user_msg = user_msg;
	}

	public String getPrompt_msg() {
		return prompt_msg;
	}

	public void setPrompt_msg(String prompt_msg) {
		this.prompt_msg = prompt_msg;
	}

	public String getsOperTime() {
		return sOperTime;
	}

	public void setsOperTime(String sOperTime) {
		this.sOperTime = sOperTime;
	}

	public long getOptTime() {
		return optTime;
	}

	public void setOptTime(long optTime) {
		this.optTime = optTime;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * <li>0-成功</li> <li>1-失败</li>
	 */
	public String getResult() {
		return result;
	}
	
	/**
	 * yangming发的邮件，提出优化建议
	 * 2013年5月21日 (周二) 15:13 建议RetInfo 中增加一个内聚的 getBooleanResult 方法， 可避免在前端做 equals 比较。
	 * @return
	 */
	public boolean getResult4Boolean() {
		return true;
	}
	/**
	 * <li>0-成功</li> <li>1-失败</li>
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * 失败时返回码，根据具体业务自定义
	 */
	public String getRetCode() {
		return retCode;
	}

	/**
	 * 设置返回码，根据具体业务自定义
	 */
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	/**
	 * 失败原因
	 */
	public String getRetMsg() {
		return retMsg;
	}

	/**
	 * 设置失败原因
	 */
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	/** 获得对象 */
	public Object getObject() {
		return object;
	}

	/** 设置对象 */
	public void setObject(Object object) {
		this.object = object;
	}

	/** 获得列表 */
	@Deprecated
	public List getList() {
		return list;
	}

	/** 设置列表 */
	@Deprecated
	public void setList(List list) {
		this.list = list;
	}

	public String toString() {

		StringBuffer paramBuffer = new StringBuffer();
		paramBuffer.append("result=").append(result).append("\n");
		paramBuffer.append("retCode=").append(retCode).append("\n");
		paramBuffer.append("retMsg=").append(retMsg).append("\n");
		// paramBuffer.append("object=").append(object.toString()).append("\n");
		paramBuffer.append("object=").append(object == null ? "" : object.toString()).append("\n");
		// paramBuffer.append("list=").append(list.toString()).append("\n");
		return paramBuffer.toString();
	}
}
