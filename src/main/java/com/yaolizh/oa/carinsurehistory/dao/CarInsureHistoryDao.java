package com.yaolizh.oa.carinsurehistory.dao;

import com.yaolizh.oa.carinsurehistory.domain.CarInsureHistoryDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆保险记录信息
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:06
 */
@Mapper
public interface CarInsureHistoryDao extends MyMapper<CarInsureHistoryDO>{

	//CarInsureHistoryDO get(String id);
	
	List<CarInsureHistoryDO> list(Map<String,Object> map);
	
	
	//int save(CarInsureHistoryDO carInsureHistory);
	
	//int update(CarInsureHistoryDO carInsureHistory);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
