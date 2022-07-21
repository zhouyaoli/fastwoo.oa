package com.yaolizh.oa.learnresource.dao;

import com.yaolizh.oa.learnresource.domain.LearnResourceDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学习平台资源信息
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:56
 */
@Mapper
public interface LearnResourceDao extends MyMapper<LearnResourceDO>{

	//LearnResourceDO get(String id);
	
	List<LearnResourceDO> list(Map<String,Object> map);
	
	
	//int save(LearnResourceDO learnResource);
	
	//int update(LearnResourceDO learnResource);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
