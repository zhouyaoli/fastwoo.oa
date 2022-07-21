package com.yaolizh.oa.projectrefunditem.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.projectrefunditem.domain.ProjectRefundItemDO;
import com.yaolizh.oa.projectrefunditem.service.ProjectRefundItemService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProjectRefundItemServiceImpl  extends BaseServiceImpl<ProjectRefundItemDO> implements ProjectRefundItemService {
	//@Autowired
//private ProjectRefundItemDao projectRefundItemDao;
//
//@Override
//public ProjectRefundItemDO get(String id){
//	return projectRefundItemDao.get(id);
//}
//
//@Override
//public List<ProjectRefundItemDO> list(Map<String, Object> map){
//	return projectRefundItemDao.list(map);
//}
//
//
//@Override
//public int save(ProjectRefundItemDO projectRefundItem){
//	if(StringUtils.isEmpty(projectRefundItem.getId())){
//		projectRefundItem.setId(UuidUtil.getUUIDfor32());
//	}
//	projectRefundItem.setCreateTime(new Date());
//	return projectRefundItemDao.save(projectRefundItem);
//}
//
//@Override
//public int update(ProjectRefundItemDO projectRefundItem){
//	projectRefundItem.setLastTime(new Date());
//	return projectRefundItemDao.update(projectRefundItem);
//}
//
//@Override
//public int remove(String id){
//	return projectRefundItemDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return projectRefundItemDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<ProjectRefundItemDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (ProjectRefundItemDO projectRefundItem : list) {
			i++;
			if (StringUtils.isEmpty(projectRefundItem.getId())) {
				projectRefundItem.setCreator(loginInfo.getId() + "");
				projectRefundItem.setCreatorby(loginInfo.getUsername());
				projectRefundItem.setCreatorby(loginInfo.getName());
				projectRefundItem.setCreateDeptid(loginInfo.getDeptId() + "");
				projectRefundItem.setCreateDeptcode(loginInfo.getDeptId() + "");
				projectRefundItem.setCreateDeptname(loginInfo.getDeptName());
				save(projectRefundItem);
			} else {
				projectRefundItem.setUpdator(loginInfo.getId() + "");
				projectRefundItem.setUpdatorby(loginInfo.getUsername());
				projectRefundItem.setUpdatorby(loginInfo.getName());
				update(projectRefundItem);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
