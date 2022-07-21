package com.yaolizh.oa.carusehistory.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.carusehistory.domain.CarUseHistoryDO;
import com.yaolizh.oa.carusehistory.service.CarUseHistoryService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CarUseHistoryServiceImpl  extends BaseServiceImpl<CarUseHistoryDO> implements CarUseHistoryService {
	//@Autowired
//private CarUseHistoryDao carUseHistoryDao;
//
//@Override
//public CarUseHistoryDO get(String id){
//	return carUseHistoryDao.get(id);
//}
//
//@Override
//public List<CarUseHistoryDO> list(Map<String, Object> map){
//	return carUseHistoryDao.list(map);
//}
//
//
//@Override
//public int save(CarUseHistoryDO carUseHistory){
//	if(StringUtils.isEmpty(carUseHistory.getId())){
//		carUseHistory.setId(UuidUtil.getUUIDfor32());
//	}
//	carUseHistory.setCreateTime(new Date());
//	return carUseHistoryDao.save(carUseHistory);
//}
//
//@Override
//public int update(CarUseHistoryDO carUseHistory){
//	carUseHistory.setLastTime(new Date());
//	return carUseHistoryDao.update(carUseHistory);
//}
//
//@Override
//public int remove(String id){
//	return carUseHistoryDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return carUseHistoryDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<CarUseHistoryDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (CarUseHistoryDO carUseHistory : list) {
			i++;
			if (StringUtils.isEmpty(carUseHistory.getId())) {
				carUseHistory.setCreator(loginInfo.getId() + "");
				carUseHistory.setCreatorby(loginInfo.getUsername());
				carUseHistory.setCreatorby(loginInfo.getName());
				carUseHistory.setCreateDeptid(loginInfo.getDeptId() + "");
				carUseHistory.setCreateDeptcode(loginInfo.getDeptId() + "");
				carUseHistory.setCreateDeptname(loginInfo.getDeptName());
				save(carUseHistory);
			} else {
				carUseHistory.setUpdator(loginInfo.getId() + "");
				carUseHistory.setUpdatorby(loginInfo.getUsername());
				carUseHistory.setUpdatorby(loginInfo.getName());
				update(carUseHistory);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
