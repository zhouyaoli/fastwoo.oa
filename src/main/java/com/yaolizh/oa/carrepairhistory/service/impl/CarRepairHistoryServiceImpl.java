package com.yaolizh.oa.carrepairhistory.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.carrepairhistory.domain.CarRepairHistoryDO;
import com.yaolizh.oa.carrepairhistory.service.CarRepairHistoryService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CarRepairHistoryServiceImpl  extends BaseServiceImpl<CarRepairHistoryDO> implements CarRepairHistoryService {
	//@Autowired
//private CarRepairHistoryDao carRepairHistoryDao;
//
//@Override
//public CarRepairHistoryDO get(String id){
//	return carRepairHistoryDao.get(id);
//}
//
//@Override
//public List<CarRepairHistoryDO> list(Map<String, Object> map){
//	return carRepairHistoryDao.list(map);
//}
//
//
//@Override
//public int save(CarRepairHistoryDO carRepairHistory){
//	if(StringUtils.isEmpty(carRepairHistory.getId())){
//		carRepairHistory.setId(UuidUtil.getUUIDfor32());
//	}
//	carRepairHistory.setCreateTime(new Date());
//	return carRepairHistoryDao.save(carRepairHistory);
//}
//
//@Override
//public int update(CarRepairHistoryDO carRepairHistory){
//	carRepairHistory.setLastTime(new Date());
//	return carRepairHistoryDao.update(carRepairHistory);
//}
//
//@Override
//public int remove(String id){
//	return carRepairHistoryDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return carRepairHistoryDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<CarRepairHistoryDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (CarRepairHistoryDO carRepairHistory : list) {
			i++;
			if (StringUtils.isEmpty(carRepairHistory.getId())) {
				carRepairHistory.setCreator(loginInfo.getId() + "");
				carRepairHistory.setCreatorby(loginInfo.getUsername());
				carRepairHistory.setCreatorby(loginInfo.getName());
				carRepairHistory.setCreateDeptid(loginInfo.getDeptId() + "");
				carRepairHistory.setCreateDeptcode(loginInfo.getDeptId() + "");
				carRepairHistory.setCreateDeptname(loginInfo.getDeptName());
				save(carRepairHistory);
			} else {
				carRepairHistory.setUpdator(loginInfo.getId() + "");
				carRepairHistory.setUpdatorby(loginInfo.getUsername());
				carRepairHistory.setUpdatorby(loginInfo.getName());
				update(carRepairHistory);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
