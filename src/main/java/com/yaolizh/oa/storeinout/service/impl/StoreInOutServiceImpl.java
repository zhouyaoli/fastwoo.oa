package com.yaolizh.oa.storeinout.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.storeinout.domain.StoreInOutDO;
import com.yaolizh.oa.storeinout.service.StoreInOutService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class StoreInOutServiceImpl  extends BaseServiceImpl<StoreInOutDO> implements StoreInOutService {
	//@Autowired
//private StoreInOutDao storeInOutDao;
//
//@Override
//public StoreInOutDO get(String id){
//	return storeInOutDao.get(id);
//}
//
//@Override
//public List<StoreInOutDO> list(Map<String, Object> map){
//	return storeInOutDao.list(map);
//}
//
//
//@Override
//public int save(StoreInOutDO storeInOut){
//	if(StringUtils.isEmpty(storeInOut.getId())){
//		storeInOut.setId(UuidUtil.getUUIDfor32());
//	}
//	storeInOut.setCreateTime(new Date());
//	return storeInOutDao.save(storeInOut);
//}
//
//@Override
//public int update(StoreInOutDO storeInOut){
//	storeInOut.setLastTime(new Date());
//	return storeInOutDao.update(storeInOut);
//}
//
//@Override
//public int remove(String id){
//	return storeInOutDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return storeInOutDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<StoreInOutDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (StoreInOutDO storeInOut : list) {
			i++;
			if (StringUtils.isEmpty(storeInOut.getId())) {
				storeInOut.setCreator(loginInfo.getId() + "");
				storeInOut.setCreatorby(loginInfo.getUsername());
				storeInOut.setCreatorby(loginInfo.getName());
				storeInOut.setCreateDeptid(loginInfo.getDeptId() + "");
				storeInOut.setCreateDeptcode(loginInfo.getDeptId() + "");
				storeInOut.setCreateDeptname(loginInfo.getDeptName());
				save(storeInOut);
			} else {
				storeInOut.setUpdator(loginInfo.getId() + "");
				storeInOut.setUpdatorby(loginInfo.getUsername());
				storeInOut.setUpdatorby(loginInfo.getName());
				update(storeInOut);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
