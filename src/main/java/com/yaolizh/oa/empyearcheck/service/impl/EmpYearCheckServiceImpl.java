package com.yaolizh.oa.empyearcheck.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.empyearcheck.domain.EmpYearCheckDO;
import com.yaolizh.oa.empyearcheck.service.EmpYearCheckService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmpYearCheckServiceImpl  extends BaseServiceImpl<EmpYearCheckDO> implements EmpYearCheckService {
	//@Autowired
//private EmpYearCheckDao empYearCheckDao;
//
//@Override
//public EmpYearCheckDO get(String id){
//	return empYearCheckDao.get(id);
//}
//
//@Override
//public List<EmpYearCheckDO> list(Map<String, Object> map){
//	return empYearCheckDao.list(map);
//}
//
//
//@Override
//public int save(EmpYearCheckDO empYearCheck){
//	if(StringUtils.isEmpty(empYearCheck.getId())){
//		empYearCheck.setId(UuidUtil.getUUIDfor32());
//	}
//	empYearCheck.setCreateTime(new Date());
//	return empYearCheckDao.save(empYearCheck);
//}
//
//@Override
//public int update(EmpYearCheckDO empYearCheck){
//	empYearCheck.setLastTime(new Date());
//	return empYearCheckDao.update(empYearCheck);
//}
//
//@Override
//public int remove(String id){
//	return empYearCheckDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return empYearCheckDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<EmpYearCheckDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (EmpYearCheckDO empYearCheck : list) {
			i++;
			if (StringUtils.isEmpty(empYearCheck.getId())) {
				empYearCheck.setCreator(loginInfo.getId() + "");
				empYearCheck.setCreatorby(loginInfo.getUsername());
				empYearCheck.setCreatorby(loginInfo.getName());
				empYearCheck.setCreateDeptid(loginInfo.getDeptId() + "");
				empYearCheck.setCreateDeptcode(loginInfo.getDeptId() + "");
				empYearCheck.setCreateDeptname(loginInfo.getDeptName());
				save(empYearCheck);
			} else {
				empYearCheck.setUpdator(loginInfo.getId() + "");
				empYearCheck.setUpdatorby(loginInfo.getUsername());
				empYearCheck.setUpdatorby(loginInfo.getName());
				update(empYearCheck);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
