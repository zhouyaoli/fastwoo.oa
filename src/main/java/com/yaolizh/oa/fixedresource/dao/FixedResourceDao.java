package com.yaolizh.oa.fixedresource.dao;

import com.yaolizh.oa.fixedresource.domain.FixedResourceDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 固定资产信息
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:03
 */
@Mapper
public interface FixedResourceDao extends MyMapper<FixedResourceDO>{

	//FixedResourceDO get(String id);
	
	List<FixedResourceDO> list(Map<String,Object> map);
	
	
	//int save(FixedResourceDO fixedResource);
	
	//int update(FixedResourceDO fixedResource);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
