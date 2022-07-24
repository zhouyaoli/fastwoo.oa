package com.yaolizh.oa.storegoods.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.storegoods.domain.StoreGoodsDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 仓库物品
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:01
 */
public interface StoreGoodsService extends BaseService<StoreGoodsDO>{
	
//	StoreGoodsDO get(String id);
	
//	List<StoreGoodsDO> list(Map<String, Object> map);
	
	
//	int save(StoreGoodsDO storeGoods);
	
//	int update(StoreGoodsDO storeGoods);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<StoreGoodsDO> list, UserDO loginInfo, HttpServletRequest request);
}
