package com.yaolizh.oa.empresthistory.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.empresthistory.domain.EmpRestHistoryDO;
/**
 * 职工请假记录
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:55
 */
public interface EmpRestHistoryService extends BaseService<EmpRestHistoryDO>{
	
//	EmpRestHistoryDO get(String id);
	
//	List<EmpRestHistoryDO> list(Map<String, Object> map);
	
	
//	int save(EmpRestHistoryDO empRestHistory);
	
//	int update(EmpRestHistoryDO empRestHistory);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<EmpRestHistoryDO> list, UserDO loginInfo, HttpServletRequest request);
}
