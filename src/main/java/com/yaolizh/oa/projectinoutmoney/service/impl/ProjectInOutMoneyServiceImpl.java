package com.yaolizh.oa.projectinoutmoney.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.projectinoutmoney.domain.ProjectInOutMoneyDO;
import com.yaolizh.oa.projectinoutmoney.service.ProjectInOutMoneyService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProjectInOutMoneyServiceImpl  extends BaseServiceImpl<ProjectInOutMoneyDO> implements ProjectInOutMoneyService {
	//@Autowired
//private ProjectInOutMoneyDao projectInOutMoneyDao;
//
//@Override
//public ProjectInOutMoneyDO get(String id){
//	return projectInOutMoneyDao.get(id);
//}
//
//@Override
//public List<ProjectInOutMoneyDO> list(Map<String, Object> map){
//	return projectInOutMoneyDao.list(map);
//}
//
//
//@Override
//public int save(ProjectInOutMoneyDO projectInOutMoney){
//	if(StringUtils.isEmpty(projectInOutMoney.getId())){
//		projectInOutMoney.setId(UuidUtil.getUUIDfor32());
//	}
//	projectInOutMoney.setCreateTime(new Date());
//	return projectInOutMoneyDao.save(projectInOutMoney);
//}
//
//@Override
//public int update(ProjectInOutMoneyDO projectInOutMoney){
//	projectInOutMoney.setLastTime(new Date());
//	return projectInOutMoneyDao.update(projectInOutMoney);
//}
//
//@Override
//public int remove(String id){
//	return projectInOutMoneyDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return projectInOutMoneyDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<ProjectInOutMoneyDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (ProjectInOutMoneyDO projectInOutMoney : list) {
			i++;
			if (StringUtils.isEmpty(projectInOutMoney.getId())) {
				projectInOutMoney.setCreator(loginInfo.getId() + "");
				projectInOutMoney.setCreatorby(loginInfo.getUsername());
				projectInOutMoney.setCreatorby(loginInfo.getName());
				projectInOutMoney.setCreateDeptid(loginInfo.getDeptId() + "");
				projectInOutMoney.setCreateDeptcode(loginInfo.getDeptId() + "");
				projectInOutMoney.setCreateDeptname(loginInfo.getDeptName());
				save(projectInOutMoney);
			} else {
				projectInOutMoney.setUpdator(loginInfo.getId() + "");
				projectInOutMoney.setUpdatorby(loginInfo.getUsername());
				projectInOutMoney.setUpdatorby(loginInfo.getName());
				update(projectInOutMoney);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
