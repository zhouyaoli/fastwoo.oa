package com.yaolizh.oa.car.dao;

import com.yaolizh.oa.car.domain.CarDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车辆信息
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:16:00
 */
@Mapper
public interface CarDao extends MyMapper<CarDO>{

	//CarDO get(String id);
	
	List<CarDO> list(Map<String,Object> map);
	
	
	//int save(CarDO car);
	
	//int update(CarDO car);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
