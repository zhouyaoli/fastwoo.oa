package com.yaolizh.oa.projectcontract.dao;

import com.yaolizh.oa.projectcontract.domain.ProjectContractDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 合同信息（附件：中标通知书）
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 17:13:51
 */
@Mapper
public interface ProjectContractDao extends MyMapper<ProjectContractDO>{

	//ProjectContractDO get(String id);
	
	List<ProjectContractDO> list(Map<String,Object> map);
	
	
	//int save(ProjectContractDO projectContract);
	
	//int update(ProjectContractDO projectContract);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
