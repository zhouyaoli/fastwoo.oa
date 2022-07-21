package com.yaolizh.oa.project.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.project.domain.ProjectDO;
import com.yaolizh.oa.project.service.ProjectService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProjectServiceImpl  extends BaseServiceImpl<ProjectDO> implements ProjectService {
	//@Autowired
//private ProjectDao projectDao;
//
//@Override
//public ProjectDO get(String id){
//	return projectDao.get(id);
//}
//
//@Override
//public List<ProjectDO> list(Map<String, Object> map){
//	return projectDao.list(map);
//}
//
//
//@Override
//public int save(ProjectDO project){
//	if(StringUtils.isEmpty(project.getId())){
//		project.setId(UuidUtil.getUUIDfor32());
//	}
//	project.setCreateTime(new Date());
//	return projectDao.save(project);
//}
//
//@Override
//public int update(ProjectDO project){
//	project.setLastTime(new Date());
//	return projectDao.update(project);
//}
//
//@Override
//public int remove(String id){
//	return projectDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return projectDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<ProjectDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (ProjectDO project : list) {
			i++;
			if (StringUtils.isEmpty(project.getId())) {
				project.setCreator(loginInfo.getId() + "");
				project.setCreatorby(loginInfo.getUsername());
				project.setCreatorby(loginInfo.getName());
				project.setCreateDeptid(loginInfo.getDeptId() + "");
				project.setCreateDeptcode(loginInfo.getDeptId() + "");
				project.setCreateDeptname(loginInfo.getDeptName());
				save(project);
			} else {
				project.setUpdator(loginInfo.getId() + "");
				project.setUpdatorby(loginInfo.getUsername());
				project.setUpdatorby(loginInfo.getName());
				update(project);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
