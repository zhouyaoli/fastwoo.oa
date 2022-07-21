package com.yaolizh.oa.fixedresourceusehistory.dao;

import com.yaolizh.oa.fixedresourceusehistory.domain.FixedResourceUseHistoryDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 固定资产借用归还记录
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:58
 */
@Mapper
public interface FixedResourceUseHistoryDao extends MyMapper<FixedResourceUseHistoryDO>{

	//FixedResourceUseHistoryDO get(String id);
	
	List<FixedResourceUseHistoryDO> list(Map<String,Object> map);
	
	
	//int save(FixedResourceUseHistoryDO fixedResourceUseHistory);
	
	//int update(FixedResourceUseHistoryDO fixedResourceUseHistory);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
