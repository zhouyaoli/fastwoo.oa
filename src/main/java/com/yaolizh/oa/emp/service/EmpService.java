package com.yaolizh.oa.emp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.emp.domain.EmpDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 职工档案信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:05
 */
public interface EmpService extends BaseService<EmpDO>{
	
//	EmpDO get(String id);
	
//	List<EmpDO> list(Map<String, Object> map);
	
	
//	int save(EmpDO emp);
	
//	int update(EmpDO emp);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<EmpDO> list, UserDO loginInfo, HttpServletRequest request);
}
