package com.yaolizh.oa.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.project.domain.ProjectDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 项目信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:54
 */
public interface ProjectService extends BaseService<ProjectDO>{
	
//	ProjectDO get(String id);
	
//	List<ProjectDO> list(Map<String, Object> map);
	
	
//	int save(ProjectDO project);
	
//	int update(ProjectDO project);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<ProjectDO> list, UserDO loginInfo, HttpServletRequest request);
}
