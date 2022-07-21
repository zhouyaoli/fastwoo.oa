package com.yaolizh.oa.empcontract.dao;

import com.yaolizh.oa.empcontract.domain.EmpContractDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 职工合同信息
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:57
 */
@Mapper
public interface EmpContractDao extends MyMapper<EmpContractDO>{

	//EmpContractDO get(String id);
	
	List<EmpContractDO> list(Map<String,Object> map);
	
	
	//int save(EmpContractDO empContract);
	
	//int update(EmpContractDO empContract);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
