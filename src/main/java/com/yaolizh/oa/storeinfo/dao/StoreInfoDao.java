package com.yaolizh.oa.storeinfo.dao;

import com.yaolizh.oa.storeinfo.domain.StoreInfoDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库情况
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:55
 */
@Mapper
public interface StoreInfoDao extends MyMapper<StoreInfoDO>{

	//StoreInfoDO get(String id);
	
	List<StoreInfoDO> list(Map<String,Object> map);
	
	
	//int save(StoreInfoDO storeInfo);
	
	//int update(StoreInfoDO storeInfo);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
