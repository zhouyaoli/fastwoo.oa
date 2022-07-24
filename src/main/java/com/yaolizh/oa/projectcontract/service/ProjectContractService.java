package com.yaolizh.oa.projectcontract.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.projectcontract.domain.ProjectContractDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 合同信息（附件：中标通知书）
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 17:13:51
 */
public interface ProjectContractService extends BaseService<ProjectContractDO>{
	
//	ProjectContractDO get(String id);
	
//	List<ProjectContractDO> list(Map<String, Object> map);
	
	
//	int save(ProjectContractDO projectContract);
	
//	int update(ProjectContractDO projectContract);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<ProjectContractDO> list, UserDO loginInfo, HttpServletRequest request);
}
