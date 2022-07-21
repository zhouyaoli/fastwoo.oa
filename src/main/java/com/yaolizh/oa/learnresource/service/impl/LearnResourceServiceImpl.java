package com.yaolizh.oa.learnresource.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.learnresource.domain.LearnResourceDO;
import com.yaolizh.oa.learnresource.service.LearnResourceService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LearnResourceServiceImpl  extends BaseServiceImpl<LearnResourceDO> implements LearnResourceService {
	//@Autowired
//private LearnResourceDao learnResourceDao;
//
//@Override
//public LearnResourceDO get(String id){
//	return learnResourceDao.get(id);
//}
//
//@Override
//public List<LearnResourceDO> list(Map<String, Object> map){
//	return learnResourceDao.list(map);
//}
//
//
//@Override
//public int save(LearnResourceDO learnResource){
//	if(StringUtils.isEmpty(learnResource.getId())){
//		learnResource.setId(UuidUtil.getUUIDfor32());
//	}
//	learnResource.setCreateTime(new Date());
//	return learnResourceDao.save(learnResource);
//}
//
//@Override
//public int update(LearnResourceDO learnResource){
//	learnResource.setLastTime(new Date());
//	return learnResourceDao.update(learnResource);
//}
//
//@Override
//public int remove(String id){
//	return learnResourceDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return learnResourceDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<LearnResourceDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (LearnResourceDO learnResource : list) {
			i++;
			if (StringUtils.isEmpty(learnResource.getId())) {
				learnResource.setCreator(loginInfo.getId() + "");
				learnResource.setCreatorby(loginInfo.getUsername());
				learnResource.setCreatorby(loginInfo.getName());
				learnResource.setCreateDeptid(loginInfo.getDeptId() + "");
				learnResource.setCreateDeptcode(loginInfo.getDeptId() + "");
				learnResource.setCreateDeptname(loginInfo.getDeptName());
				save(learnResource);
			} else {
				learnResource.setUpdator(loginInfo.getId() + "");
				learnResource.setUpdatorby(loginInfo.getUsername());
				learnResource.setUpdatorby(loginInfo.getName());
				update(learnResource);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
