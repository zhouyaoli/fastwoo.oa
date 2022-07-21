package com.yaolizh.oa.project.dao;

import com.yaolizh.oa.project.domain.ProjectDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目信息
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:54
 */
@Mapper
public interface ProjectDao extends MyMapper<ProjectDO>{

	//ProjectDO get(String id);
	
	List<ProjectDO> list(Map<String,Object> map);
	
	
	//int save(ProjectDO project);
	
	//int update(ProjectDO project);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
