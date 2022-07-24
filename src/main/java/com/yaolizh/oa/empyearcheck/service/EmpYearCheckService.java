package com.yaolizh.oa.empyearcheck.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.empyearcheck.domain.EmpYearCheckDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 职工年度考核报告
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:04
 */
public interface EmpYearCheckService extends BaseService<EmpYearCheckDO>{
	
//	EmpYearCheckDO get(String id);
	
//	List<EmpYearCheckDO> list(Map<String, Object> map);
	
	
//	int save(EmpYearCheckDO empYearCheck);
	
//	int update(EmpYearCheckDO empYearCheck);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<EmpYearCheckDO> list, UserDO loginInfo, HttpServletRequest request);
}
