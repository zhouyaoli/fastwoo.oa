package com.yaolizh.oa.emp.dao;

import com.yaolizh.oa.emp.domain.EmpDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 职工档案信息
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:05
 */
@Mapper
public interface EmpDao extends MyMapper<EmpDO>{

	//EmpDO get(String id);
	
	List<EmpDO> list(Map<String,Object> map);
	
	
	//int save(EmpDO emp);
	
	//int update(EmpDO emp);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
