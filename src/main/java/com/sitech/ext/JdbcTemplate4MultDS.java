package com.sitech.ext;

import java.util.List;
import java.util.Map;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 该类用于多库查询
 * @author wangtlc 
 * @date 2016-1-21 下午3:42:51
 *
 * 修改日期    修改人    修改目的
 *
 */
@Log4j
public class JdbcTemplate4MultDS extends JdbcTemplate {
	@Setter
	private JdbcTemplate jdbcTemplate_ecora2;

	public <T> List<T> queryForList4MultDS(String sql, Class<T> elementType) throws DataAccessException {
		List<T> list = super.queryForList(sql, elementType);
		list.addAll(this.jdbcTemplate_ecora2.queryForList(sql, elementType));
		log.info("两个LIST合并完成，合并后大小为："+list.size());
		return list;
	}

	public List<Map<String, Object>> queryForList4MultDS(String sql) throws DataAccessException {
		List<Map<String, Object>> list = super.queryForList(sql);
		list.addAll(this.jdbcTemplate_ecora2.queryForList(sql));
		log.info("两个LIST合并完成，合并后大小为："+list.size());
		//TODO 考虑排序问题
		return list;
	}

	public <T> List<T> queryForList4MultDS(String sql, Object[] args, int[] argTypes, Class<T> elementType) throws DataAccessException {
		List<T> list = super.queryForList(sql, args, argTypes, elementType);
		list.addAll(this.jdbcTemplate_ecora2.queryForList(sql, args, argTypes, elementType));
		log.info("两个LIST合并完成，合并后大小为："+list.size());
		return list;
	}

	public <T> List<T> queryForList4MultDS(String sql, Object[] args, Class<T> elementType) throws DataAccessException {
		List<T> list = super.queryForList(sql, args, elementType);
		list.addAll(this.jdbcTemplate_ecora2.queryForList(sql, args, elementType));
		log.info("两个LIST合并完成，合并后大小为："+list.size());
		return list;
	}

	public <T> List<T> queryForList4MultDS(String sql, Class<T> elementType, Object... args) throws DataAccessException {
		List<T> list = super.queryForList(sql, elementType, args);
		list.addAll(this.jdbcTemplate_ecora2.queryForList(sql, elementType, args));
		log.info("两个LIST合并完成，合并后大小为："+list.size());
		return list;
	}

	public List<Map<String, Object>> queryForList4MultDS(String sql, Object[] args, int[] argTypes) throws DataAccessException {
		List<Map<String, Object>> list = super.queryForList(sql, args, argTypes);
		list.addAll(this.jdbcTemplate_ecora2.queryForList(sql, args, argTypes));
		log.info("两个LIST合并完成，合并后大小为："+list.size());
		return list;
	}

	public List<Map<String, Object>> queryForList4MultDS(String sql, Object... args) throws DataAccessException {
		List<Map<String, Object>> list = super.queryForList(sql, args);
		list.addAll(this.jdbcTemplate_ecora2.queryForList(sql, args));
		log.info("两个LIST合并完成，合并后大小为："+list.size());
		return list;
	}

}
