package com.yaolizh.oa.projectrefund.dao;

import com.yaolizh.oa.projectrefund.domain.ProjectRefundDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 报销信息表
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:55
 */
@Mapper
public interface ProjectRefundDao extends MyMapper<ProjectRefundDO>{

	//ProjectRefundDO get(String id);
	
	List<ProjectRefundDO> list(Map<String,Object> map);
	
	
	//int save(ProjectRefundDO projectRefund);
	
	//int update(ProjectRefundDO projectRefund);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
