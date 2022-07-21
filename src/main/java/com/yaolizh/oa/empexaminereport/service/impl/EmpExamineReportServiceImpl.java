package com.yaolizh.oa.empexaminereport.service.impl;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.empexaminereport.domain.EmpExamineReportDO;
import com.yaolizh.oa.empexaminereport.service.EmpExamineReportService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmpExamineReportServiceImpl  extends BaseServiceImpl<EmpExamineReportDO> implements EmpExamineReportService {
	//@Autowired
//private EmpExamineReportDao empExamineReportDao;
//
//@Override
//public EmpExamineReportDO get(String id){
//	return empExamineReportDao.get(id);
//}
//
//@Override
//public List<EmpExamineReportDO> list(Map<String, Object> map){
//	return empExamineReportDao.list(map);
//}
//
//
//@Override
//public int save(EmpExamineReportDO empExamineReport){
//	if(StringUtils.isEmpty(empExamineReport.getId())){
//		empExamineReport.setId(UuidUtil.getUUIDfor32());
//	}
//	empExamineReport.setCreateTime(new Date());
//	return empExamineReportDao.save(empExamineReport);
//}
//
//@Override
//public int update(EmpExamineReportDO empExamineReport){
//	empExamineReport.setLastTime(new Date());
//	return empExamineReportDao.update(empExamineReport);
//}
//
//@Override
//public int remove(String id){
//	return empExamineReportDao.remove(id);
//}
//
//@Override
//public int batchRemove(String[] ids){
//	return empExamineReportDao.batchRemove(ids);
//}
	@Override
	public void saveImportExcel(List<EmpExamineReportDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (EmpExamineReportDO empExamineReport : list) {
			i++;
			if (StringUtils.isEmpty(empExamineReport.getId())) {
				empExamineReport.setCreator(loginInfo.getId() + "");
				empExamineReport.setCreatorby(loginInfo.getUsername());
				empExamineReport.setCreatorby(loginInfo.getName());
				empExamineReport.setCreateDeptid(loginInfo.getDeptId() + "");
				empExamineReport.setCreateDeptcode(loginInfo.getDeptId() + "");
				empExamineReport.setCreateDeptname(loginInfo.getDeptName());
				save(empExamineReport);
			} else {
				empExamineReport.setUpdator(loginInfo.getId() + "");
				empExamineReport.setUpdatorby(loginInfo.getUsername());
				empExamineReport.setUpdatorby(loginInfo.getName());
				update(empExamineReport);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}
}
