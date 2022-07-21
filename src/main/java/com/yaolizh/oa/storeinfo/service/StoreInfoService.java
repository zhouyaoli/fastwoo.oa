package com.yaolizh.oa.storeinfo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.storeinfo.domain.StoreInfoDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 仓库情况
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:55
 */
public interface StoreInfoService extends BaseService<StoreInfoDO>{
	
//	StoreInfoDO get(String id);
	
//	List<StoreInfoDO> list(Map<String, Object> map);
	
	
//	int save(StoreInfoDO storeInfo);
	
//	int update(StoreInfoDO storeInfo);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<StoreInfoDO> list, UserDO loginInfo, HttpServletRequest request);
}
