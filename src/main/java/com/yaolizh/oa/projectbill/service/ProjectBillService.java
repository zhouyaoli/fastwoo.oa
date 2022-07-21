package com.yaolizh.oa.projectbill.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.projectbill.domain.ProjectBillDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 开票情况信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:56
 */
public interface ProjectBillService extends BaseService<ProjectBillDO>{
	
//	ProjectBillDO get(String id);
	
//	List<ProjectBillDO> list(Map<String, Object> map);
	
	
//	int save(ProjectBillDO projectBill);
	
//	int update(ProjectBillDO projectBill);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<ProjectBillDO> list, UserDO loginInfo, HttpServletRequest request);
}
