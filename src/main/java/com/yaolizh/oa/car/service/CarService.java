package com.yaolizh.oa.car.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.car.domain.CarDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 车辆信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:28:59
 */
public interface CarService extends BaseService<CarDO>{
	
//	CarDO get(String id);
	
//	List<CarDO> list(Map<String, Object> map);
	
	
//	int save(CarDO car);
	
//	int update(CarDO car);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<CarDO> list, UserDO loginInfo, HttpServletRequest request);
}
