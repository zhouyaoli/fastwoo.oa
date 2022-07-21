package com.yaolizh.oa.carfaulthistory.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.carfaulthistory.domain.CarFaultHistoryDO;
import com.yaolizh.oa.carfaulthistory.service.CarFaultHistoryService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CarFaultHistoryServiceImpl  extends BaseServiceImpl<CarFaultHistoryDO> implements CarFaultHistoryService {
	//@Autowired
//private CarFaultHistoryDao carFaultHistoryDao;
//
//@Override
//public CarFaultHistoryDO get(String id){
//	return carFaultHistoryDao.get(id);
//}
//
//@Override
//public List<CarFaultHistoryDO> list(Map<String, Object> map){
//	return carFaultHistoryDao.list(map);
//}
//
//
//@Override
//public int save(CarFaultHistoryDO carFaultHistory){
//	if(StringUtils.isEmpty(carFaultHistory.getId())){
//		carFaultHistory.setId(UuidUtil.getUUIDfor32());
//	}
//	carFaultHistory.setCreateTime(new Date());
//	return carFaultHistoryDao.save(carFaultHistory);
//}
//
//@Override
//public int update(CarFaultHistoryDO carFaultHistory){
//	carFaultHistory.setLastTime(new Date());
//	return carFaultHistoryDao.update(carFaultHistory);
//}
//
//@Override
//public int remove(String id){
//	return carFaultHistoryDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return carFaultHistoryDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<CarFaultHistoryDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (CarFaultHistoryDO carFaultHistory : list) {
			i++;
			if (StringUtils.isEmpty(carFaultHistory.getId())) {
				carFaultHistory.setCreator(loginInfo.getId() + "");
				carFaultHistory.setCreatorby(loginInfo.getUsername());
				carFaultHistory.setCreatorby(loginInfo.getName());
				carFaultHistory.setCreateDeptid(loginInfo.getDeptId() + "");
				carFaultHistory.setCreateDeptcode(loginInfo.getDeptId() + "");
				carFaultHistory.setCreateDeptname(loginInfo.getDeptName());
				save(carFaultHistory);
			} else {
				carFaultHistory.setUpdator(loginInfo.getId() + "");
				carFaultHistory.setUpdatorby(loginInfo.getUsername());
				carFaultHistory.setUpdatorby(loginInfo.getName());
				update(carFaultHistory);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
