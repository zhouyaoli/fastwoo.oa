package com.yaolizh.oa.empresthistory.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.empresthistory.domain.EmpRestHistoryDO;
import com.yaolizh.oa.empresthistory.service.EmpRestHistoryService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmpRestHistoryServiceImpl  extends BaseServiceImpl<EmpRestHistoryDO> implements EmpRestHistoryService {
	//@Autowired
//private EmpRestHistoryDao empRestHistoryDao;
//
//@Override
//public EmpRestHistoryDO get(String id){
//	return empRestHistoryDao.get(id);
//}
//
//@Override
//public List<EmpRestHistoryDO> list(Map<String, Object> map){
//	return empRestHistoryDao.list(map);
//}
//
//
//@Override
//public int save(EmpRestHistoryDO empRestHistory){
//	if(StringUtils.isEmpty(empRestHistory.getId())){
//		empRestHistory.setId(UuidUtil.getUUIDfor32());
//	}
//	empRestHistory.setCreateTime(new Date());
//	return empRestHistoryDao.save(empRestHistory);
//}
//
//@Override
//public int update(EmpRestHistoryDO empRestHistory){
//	empRestHistory.setLastTime(new Date());
//	return empRestHistoryDao.update(empRestHistory);
//}
//
//@Override
//public int remove(String id){
//	return empRestHistoryDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return empRestHistoryDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<EmpRestHistoryDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (EmpRestHistoryDO empRestHistory : list) {
			i++;
			if (StringUtils.isEmpty(empRestHistory.getId())) {
				empRestHistory.setCreator(loginInfo.getId() + "");
				empRestHistory.setCreatorby(loginInfo.getUsername());
				empRestHistory.setCreatorby(loginInfo.getName());
				empRestHistory.setCreateDeptid(loginInfo.getDeptId() + "");
				empRestHistory.setCreateDeptcode(loginInfo.getDeptId() + "");
				empRestHistory.setCreateDeptname(loginInfo.getDeptName());
				save(empRestHistory);
			} else {
				empRestHistory.setUpdator(loginInfo.getId() + "");
				empRestHistory.setUpdatorby(loginInfo.getUsername());
				empRestHistory.setUpdatorby(loginInfo.getName());
				update(empRestHistory);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
