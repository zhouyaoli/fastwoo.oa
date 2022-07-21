package com.yaolizh.oa.carillegalhistory.dao;

import com.yaolizh.oa.carillegalhistory.domain.CarIllegalHistoryDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆违章记录信息
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:59
 */
@Mapper
public interface CarIllegalHistoryDao extends MyMapper<CarIllegalHistoryDO>{

	//CarIllegalHistoryDO get(String id);
	
	List<CarIllegalHistoryDO> list(Map<String,Object> map);
	
	
	//int save(CarIllegalHistoryDO carIllegalHistory);
	
	//int update(CarIllegalHistoryDO carIllegalHistory);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
