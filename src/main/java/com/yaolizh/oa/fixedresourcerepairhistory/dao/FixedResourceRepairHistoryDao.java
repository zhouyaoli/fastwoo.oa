package com.yaolizh.oa.fixedresourcerepairhistory.dao;

import com.yaolizh.oa.fixedresourcerepairhistory.domain.FixedResourceRepairHistoryDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 固定资产维修记录
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:58
 */
@Mapper
public interface FixedResourceRepairHistoryDao extends MyMapper<FixedResourceRepairHistoryDO>{

	//FixedResourceRepairHistoryDO get(String id);
	
	List<FixedResourceRepairHistoryDO> list(Map<String,Object> map);
	
	
	//int save(FixedResourceRepairHistoryDO fixedResourceRepairHistory);
	
	//int update(FixedResourceRepairHistoryDO fixedResourceRepairHistory);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
