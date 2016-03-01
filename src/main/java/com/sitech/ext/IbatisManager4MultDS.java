package com.sitech.ext;

import java.util.List;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

import com.sitech.core.architecture.exception.AppException;
import com.sitech.core.architecture.ibatis.IbatisManager;

/**
 * 该类用于多库查询
 * @author wangtlc 
 * @date 2016-1-21 下午3:42:51
 *
 * 修改日期    修改人    修改目的
 *
 */
@Log4j
public class IbatisManager4MultDS extends IbatisManager {
	@Setter
	private IbatisManager ibatisManager_ecora2;

	public List queryForList4MultDS(String id) throws AppException {
		List list = super.queryForList(id);
		List list2 = this.ibatisManager_ecora2.queryForList(id);
		list.addAll(list2);
		log.info("两个LIST合并完成，合并后大小为："+list.size());
		//TODO 增加list非null处理
		return list;
	}

	public List queryForList4MultDS(String id, Object parameterObject) throws AppException {
		List list = super.queryForList(id, parameterObject);
		List list2 = this.ibatisManager_ecora2.queryForList(id, parameterObject);
		list.addAll(list2);
		log.info("两个LIST合并完成，合并后大小为："+list.size());
		return list;
	}

}
