package com.yaolizh.oa.storeinfo.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.storeinfo.domain.StoreInfoDO;
import com.yaolizh.oa.storeinfo.service.StoreInfoService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class StoreInfoServiceImpl  extends BaseServiceImpl<StoreInfoDO> implements StoreInfoService {
	//@Autowired
//private StoreInfoDao storeInfoDao;
//
//@Override
//public StoreInfoDO get(String id){
//	return storeInfoDao.get(id);
//}
//
//@Override
//public List<StoreInfoDO> list(Map<String, Object> map){
//	return storeInfoDao.list(map);
//}
//
//
//@Override
//public int save(StoreInfoDO storeInfo){
//	if(StringUtils.isEmpty(storeInfo.getId())){
//		storeInfo.setId(UuidUtil.getUUIDfor32());
//	}
//	storeInfo.setCreateTime(new Date());
//	return storeInfoDao.save(storeInfo);
//}
//
//@Override
//public int update(StoreInfoDO storeInfo){
//	storeInfo.setLastTime(new Date());
//	return storeInfoDao.update(storeInfo);
//}
//
//@Override
//public int remove(String id){
//	return storeInfoDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return storeInfoDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<StoreInfoDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (StoreInfoDO storeInfo : list) {
			i++;
			if (StringUtils.isEmpty(storeInfo.getId())) {
				storeInfo.setCreator(loginInfo.getId() + "");
				storeInfo.setCreatorby(loginInfo.getUsername());
				storeInfo.setCreatorby(loginInfo.getName());
				storeInfo.setCreateDeptid(loginInfo.getDeptId() + "");
				storeInfo.setCreateDeptcode(loginInfo.getDeptId() + "");
				storeInfo.setCreateDeptname(loginInfo.getDeptName());
				save(storeInfo);
			} else {
				storeInfo.setUpdator(loginInfo.getId() + "");
				storeInfo.setUpdatorby(loginInfo.getUsername());
				storeInfo.setUpdatorby(loginInfo.getName());
				update(storeInfo);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
