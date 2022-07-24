package com.yaolizh.oa.storeinout.dao;

import com.yaolizh.oa.storeinout.domain.StoreInOutDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库出入库记录
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:00
 */
@Mapper
public interface StoreInOutDao extends MyMapper<StoreInOutDO>{

	//StoreInOutDO get(String id);
	
	List<StoreInOutDO> list(Map<String,Object> map);
	
	
	//int save(StoreInOutDO storeInOut);
	
	//int update(StoreInOutDO storeInOut);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
