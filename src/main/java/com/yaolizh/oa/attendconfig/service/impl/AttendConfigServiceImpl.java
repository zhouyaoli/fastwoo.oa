package com.yaolizh.oa.attendconfig.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.attendconfig.domain.AttendConfigDO;
import com.yaolizh.oa.attendconfig.service.AttendConfigService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AttendConfigServiceImpl  extends BaseServiceImpl<AttendConfigDO> implements AttendConfigService {
	//@Autowired
//private AttendConfigDao attendConfigDao;
//
//@Override
//public AttendConfigDO get(String id){
//	return attendConfigDao.get(id);
//}
//
//@Override
//public List<AttendConfigDO> list(Map<String, Object> map){
//	return attendConfigDao.list(map);
//}
//
//
//@Override
//public int save(AttendConfigDO attendConfig){
//	if(StringUtils.isEmpty(attendConfig.getId())){
//		attendConfig.setId(UuidUtil.getUUIDfor32());
//	}
//	attendConfig.setCreateTime(new Date());
//	return attendConfigDao.save(attendConfig);
//}
//
//@Override
//public int update(AttendConfigDO attendConfig){
//	attendConfig.setLastTime(new Date());
//	return attendConfigDao.update(attendConfig);
//}
//
//@Override
//public int remove(String id){
//	return attendConfigDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return attendConfigDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<AttendConfigDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (AttendConfigDO attendConfig : list) {
			i++;
			if (StringUtils.isEmpty(attendConfig.getId())) {
				attendConfig.setCreator(loginInfo.getId() + "");
				attendConfig.setCreatorby(loginInfo.getUsername());
				attendConfig.setCreatorby(loginInfo.getName());
				attendConfig.setCreateDeptid(loginInfo.getDeptId() + "");
				attendConfig.setCreateDeptcode(loginInfo.getDeptId() + "");
				attendConfig.setCreateDeptname(loginInfo.getDeptName());
				save(attendConfig);
			} else {
				attendConfig.setUpdator(loginInfo.getId() + "");
				attendConfig.setUpdatorby(loginInfo.getUsername());
				attendConfig.setUpdatorby(loginInfo.getName());
				update(attendConfig);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
