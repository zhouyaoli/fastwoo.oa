package com.yaolizh.oa.projectinoutmoney.dao;

import com.yaolizh.oa.projectinoutmoney.domain.ProjectInOutMoneyDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收支明细
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:53
 */
@Mapper
public interface ProjectInOutMoneyDao extends MyMapper<ProjectInOutMoneyDO>{

	//ProjectInOutMoneyDO get(String id);
	
	List<ProjectInOutMoneyDO> list(Map<String,Object> map);
	
	
	//int save(ProjectInOutMoneyDO projectInOutMoney);
	
	//int update(ProjectInOutMoneyDO projectInOutMoney);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
