package com.sitech.rest;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import lombok.extern.log4j.Log4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sitech.core.EchnContextUtils;
import com.sitech.core.architecture.bean.RetInfo;
import com.sitech.ext.DataSourceHolder;
import com.sitech.impl.UniImpl;

@Path("/v1/res")
@Log4j
@Service
public class RestTest {

	@Resource
	private UniImpl uniImpl;

	@POST
	@Path("/ordersave")
	@Produces("application/json; charset=utf-8")
	public RetInfo ordersave() {//返回存到哪个库里了
		RetInfo r = new RetInfo();
		uniImpl.createOrder();
//		r.setRetMsg(DataSourceHolder.getDS4Ecora());
		return r;
	}

	@POST
	@Path("/orderget")
	@Produces("application/json; charset=utf-8")
	public RetInfo orderget() throws Exception {//返回值相应数据库的值
		RetInfo r = new RetInfo();
		if (StringUtils.isBlank(EchnContextUtils.getParameter("orderId"))) {
			r.setObject("orderId can not blank");
		}else {
			r.setObject(uniImpl.getOrder(EchnContextUtils.getParameter("orderId")));
		}
		return r;
	}
	
	@POST
	@Path("/multds")
	@Produces("application/json; charset=utf-8")
	public RetInfo multds() throws Exception {//返回值相应数据库的值
		RetInfo r = new RetInfo();
		r.setObject(uniImpl.multds());
		return r;
	}
	
	@POST
	@Path("/dberror")
	@Produces("application/json; charset=utf-8")
	public RetInfo dberror() throws Exception {//返回值相应数据库的值
		RetInfo r = new RetInfo();
		r.setObject(uniImpl.dberror());
		return r;
	}
}
