package com.yaolizh.oa.empyearcheck.dao;

import com.yaolizh.oa.empyearcheck.domain.EmpYearCheckDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 职工年度考核报告
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:56
 */
@Mapper
public interface EmpYearCheckDao extends MyMapper<EmpYearCheckDO>{

	//EmpYearCheckDO get(String id);
	
	List<EmpYearCheckDO> list(Map<String,Object> map);
	
	
	//int save(EmpYearCheckDO empYearCheck);
	
	//int update(EmpYearCheckDO empYearCheck);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
