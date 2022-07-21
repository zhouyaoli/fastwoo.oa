package com.yaolizh.oa.projectrefund.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.projectrefund.domain.ProjectRefundDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 报销信息表
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:55
 */
public interface ProjectRefundService extends BaseService<ProjectRefundDO>{
	
//	ProjectRefundDO get(String id);
	
//	List<ProjectRefundDO> list(Map<String, Object> map);
	
	
//	int save(ProjectRefundDO projectRefund);
	
//	int update(ProjectRefundDO projectRefund);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<ProjectRefundDO> list, UserDO loginInfo, HttpServletRequest request);
}
