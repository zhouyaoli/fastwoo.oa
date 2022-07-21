package com.yaolizh.oa.car.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.car.domain.CarDO;
import com.yaolizh.oa.car.service.CarService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CarServiceImpl  extends BaseServiceImpl<CarDO> implements CarService {
	//@Autowired
//private CarDao carDao;
//
//@Override
//public CarDO get(String id){
//	return carDao.get(id);
//}
//
//@Override
//public List<CarDO> list(Map<String, Object> map){
//	return carDao.list(map);
//}
//
//
//@Override
//public int save(CarDO car){
//	if(StringUtils.isEmpty(car.getId())){
//		car.setId(UuidUtil.getUUIDfor32());
//	}
//	car.setCreateTime(new Date());
//	return carDao.save(car);
//}
//
//@Override
//public int update(CarDO car){
//	car.setLastTime(new Date());
//	return carDao.update(car);
//}
//
//@Override
//public int remove(String id){
//	return carDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return carDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<CarDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (CarDO car : list) {
			i++;
			if (StringUtils.isEmpty(car.getId())) {
				car.setCreator(loginInfo.getId() + "");
				car.setCreatorby(loginInfo.getUsername());
				car.setCreatorby(loginInfo.getName());
				car.setCreateDeptid(loginInfo.getDeptId() + "");
				car.setCreateDeptcode(loginInfo.getDeptId() + "");
				car.setCreateDeptname(loginInfo.getDeptName());
				save(car);
			} else {
				car.setUpdator(loginInfo.getId() + "");
				car.setUpdatorby(loginInfo.getUsername());
				car.setUpdatorby(loginInfo.getName());
				update(car);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
