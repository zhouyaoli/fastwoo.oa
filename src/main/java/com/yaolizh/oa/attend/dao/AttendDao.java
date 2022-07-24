package com.yaolizh.oa.attend.dao;

import com.yaolizh.oa.attend.domain.AttendDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考勤打卡信息
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:07
 */
@Mapper
public interface AttendDao extends MyMapper<AttendDO>{

	//AttendDO get(String id);
	
	List<AttendDO> list(Map<String,Object> map);
	
	
	//int save(AttendDO attend);
	
	//int update(AttendDO attend);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
