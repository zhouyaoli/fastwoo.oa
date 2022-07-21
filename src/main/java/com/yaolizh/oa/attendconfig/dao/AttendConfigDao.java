package com.yaolizh.oa.attendconfig.dao;

import com.yaolizh.oa.attendconfig.domain.AttendConfigDO;

import java.util.List;
import java.util.Map;
import com.yaolizh.fastwoo.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考勤配置信息
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:16:00
 */
@Mapper
public interface AttendConfigDao extends MyMapper<AttendConfigDO>{

	//AttendConfigDO get(String id);
	
	List<AttendConfigDO> list(Map<String,Object> map);
	
	
	//int save(AttendConfigDO attendConfig);
	
	//int update(AttendConfigDO attendConfig);
	
	//int remove(String id);
	
	int batchRemove(String[] ids);
}
