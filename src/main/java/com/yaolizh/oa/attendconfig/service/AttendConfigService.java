package com.yaolizh.oa.attendconfig.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yaolizh.oa.attendconfig.domain.AttendConfigDO;
import com.yaolizh.fastwoo.common.service.BaseService;
//import java.util.List;
//import java.util.Map;
import com.yaolizh.fastwoo.system.domain.UserDO;
/**
 * 考勤配置信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:16:00
 */
public interface AttendConfigService extends BaseService<AttendConfigDO>{
	
//	AttendConfigDO get(String id);
	
//	List<AttendConfigDO> list(Map<String, Object> map);
	
	
//	int save(AttendConfigDO attendConfig);
	
//	int update(AttendConfigDO attendConfig);
	
//	int remove(String id);
	
//	int batchRemove(String[] ids);
	void saveImportExcel(List<AttendConfigDO> list, UserDO loginInfo, HttpServletRequest request);
}
