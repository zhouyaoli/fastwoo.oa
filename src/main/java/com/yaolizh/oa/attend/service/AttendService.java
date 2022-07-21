package com.yaolizh.oa.attend.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.attend.domain.AttendDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 考勤打卡信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:53
 */
public interface AttendService extends BaseService<AttendDO>{
	
//	AttendDO get(String id);
	
//	List<AttendDO> list(Map<String, Object> map);
	
	
//	int save(AttendDO attend);
	
//	int update(AttendDO attend);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<AttendDO> list, UserDO loginInfo, HttpServletRequest request);
}
