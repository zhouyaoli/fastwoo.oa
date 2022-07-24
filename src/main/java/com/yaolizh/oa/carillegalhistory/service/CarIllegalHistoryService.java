package com.yaolizh.oa.carillegalhistory.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.carillegalhistory.domain.CarIllegalHistoryDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 车辆违章记录信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:06
 */
public interface CarIllegalHistoryService extends BaseService<CarIllegalHistoryDO>{
	
//	CarIllegalHistoryDO get(String id);
	
//	List<CarIllegalHistoryDO> list(Map<String, Object> map);
	
	
//	int save(CarIllegalHistoryDO carIllegalHistory);
	
//	int update(CarIllegalHistoryDO carIllegalHistory);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<CarIllegalHistoryDO> list, UserDO loginInfo, HttpServletRequest request);
}
