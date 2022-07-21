package com.yaolizh.oa.projectcontract.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.projectcontract.domain.ProjectContractDO;
import com.yaolizh.oa.projectcontract.service.ProjectContractService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProjectContractServiceImpl  extends BaseServiceImpl<ProjectContractDO> implements ProjectContractService {
	//@Autowired
//private ProjectContractDao projectContractDao;
//
//@Override
//public ProjectContractDO get(String id){
//	return projectContractDao.get(id);
//}
//
//@Override
//public List<ProjectContractDO> list(Map<String, Object> map){
//	return projectContractDao.list(map);
//}
//
//
//@Override
//public int save(ProjectContractDO projectContract){
//	if(StringUtils.isEmpty(projectContract.getId())){
//		projectContract.setId(UuidUtil.getUUIDfor32());
//	}
//	projectContract.setCreateTime(new Date());
//	return projectContractDao.save(projectContract);
//}
//
//@Override
//public int update(ProjectContractDO projectContract){
//	projectContract.setLastTime(new Date());
//	return projectContractDao.update(projectContract);
//}
//
//@Override
//public int remove(String id){
//	return projectContractDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return projectContractDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<ProjectContractDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (ProjectContractDO projectContract : list) {
			i++;
			if (StringUtils.isEmpty(projectContract.getId())) {
				projectContract.setCreator(loginInfo.getId() + "");
				projectContract.setCreatorby(loginInfo.getUsername());
				projectContract.setCreatorby(loginInfo.getName());
				projectContract.setCreateDeptid(loginInfo.getDeptId() + "");
				projectContract.setCreateDeptcode(loginInfo.getDeptId() + "");
				projectContract.setCreateDeptname(loginInfo.getDeptName());
				save(projectContract);
			} else {
				projectContract.setUpdator(loginInfo.getId() + "");
				projectContract.setUpdatorby(loginInfo.getUsername());
				projectContract.setUpdatorby(loginInfo.getName());
				update(projectContract);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
