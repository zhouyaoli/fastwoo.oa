package com.yaolizh.oa.fixedresourcerepairhistory.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.fixedresourcerepairhistory.domain.FixedResourceRepairHistoryDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 固定资产维修记录
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:03
 */
public interface FixedResourceRepairHistoryService extends BaseService<FixedResourceRepairHistoryDO>{
	
//	FixedResourceRepairHistoryDO get(String id);
	
//	List<FixedResourceRepairHistoryDO> list(Map<String, Object> map);
	
	
//	int save(FixedResourceRepairHistoryDO fixedResourceRepairHistory);
	
//	int update(FixedResourceRepairHistoryDO fixedResourceRepairHistory);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<FixedResourceRepairHistoryDO> list, UserDO loginInfo, HttpServletRequest request);
}
