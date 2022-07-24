package com.yaolizh.oa.projectrefunditem.dao;

import com.yaolizh.oa.projectrefunditem.domain.ProjectRefundItemDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 报销子项表
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:01
 */
@Mapper
public interface ProjectRefundItemDao extends MyMapper<ProjectRefundItemDO>{

	//ProjectRefundItemDO get(String id);
	
	List<ProjectRefundItemDO> list(Map<String,Object> map);
	
	
	//int save(ProjectRefundItemDO projectRefundItem);
	
	//int update(ProjectRefundItemDO projectRefundItem);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
