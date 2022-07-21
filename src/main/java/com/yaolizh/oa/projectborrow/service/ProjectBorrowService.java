package com.yaolizh.oa.projectborrow.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.projectborrow.domain.ProjectBorrowDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 借款信息表
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:54
 */
public interface ProjectBorrowService extends BaseService<ProjectBorrowDO>{
	
//	ProjectBorrowDO get(String id);
	
//	List<ProjectBorrowDO> list(Map<String, Object> map);
	
	
//	int save(ProjectBorrowDO projectBorrow);
	
//	int update(ProjectBorrowDO projectBorrow);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<ProjectBorrowDO> list, UserDO loginInfo, HttpServletRequest request);
}
