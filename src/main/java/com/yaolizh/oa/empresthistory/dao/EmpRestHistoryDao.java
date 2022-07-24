package com.yaolizh.oa.empresthistory.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yaolizh.fastwoo.mybatis.MyMapper;
import com.yaolizh.oa.empresthistory.domain.EmpRestHistoryDO;

/**
 * 职工请假记录
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:55
 */
@Mapper
public interface EmpRestHistoryDao extends MyMapper<EmpRestHistoryDO>{

	//EmpRestHistoryDO get(String id);
	
	List<EmpRestHistoryDO> list(Map<String,Object> map);
	
	
	//int save(EmpRestHistoryDO empRestHistory);
	
	//int update(EmpRestHistoryDO empRestHistory);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
