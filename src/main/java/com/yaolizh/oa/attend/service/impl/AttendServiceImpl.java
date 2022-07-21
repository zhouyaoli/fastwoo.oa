package com.yaolizh.oa.attend.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.attend.domain.AttendDO;
import com.yaolizh.oa.attend.service.AttendService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AttendServiceImpl  extends BaseServiceImpl<AttendDO> implements AttendService {
	//@Autowired
//private AttendDao attendDao;
//
//@Override
//public AttendDO get(String id){
//	return attendDao.get(id);
//}
//
//@Override
//public List<AttendDO> list(Map<String, Object> map){
//	return attendDao.list(map);
//}
//
//
//@Override
//public int save(AttendDO attend){
//	if(StringUtils.isEmpty(attend.getId())){
//		attend.setId(UuidUtil.getUUIDfor32());
//	}
//	attend.setCreateTime(new Date());
//	return attendDao.save(attend);
//}
//
//@Override
//public int update(AttendDO attend){
//	attend.setLastTime(new Date());
//	return attendDao.update(attend);
//}
//
//@Override
//public int remove(String id){
//	return attendDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return attendDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<AttendDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (AttendDO attend : list) {
			i++;
			if (StringUtils.isEmpty(attend.getId())) {
				attend.setCreator(loginInfo.getId() + "");
				attend.setCreatorby(loginInfo.getUsername());
				attend.setCreatorby(loginInfo.getName());
				attend.setCreateDeptid(loginInfo.getDeptId() + "");
				attend.setCreateDeptcode(loginInfo.getDeptId() + "");
				attend.setCreateDeptname(loginInfo.getDeptName());
				save(attend);
			} else {
				attend.setUpdator(loginInfo.getId() + "");
				attend.setUpdatorby(loginInfo.getUsername());
				attend.setUpdatorby(loginInfo.getName());
				update(attend);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
