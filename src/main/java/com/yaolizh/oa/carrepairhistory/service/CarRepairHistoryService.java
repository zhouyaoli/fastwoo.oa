package com.yaolizh.oa.carrepairhistory.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.carrepairhistory.domain.CarRepairHistoryDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 车辆维修维保记录信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:05
 */
public interface CarRepairHistoryService extends BaseService<CarRepairHistoryDO>{
	
//	CarRepairHistoryDO get(String id);
	
//	List<CarRepairHistoryDO> list(Map<String, Object> map);
	
	
//	int save(CarRepairHistoryDO carRepairHistory);
	
//	int update(CarRepairHistoryDO carRepairHistory);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<CarRepairHistoryDO> list, UserDO loginInfo, HttpServletRequest request);
}
