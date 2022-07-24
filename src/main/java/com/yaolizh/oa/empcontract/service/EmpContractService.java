package com.yaolizh.oa.empcontract.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.empcontract.domain.EmpContractDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 职工合同信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:04
 */
public interface EmpContractService extends BaseService<EmpContractDO>{
	
//	EmpContractDO get(String id);
	
//	List<EmpContractDO> list(Map<String, Object> map);
	
	
//	int save(EmpContractDO empContract);
	
//	int update(EmpContractDO empContract);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<EmpContractDO> list, UserDO loginInfo, HttpServletRequest request);
}
