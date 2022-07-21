package com.yaolizh.oa.carrepairhistory.dao;

import com.yaolizh.oa.carrepairhistory.domain.CarRepairHistoryDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆维修维保记录信息
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:59
 */
@Mapper
public interface CarRepairHistoryDao extends MyMapper<CarRepairHistoryDO>{

	//CarRepairHistoryDO get(String id);
	
	List<CarRepairHistoryDO> list(Map<String,Object> map);
	
	
	//int save(CarRepairHistoryDO carRepairHistory);
	
	//int update(CarRepairHistoryDO carRepairHistory);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
