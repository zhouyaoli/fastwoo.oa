package com.yaolizh.oa.empexaminereport.dao;

import com.yaolizh.oa.empexaminereport.domain.EmpExamineReportDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 职工体检报告
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:04
 */
@Mapper
public interface EmpExamineReportDao extends MyMapper<EmpExamineReportDO>{

	//EmpExamineReportDO get(String id);
	
	List<EmpExamineReportDO> list(Map<String,Object> map);
	
	
	//int save(EmpExamineReportDO empExamineReport);
	
	//int update(EmpExamineReportDO empExamineReport);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
