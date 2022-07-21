package com.yaolizh.oa.carinsurehistory.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.carinsurehistory.domain.CarInsureHistoryDO;
import com.yaolizh.oa.carinsurehistory.service.CarInsureHistoryService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CarInsureHistoryServiceImpl  extends BaseServiceImpl<CarInsureHistoryDO> implements CarInsureHistoryService {
	//@Autowired
//private CarInsureHistoryDao carInsureHistoryDao;
//
//@Override
//public CarInsureHistoryDO get(String id){
//	return carInsureHistoryDao.get(id);
//}
//
//@Override
//public List<CarInsureHistoryDO> list(Map<String, Object> map){
//	return carInsureHistoryDao.list(map);
//}
//
//
//@Override
//public int save(CarInsureHistoryDO carInsureHistory){
//	if(StringUtils.isEmpty(carInsureHistory.getId())){
//		carInsureHistory.setId(UuidUtil.getUUIDfor32());
//	}
//	carInsureHistory.setCreateTime(new Date());
//	return carInsureHistoryDao.save(carInsureHistory);
//}
//
//@Override
//public int update(CarInsureHistoryDO carInsureHistory){
//	carInsureHistory.setLastTime(new Date());
//	return carInsureHistoryDao.update(carInsureHistory);
//}
//
//@Override
//public int remove(String id){
//	return carInsureHistoryDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return carInsureHistoryDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<CarInsureHistoryDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (CarInsureHistoryDO carInsureHistory : list) {
			i++;
			if (StringUtils.isEmpty(carInsureHistory.getId())) {
				carInsureHistory.setCreator(loginInfo.getId() + "");
				carInsureHistory.setCreatorby(loginInfo.getUsername());
				carInsureHistory.setCreatorby(loginInfo.getName());
				carInsureHistory.setCreateDeptid(loginInfo.getDeptId() + "");
				carInsureHistory.setCreateDeptcode(loginInfo.getDeptId() + "");
				carInsureHistory.setCreateDeptname(loginInfo.getDeptName());
				save(carInsureHistory);
			} else {
				carInsureHistory.setUpdator(loginInfo.getId() + "");
				carInsureHistory.setUpdatorby(loginInfo.getUsername());
				carInsureHistory.setUpdatorby(loginInfo.getName());
				update(carInsureHistory);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
