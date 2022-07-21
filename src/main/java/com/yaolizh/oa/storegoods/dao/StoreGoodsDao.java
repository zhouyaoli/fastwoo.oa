package com.yaolizh.oa.storegoods.dao;

import com.yaolizh.oa.storegoods.domain.StoreGoodsDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库物品
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:56
 */
@Mapper
public interface StoreGoodsDao extends MyMapper<StoreGoodsDO>{

	//StoreGoodsDO get(String id);
	
	List<StoreGoodsDO> list(Map<String,Object> map);
	
	
	//int save(StoreGoodsDO storeGoods);
	
	//int update(StoreGoodsDO storeGoods);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
