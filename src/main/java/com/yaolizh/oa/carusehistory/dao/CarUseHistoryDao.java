package com.yaolizh.oa.carusehistory.dao;

import com.yaolizh.oa.carusehistory.domain.CarUseHistoryDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆使用记录信息
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:05
 */
@Mapper
public interface CarUseHistoryDao extends MyMapper<CarUseHistoryDO>{

	//CarUseHistoryDO get(String id);
	
	List<CarUseHistoryDO> list(Map<String,Object> map);
	
	
	//int save(CarUseHistoryDO carUseHistory);
	
	//int update(CarUseHistoryDO carUseHistory);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
