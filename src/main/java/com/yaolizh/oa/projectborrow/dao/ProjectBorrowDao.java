package com.yaolizh.oa.projectborrow.dao;

import com.yaolizh.oa.projectborrow.domain.ProjectBorrowDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 借款信息表
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:54
 */
@Mapper
public interface ProjectBorrowDao extends MyMapper<ProjectBorrowDO>{

	//ProjectBorrowDO get(String id);
	
	List<ProjectBorrowDO> list(Map<String,Object> map);
	
	
	//int save(ProjectBorrowDO projectBorrow);
	
	//int update(ProjectBorrowDO projectBorrow);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
