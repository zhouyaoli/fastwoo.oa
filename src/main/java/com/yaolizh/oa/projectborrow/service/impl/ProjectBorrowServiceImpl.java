package com.yaolizh.oa.projectborrow.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.projectborrow.domain.ProjectBorrowDO;
import com.yaolizh.oa.projectborrow.service.ProjectBorrowService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProjectBorrowServiceImpl  extends BaseServiceImpl<ProjectBorrowDO> implements ProjectBorrowService {
	//@Autowired
//private ProjectBorrowDao projectBorrowDao;
//
//@Override
//public ProjectBorrowDO get(String id){
//	return projectBorrowDao.get(id);
//}
//
//@Override
//public List<ProjectBorrowDO> list(Map<String, Object> map){
//	return projectBorrowDao.list(map);
//}
//
//
//@Override
//public int save(ProjectBorrowDO projectBorrow){
//	if(StringUtils.isEmpty(projectBorrow.getId())){
//		projectBorrow.setId(UuidUtil.getUUIDfor32());
//	}
//	projectBorrow.setCreateTime(new Date());
//	return projectBorrowDao.save(projectBorrow);
//}
//
//@Override
//public int update(ProjectBorrowDO projectBorrow){
//	projectBorrow.setLastTime(new Date());
//	return projectBorrowDao.update(projectBorrow);
//}
//
//@Override
//public int remove(String id){
//	return projectBorrowDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return projectBorrowDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<ProjectBorrowDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (ProjectBorrowDO projectBorrow : list) {
			i++;
			if (StringUtils.isEmpty(projectBorrow.getId())) {
				projectBorrow.setCreator(loginInfo.getId() + "");
				projectBorrow.setCreatorby(loginInfo.getUsername());
				projectBorrow.setCreatorby(loginInfo.getName());
				projectBorrow.setCreateDeptid(loginInfo.getDeptId() + "");
				projectBorrow.setCreateDeptcode(loginInfo.getDeptId() + "");
				projectBorrow.setCreateDeptname(loginInfo.getDeptName());
				save(projectBorrow);
			} else {
				projectBorrow.setUpdator(loginInfo.getId() + "");
				projectBorrow.setUpdatorby(loginInfo.getUsername());
				projectBorrow.setUpdatorby(loginInfo.getName());
				update(projectBorrow);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
