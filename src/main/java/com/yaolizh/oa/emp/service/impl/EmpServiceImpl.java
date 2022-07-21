package com.yaolizh.oa.emp.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.emp.domain.EmpDO;
import com.yaolizh.oa.emp.service.EmpService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmpServiceImpl  extends BaseServiceImpl<EmpDO> implements EmpService {
	//@Autowired
//private EmpDao empDao;
//
//@Override
//public EmpDO get(String id){
//	return empDao.get(id);
//}
//
//@Override
//public List<EmpDO> list(Map<String, Object> map){
//	return empDao.list(map);
//}
//
//
//@Override
//public int save(EmpDO emp){
//	if(StringUtils.isEmpty(emp.getId())){
//		emp.setId(UuidUtil.getUUIDfor32());
//	}
//	emp.setCreateTime(new Date());
//	return empDao.save(emp);
//}
//
//@Override
//public int update(EmpDO emp){
//	emp.setLastTime(new Date());
//	return empDao.update(emp);
//}
//
//@Override
//public int remove(String id){
//	return empDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return empDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<EmpDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (EmpDO emp : list) {
			i++;
			if (StringUtils.isEmpty(emp.getId())) {
				emp.setCreator(loginInfo.getId() + "");
				emp.setCreatorby(loginInfo.getUsername());
				emp.setCreatorby(loginInfo.getName());
				emp.setCreateDeptid(loginInfo.getDeptId() + "");
				emp.setCreateDeptcode(loginInfo.getDeptId() + "");
				emp.setCreateDeptname(loginInfo.getDeptName());
				save(emp);
			} else {
				emp.setUpdator(loginInfo.getId() + "");
				emp.setUpdatorby(loginInfo.getUsername());
				emp.setUpdatorby(loginInfo.getName());
				update(emp);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
