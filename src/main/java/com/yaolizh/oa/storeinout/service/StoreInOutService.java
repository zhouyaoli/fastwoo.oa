package com.yaolizh.oa.storeinout.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.storeinout.domain.StoreInOutDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 仓库出入库记录
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:00
 */
public interface StoreInOutService extends BaseService<StoreInOutDO>{
	
//	StoreInOutDO get(String id);
	
//	List<StoreInOutDO> list(Map<String, Object> map);
	
	
//	int save(StoreInOutDO storeInOut);
	
//	int update(StoreInOutDO storeInOut);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<StoreInOutDO> list, UserDO loginInfo, HttpServletRequest request);
}
