package com.yaolizh.oa.fixedresourceusehistory.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.fixedresourceusehistory.domain.FixedResourceUseHistoryDO;
import com.yaolizh.oa.fixedresourceusehistory.service.FixedResourceUseHistoryService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FixedResourceUseHistoryServiceImpl  extends BaseServiceImpl<FixedResourceUseHistoryDO> implements FixedResourceUseHistoryService {
	//@Autowired
//private FixedResourceUseHistoryDao fixedResourceUseHistoryDao;
//
//@Override
//public FixedResourceUseHistoryDO get(String id){
//	return fixedResourceUseHistoryDao.get(id);
//}
//
//@Override
//public List<FixedResourceUseHistoryDO> list(Map<String, Object> map){
//	return fixedResourceUseHistoryDao.list(map);
//}
//
//
//@Override
//public int save(FixedResourceUseHistoryDO fixedResourceUseHistory){
//	if(StringUtils.isEmpty(fixedResourceUseHistory.getId())){
//		fixedResourceUseHistory.setId(UuidUtil.getUUIDfor32());
//	}
//	fixedResourceUseHistory.setCreateTime(new Date());
//	return fixedResourceUseHistoryDao.save(fixedResourceUseHistory);
//}
//
//@Override
//public int update(FixedResourceUseHistoryDO fixedResourceUseHistory){
//	fixedResourceUseHistory.setLastTime(new Date());
//	return fixedResourceUseHistoryDao.update(fixedResourceUseHistory);
//}
//
//@Override
//public int remove(String id){
//	return fixedResourceUseHistoryDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return fixedResourceUseHistoryDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<FixedResourceUseHistoryDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (FixedResourceUseHistoryDO fixedResourceUseHistory : list) {
			i++;
			if (StringUtils.isEmpty(fixedResourceUseHistory.getId())) {
				fixedResourceUseHistory.setCreator(loginInfo.getId() + "");
				fixedResourceUseHistory.setCreatorby(loginInfo.getUsername());
				fixedResourceUseHistory.setCreatorby(loginInfo.getName());
				fixedResourceUseHistory.setCreateDeptid(loginInfo.getDeptId() + "");
				fixedResourceUseHistory.setCreateDeptcode(loginInfo.getDeptId() + "");
				fixedResourceUseHistory.setCreateDeptname(loginInfo.getDeptName());
				save(fixedResourceUseHistory);
			} else {
				fixedResourceUseHistory.setUpdator(loginInfo.getId() + "");
				fixedResourceUseHistory.setUpdatorby(loginInfo.getUsername());
				fixedResourceUseHistory.setUpdatorby(loginInfo.getName());
				update(fixedResourceUseHistory);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
