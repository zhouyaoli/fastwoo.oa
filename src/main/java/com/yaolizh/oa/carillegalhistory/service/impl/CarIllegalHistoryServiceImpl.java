package com.yaolizh.oa.carillegalhistory.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.carillegalhistory.domain.CarIllegalHistoryDO;
import com.yaolizh.oa.carillegalhistory.service.CarIllegalHistoryService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CarIllegalHistoryServiceImpl  extends BaseServiceImpl<CarIllegalHistoryDO> implements CarIllegalHistoryService {
	//@Autowired
//private CarIllegalHistoryDao carIllegalHistoryDao;
//
//@Override
//public CarIllegalHistoryDO get(String id){
//	return carIllegalHistoryDao.get(id);
//}
//
//@Override
//public List<CarIllegalHistoryDO> list(Map<String, Object> map){
//	return carIllegalHistoryDao.list(map);
//}
//
//
//@Override
//public int save(CarIllegalHistoryDO carIllegalHistory){
//	if(StringUtils.isEmpty(carIllegalHistory.getId())){
//		carIllegalHistory.setId(UuidUtil.getUUIDfor32());
//	}
//	carIllegalHistory.setCreateTime(new Date());
//	return carIllegalHistoryDao.save(carIllegalHistory);
//}
//
//@Override
//public int update(CarIllegalHistoryDO carIllegalHistory){
//	carIllegalHistory.setLastTime(new Date());
//	return carIllegalHistoryDao.update(carIllegalHistory);
//}
//
//@Override
//public int remove(String id){
//	return carIllegalHistoryDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return carIllegalHistoryDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<CarIllegalHistoryDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (CarIllegalHistoryDO carIllegalHistory : list) {
			i++;
			if (StringUtils.isEmpty(carIllegalHistory.getId())) {
				carIllegalHistory.setCreator(loginInfo.getId() + "");
				carIllegalHistory.setCreatorby(loginInfo.getUsername());
				carIllegalHistory.setCreatorby(loginInfo.getName());
				carIllegalHistory.setCreateDeptid(loginInfo.getDeptId() + "");
				carIllegalHistory.setCreateDeptcode(loginInfo.getDeptId() + "");
				carIllegalHistory.setCreateDeptname(loginInfo.getDeptName());
				save(carIllegalHistory);
			} else {
				carIllegalHistory.setUpdator(loginInfo.getId() + "");
				carIllegalHistory.setUpdatorby(loginInfo.getUsername());
				carIllegalHistory.setUpdatorby(loginInfo.getName());
				update(carIllegalHistory);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
