package com.yaolizh.oa.projectrefund.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.projectrefund.domain.ProjectRefundDO;
import com.yaolizh.oa.projectrefund.service.ProjectRefundService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProjectRefundServiceImpl  extends BaseServiceImpl<ProjectRefundDO> implements ProjectRefundService {
	//@Autowired
//private ProjectRefundDao projectRefundDao;
//
//@Override
//public ProjectRefundDO get(String id){
//	return projectRefundDao.get(id);
//}
//
//@Override
//public List<ProjectRefundDO> list(Map<String, Object> map){
//	return projectRefundDao.list(map);
//}
//
//
//@Override
//public int save(ProjectRefundDO projectRefund){
//	if(StringUtils.isEmpty(projectRefund.getId())){
//		projectRefund.setId(UuidUtil.getUUIDfor32());
//	}
//	projectRefund.setCreateTime(new Date());
//	return projectRefundDao.save(projectRefund);
//}
//
//@Override
//public int update(ProjectRefundDO projectRefund){
//	projectRefund.setLastTime(new Date());
//	return projectRefundDao.update(projectRefund);
//}
//
//@Override
//public int remove(String id){
//	return projectRefundDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return projectRefundDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<ProjectRefundDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (ProjectRefundDO projectRefund : list) {
			i++;
			if (StringUtils.isEmpty(projectRefund.getId())) {
				projectRefund.setCreator(loginInfo.getId() + "");
				projectRefund.setCreatorby(loginInfo.getUsername());
				projectRefund.setCreatorby(loginInfo.getName());
				projectRefund.setCreateDeptid(loginInfo.getDeptId() + "");
				projectRefund.setCreateDeptcode(loginInfo.getDeptId() + "");
				projectRefund.setCreateDeptname(loginInfo.getDeptName());
				save(projectRefund);
			} else {
				projectRefund.setUpdator(loginInfo.getId() + "");
				projectRefund.setUpdatorby(loginInfo.getUsername());
				projectRefund.setUpdatorby(loginInfo.getName());
				update(projectRefund);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
