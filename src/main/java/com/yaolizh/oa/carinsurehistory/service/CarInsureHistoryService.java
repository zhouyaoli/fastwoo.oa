package com.yaolizh.oa.carinsurehistory.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.carinsurehistory.domain.CarInsureHistoryDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 车辆保险记录信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:06
 */
public interface CarInsureHistoryService extends BaseService<CarInsureHistoryDO>{
	
//	CarInsureHistoryDO get(String id);
	
//	List<CarInsureHistoryDO> list(Map<String, Object> map);
	
	
//	int save(CarInsureHistoryDO carInsureHistory);
	
//	int update(CarInsureHistoryDO carInsureHistory);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<CarInsureHistoryDO> list, UserDO loginInfo, HttpServletRequest request);
}
