package com.yaolizh.oa.carfaulthistory.dao;

import com.yaolizh.oa.carfaulthistory.domain.CarFaultHistoryDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆事故记录信息
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:06
 */
@Mapper
public interface CarFaultHistoryDao extends MyMapper<CarFaultHistoryDO>{

	//CarFaultHistoryDO get(String id);
	
	List<CarFaultHistoryDO> list(Map<String,Object> map);
	
	
	//int save(CarFaultHistoryDO carFaultHistory);
	
	//int update(CarFaultHistoryDO carFaultHistory);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
