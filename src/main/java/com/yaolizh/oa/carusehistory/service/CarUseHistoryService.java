package com.yaolizh.oa.carusehistory.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.carusehistory.domain.CarUseHistoryDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 车辆使用记录信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:05
 */
public interface CarUseHistoryService extends BaseService<CarUseHistoryDO>{
	
//	CarUseHistoryDO get(String id);
	
//	List<CarUseHistoryDO> list(Map<String, Object> map);
	
	
//	int save(CarUseHistoryDO carUseHistory);
	
//	int update(CarUseHistoryDO carUseHistory);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<CarUseHistoryDO> list, UserDO loginInfo, HttpServletRequest request);
}
