package com.yaolizh.oa.fixedresource.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.fixedresource.domain.FixedResourceDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 固定资产信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:58
 */
public interface FixedResourceService extends BaseService<FixedResourceDO>{
	
//	FixedResourceDO get(String id);
	
//	List<FixedResourceDO> list(Map<String, Object> map);
	
	
//	int save(FixedResourceDO fixedResource);
	
//	int update(FixedResourceDO fixedResource);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<FixedResourceDO> list, UserDO loginInfo, HttpServletRequest request);
}
