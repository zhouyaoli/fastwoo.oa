package com.yaolizh.oa.fixedresourcerepairhistory.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.fixedresourcerepairhistory.domain.FixedResourceRepairHistoryDO;
import com.yaolizh.oa.fixedresourcerepairhistory.service.FixedResourceRepairHistoryService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FixedResourceRepairHistoryServiceImpl  extends BaseServiceImpl<FixedResourceRepairHistoryDO> implements FixedResourceRepairHistoryService {
	//@Autowired
//private FixedResourceRepairHistoryDao fixedResourceRepairHistoryDao;
//
//@Override
//public FixedResourceRepairHistoryDO get(String id){
//	return fixedResourceRepairHistoryDao.get(id);
//}
//
//@Override
//public List<FixedResourceRepairHistoryDO> list(Map<String, Object> map){
//	return fixedResourceRepairHistoryDao.list(map);
//}
//
//
//@Override
//public int save(FixedResourceRepairHistoryDO fixedResourceRepairHistory){
//	if(StringUtils.isEmpty(fixedResourceRepairHistory.getId())){
//		fixedResourceRepairHistory.setId(UuidUtil.getUUIDfor32());
//	}
//	fixedResourceRepairHistory.setCreateTime(new Date());
//	return fixedResourceRepairHistoryDao.save(fixedResourceRepairHistory);
//}
//
//@Override
//public int update(FixedResourceRepairHistoryDO fixedResourceRepairHistory){
//	fixedResourceRepairHistory.setLastTime(new Date());
//	return fixedResourceRepairHistoryDao.update(fixedResourceRepairHistory);
//}
//
//@Override
//public int remove(String id){
//	return fixedResourceRepairHistoryDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return fixedResourceRepairHistoryDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<FixedResourceRepairHistoryDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (FixedResourceRepairHistoryDO fixedResourceRepairHistory : list) {
			i++;
			if (StringUtils.isEmpty(fixedResourceRepairHistory.getId())) {
				fixedResourceRepairHistory.setCreator(loginInfo.getId() + "");
				fixedResourceRepairHistory.setCreatorby(loginInfo.getUsername());
				fixedResourceRepairHistory.setCreatorby(loginInfo.getName());
				fixedResourceRepairHistory.setCreateDeptid(loginInfo.getDeptId() + "");
				fixedResourceRepairHistory.setCreateDeptcode(loginInfo.getDeptId() + "");
				fixedResourceRepairHistory.setCreateDeptname(loginInfo.getDeptName());
				save(fixedResourceRepairHistory);
			} else {
				fixedResourceRepairHistory.setUpdator(loginInfo.getId() + "");
				fixedResourceRepairHistory.setUpdatorby(loginInfo.getUsername());
				fixedResourceRepairHistory.setUpdatorby(loginInfo.getName());
				update(fixedResourceRepairHistory);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
