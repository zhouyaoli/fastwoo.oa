package com.yaolizh.oa.projectinoutmoney.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.projectinoutmoney.domain.ProjectInOutMoneyDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 收支明细
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:00
 */
public interface ProjectInOutMoneyService extends BaseService<ProjectInOutMoneyDO>{
	
//	ProjectInOutMoneyDO get(String id);
	
//	List<ProjectInOutMoneyDO> list(Map<String, Object> map);
	
	
//	int save(ProjectInOutMoneyDO projectInOutMoney);
	
//	int update(ProjectInOutMoneyDO projectInOutMoney);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<ProjectInOutMoneyDO> list, UserDO loginInfo, HttpServletRequest request);
}
