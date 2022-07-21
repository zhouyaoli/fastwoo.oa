package com.yaolizh.oa.carfaulthistory.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.carfaulthistory.domain.CarFaultHistoryDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 车辆事故记录信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:16:00
 */
public interface CarFaultHistoryService extends BaseService<CarFaultHistoryDO>{
	
//	CarFaultHistoryDO get(String id);
	
//	List<CarFaultHistoryDO> list(Map<String, Object> map);
	
	
//	int save(CarFaultHistoryDO carFaultHistory);
	
//	int update(CarFaultHistoryDO carFaultHistory);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<CarFaultHistoryDO> list, UserDO loginInfo, HttpServletRequest request);
}
