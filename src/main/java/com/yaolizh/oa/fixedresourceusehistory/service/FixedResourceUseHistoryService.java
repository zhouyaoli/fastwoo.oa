package com.yaolizh.oa.fixedresourceusehistory.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.fixedresourceusehistory.domain.FixedResourceUseHistoryDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 固定资产借用归还记录
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:58
 */
public interface FixedResourceUseHistoryService extends BaseService<FixedResourceUseHistoryDO>{
	
//	FixedResourceUseHistoryDO get(String id);
	
//	List<FixedResourceUseHistoryDO> list(Map<String, Object> map);
	
	
//	int save(FixedResourceUseHistoryDO fixedResourceUseHistory);
	
//	int update(FixedResourceUseHistoryDO fixedResourceUseHistory);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<FixedResourceUseHistoryDO> list, UserDO loginInfo, HttpServletRequest request);
}
