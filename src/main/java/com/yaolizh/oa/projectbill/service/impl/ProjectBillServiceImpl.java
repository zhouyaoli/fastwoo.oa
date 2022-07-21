package com.yaolizh.oa.projectbill.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.projectbill.domain.ProjectBillDO;
import com.yaolizh.oa.projectbill.service.ProjectBillService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProjectBillServiceImpl  extends BaseServiceImpl<ProjectBillDO> implements ProjectBillService {
	//@Autowired
//private ProjectBillDao projectBillDao;
//
//@Override
//public ProjectBillDO get(String id){
//	return projectBillDao.get(id);
//}
//
//@Override
//public List<ProjectBillDO> list(Map<String, Object> map){
//	return projectBillDao.list(map);
//}
//
//
//@Override
//public int save(ProjectBillDO projectBill){
//	if(StringUtils.isEmpty(projectBill.getId())){
//		projectBill.setId(UuidUtil.getUUIDfor32());
//	}
//	projectBill.setCreateTime(new Date());
//	return projectBillDao.save(projectBill);
//}
//
//@Override
//public int update(ProjectBillDO projectBill){
//	projectBill.setLastTime(new Date());
//	return projectBillDao.update(projectBill);
//}
//
//@Override
//public int remove(String id){
//	return projectBillDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return projectBillDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<ProjectBillDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (ProjectBillDO projectBill : list) {
			i++;
			if (StringUtils.isEmpty(projectBill.getId())) {
				projectBill.setCreator(loginInfo.getId() + "");
				projectBill.setCreatorby(loginInfo.getUsername());
				projectBill.setCreatorby(loginInfo.getName());
				projectBill.setCreateDeptid(loginInfo.getDeptId() + "");
				projectBill.setCreateDeptcode(loginInfo.getDeptId() + "");
				projectBill.setCreateDeptname(loginInfo.getDeptName());
				save(projectBill);
			} else {
				projectBill.setUpdator(loginInfo.getId() + "");
				projectBill.setUpdatorby(loginInfo.getUsername());
				projectBill.setUpdatorby(loginInfo.getName());
				update(projectBill);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
