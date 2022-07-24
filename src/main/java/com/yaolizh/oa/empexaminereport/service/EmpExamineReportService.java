package com.yaolizh.oa.empexaminereport.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.empexaminereport.domain.EmpExamineReportDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 职工体检报告
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:04
 */
public interface EmpExamineReportService extends BaseService<EmpExamineReportDO>{
	
//	EmpExamineReportDO get(String id);
	
//	List<EmpExamineReportDO> list(Map<String, Object> map);
	
	
//	int save(EmpExamineReportDO empExamineReport);
	
//	int update(EmpExamineReportDO empExamineReport);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<EmpExamineReportDO> list, UserDO loginInfo, HttpServletRequest request);
}
