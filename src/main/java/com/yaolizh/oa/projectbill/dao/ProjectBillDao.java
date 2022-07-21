package com.yaolizh.oa.projectbill.dao;

import com.yaolizh.oa.projectbill.domain.ProjectBillDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 开票情况信息
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:56
 */
@Mapper
public interface ProjectBillDao extends MyMapper<ProjectBillDO>{

	//ProjectBillDO get(String id);
	
	List<ProjectBillDO> list(Map<String,Object> map);
	
	
	//int save(ProjectBillDO projectBill);
	
	//int update(ProjectBillDO projectBill);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
