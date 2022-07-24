package com.yaolizh.oa.empresthistory.dao;

import com.yaolizh.oa.empresthistory.domain.EmpRestHistoryDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 职工请假记录
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:04
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
