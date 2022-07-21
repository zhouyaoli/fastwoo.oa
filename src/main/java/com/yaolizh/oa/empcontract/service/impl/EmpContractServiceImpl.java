package com.yaolizh.oa.empcontract.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.empcontract.domain.EmpContractDO;
import com.yaolizh.oa.empcontract.service.EmpContractService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmpContractServiceImpl  extends BaseServiceImpl<EmpContractDO> implements EmpContractService {
	//@Autowired
//private EmpContractDao empContractDao;
//
//@Override
//public EmpContractDO get(String id){
//	return empContractDao.get(id);
//}
//
//@Override
//public List<EmpContractDO> list(Map<String, Object> map){
//	return empContractDao.list(map);
//}
//
//
//@Override
//public int save(EmpContractDO empContract){
//	if(StringUtils.isEmpty(empContract.getId())){
//		empContract.setId(UuidUtil.getUUIDfor32());
//	}
//	empContract.setCreateTime(new Date());
//	return empContractDao.save(empContract);
//}
//
//@Override
//public int update(EmpContractDO empContract){
//	empContract.setLastTime(new Date());
//	return empContractDao.update(empContract);
//}
//
//@Override
//public int remove(String id){
//	return empContractDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return empContractDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<EmpContractDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (EmpContractDO empContract : list) {
			i++;
			if (StringUtils.isEmpty(empContract.getId())) {
				empContract.setCreator(loginInfo.getId() + "");
				empContract.setCreatorby(loginInfo.getUsername());
				empContract.setCreatorby(loginInfo.getName());
				empContract.setCreateDeptid(loginInfo.getDeptId() + "");
				empContract.setCreateDeptcode(loginInfo.getDeptId() + "");
				empContract.setCreateDeptname(loginInfo.getDeptName());
				save(empContract);
			} else {
				empContract.setUpdator(loginInfo.getId() + "");
				empContract.setUpdatorby(loginInfo.getUsername());
				empContract.setUpdatorby(loginInfo.getName());
				update(empContract);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
