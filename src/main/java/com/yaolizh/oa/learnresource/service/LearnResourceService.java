package com.yaolizh.oa.learnresource.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.learnresource.domain.LearnResourceDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 学习平台资源信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:56
 */
public interface LearnResourceService extends BaseService<LearnResourceDO>{
	
//	LearnResourceDO get(String id);
	
//	List<LearnResourceDO> list(Map<String, Object> map);
	
	
//	int save(LearnResourceDO learnResource);
	
//	int update(LearnResourceDO learnResource);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<LearnResourceDO> list, UserDO loginInfo, HttpServletRequest request);
}
