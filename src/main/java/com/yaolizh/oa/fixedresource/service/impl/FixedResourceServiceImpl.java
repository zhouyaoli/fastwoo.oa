package com.yaolizh.oa.fixedresource.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.fixedresource.domain.FixedResourceDO;
import com.yaolizh.oa.fixedresource.service.FixedResourceService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FixedResourceServiceImpl  extends BaseServiceImpl<FixedResourceDO> implements FixedResourceService {
	//@Autowired
//private FixedResourceDao fixedResourceDao;
//
//@Override
//public FixedResourceDO get(String id){
//	return fixedResourceDao.get(id);
//}
//
//@Override
//public List<FixedResourceDO> list(Map<String, Object> map){
//	return fixedResourceDao.list(map);
//}
//
//
//@Override
//public int save(FixedResourceDO fixedResource){
//	if(StringUtils.isEmpty(fixedResource.getId())){
//		fixedResource.setId(UuidUtil.getUUIDfor32());
//	}
//	fixedResource.setCreateTime(new Date());
//	return fixedResourceDao.save(fixedResource);
//}
//
//@Override
//public int update(FixedResourceDO fixedResource){
//	fixedResource.setLastTime(new Date());
//	return fixedResourceDao.update(fixedResource);
//}
//
//@Override
//public int remove(String id){
//	return fixedResourceDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return fixedResourceDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<FixedResourceDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (FixedResourceDO fixedResource : list) {
			i++;
			if (StringUtils.isEmpty(fixedResource.getId())) {
				fixedResource.setCreator(loginInfo.getId() + "");
				fixedResource.setCreatorby(loginInfo.getUsername());
				fixedResource.setCreatorby(loginInfo.getName());
				fixedResource.setCreateDeptid(loginInfo.getDeptId() + "");
				fixedResource.setCreateDeptcode(loginInfo.getDeptId() + "");
				fixedResource.setCreateDeptname(loginInfo.getDeptName());
				save(fixedResource);
			} else {
				fixedResource.setUpdator(loginInfo.getId() + "");
				fixedResource.setUpdatorby(loginInfo.getUsername());
				fixedResource.setUpdatorby(loginInfo.getName());
				update(fixedResource);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
