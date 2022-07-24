package com.yaolizh.oa.projectrefunditem.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.projectrefunditem.domain.ProjectRefundItemDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 报销子项表
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:01
 */
public interface ProjectRefundItemService extends BaseService<ProjectRefundItemDO>{
	
//	ProjectRefundItemDO get(String id);
	
//	List<ProjectRefundItemDO> list(Map<String, Object> map);
	
	
//	int save(ProjectRefundItemDO projectRefundItem);
	
//	int update(ProjectRefundItemDO projectRefundItem);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<ProjectRefundItemDO> list, UserDO loginInfo, HttpServletRequest request);
}
