package com.yaolizh.oa.project.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.yaolizh.fastwoo.common.exception.MyException;
import com.yaolizh.fastwoo.common.service.impl.BaseServiceImpl;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.constant.BusinessConstant.ProjectState;
import com.yaolizh.oa.project.domain.ProjectDO;
import com.yaolizh.oa.project.service.ProjectService;

@Transactional
@Service
public class ProjectServiceImpl extends BaseServiceImpl<ProjectDO> implements ProjectService {

	@Override
	public ProjectDO save(ProjectDO project) {
		if (hasExistsNo(project.getNo(), project.getId())) {
			throw new MyException("项目编号也存在");
		}
		return super.save(project);
	}

	

	@Override
	public ProjectDO update(ProjectDO project) {
		if (hasExistsNo(project.getNo(), project.getId())) {
			throw new MyException("项目编号也存在");
		}
		project.setLastTime(new Date());
		return super.update(project);
	}

	
	

	@Override
	public void saveImportExcel(List<ProjectDO> list, UserDO loginInfo, HttpServletRequest request) {
		int i = 0;
		for (ProjectDO project : list) {
			i++;
			if (StringUtils.isEmpty(project.getId())) {
				project.setCreator(loginInfo.getId() + "");
				project.setCreatorby(loginInfo.getUsername());
				project.setCreatorby(loginInfo.getName());
				project.setCreateDeptid(loginInfo.getDeptId() + "");
				project.setCreateDeptcode(loginInfo.getDeptId() + "");
				project.setCreateDeptname(loginInfo.getDeptName());
				save(project);
			} else {
				project.setUpdator(loginInfo.getId() + "");
				project.setUpdatorby(loginInfo.getUsername());
				project.setUpdatorby(loginInfo.getName());
				update(project);
			}
			HttpSession session = request.getSession();
			session.setAttribute("upload_msg", "成功导入" + i + "行数据");
		}
	}

	@Override
	public ProjectDO stand(String id) {
		ProjectDO project = get(id);
		if (null == project) {
			throw new MyException("参数不对");
		}
		if (ProjectState.stand.getKey() <= project.getState()) {
			throw new MyException("当前项目不在未立项状态，请刷新项目重试");
		}
		project.setState(ProjectState.stand.getKey());
		this.update(project);
		return project;
	}

	@Override
	public ProjectDO close(String id) {
		ProjectDO project = get(id);
		if (null == project) {
			throw new MyException("参数不对");
		}
		if (ProjectState.close.getKey() <= project.getState()) {
			throw new MyException("当前项目不在未结束状态，请刷新项目重试");
		}
		project.setState(ProjectState.close.getKey());
		this.update(project);
		return project;
	}
	
	/** 
	 * @Title: hasExistsNo 
	 * @Description: (这里用一句话描述这个方法的作用) 
	 * @param no
	 * @param id
	 * @return    参数说明 
	 * @return boolean    返回类型 
	 * @throws 
	 */
	private boolean hasExistsNo(String no, String id) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("no", no);
		if (StringUtils.isNotEmpty(id)) {
			params.put("id", id);
		}
		return exit(params);

	}
}
