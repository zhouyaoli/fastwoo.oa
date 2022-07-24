package com.yaolizh.oa.project.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.yaolizh.fastwoo.common.controller.BaseController;
import com.yaolizh.fastwoo.common.utils.DateUtils;
import com.yaolizh.fastwoo.common.utils.PageUtils;
import com.yaolizh.fastwoo.common.utils.Query;
import com.yaolizh.fastwoo.common.utils.R;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.constant.BusinessConstant.ProjectState;
import com.yaolizh.oa.project.domain.ProjectDO;
import com.yaolizh.oa.project.service.ProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 项目信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:54
 */
@Api(value = "项目信息")
@Controller
@RequestMapping("/oa/project")
public class ProjectController extends BaseController {
	@Autowired
	private ProjectService projectService;

	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value = "进入列表页面", notes = "进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:project:project")
	String Project() {
		return "oa/project/project";
	}

	/**
	 * 列表数据查询接口
	 * @param params Map<String, Object> 查询参数
	 * @return
	 */
	@ApiOperation(value = "列表数据查询接口", notes = "列表数据查询接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map<String, Object>") })
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:project:project")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
		Page<ProjectDO> projectList = (Page<ProjectDO>) projectService.list(query);

		PageUtils pageUtils = new PageUtils(projectList);
		return pageUtils;
	}

	/**
	 * 去新增数据页面
	 * @return
	 */
	@ApiOperation(value = "去新增数据页面", notes = "去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:project:add")
	String add(Model model) {
		ProjectState[] states = ProjectState.values();
		model.addAttribute("states", states);
		return "oa/project/add";
	}

	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	@ApiOperation(value = "去修改数据页面", notes = "去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:project:edit")
	String edit(@PathVariable("id") String id, Model model) {
		ProjectState[] states = ProjectState.values();
		model.addAttribute("states", states);
		ProjectDO project = projectService.get(id);
		model.addAttribute("project", project);
		return "oa/project/edit";
	}

	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	@ApiOperation(value = "去选择项目数据页面", notes = "去选择项目数据页面")
	@GetMapping("/selectProjectWin")
	@RequiresAuthentication
	String selectProjectWin(Model model) {
		return "oa/project/selectProjectWin";
	}

	/**
	 * 列表数据查询接口
	 * @param params Map<String, Object> 查询参数
	 * @return
	 */
	@ApiOperation(value = "选择项目数据查询接口", notes = "选择项目数据查询接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map<String, Object>") })
	@ResponseBody
	@GetMapping("/selectProjectWinList")
	@RequiresAuthentication
	public PageUtils selectProjectWinList(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
		Page<ProjectDO> projectList = (Page<ProjectDO>) projectService.list(query);
		PageUtils pageUtils = new PageUtils(projectList);
		return pageUtils;
	}

	/**
	 * 新增保存接口
	 * @param project  ProjectDO
	 * @return
	 */
	@ApiOperation(value = "新增保存接口", notes = "新增保存接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "project", value = "保存实体信息", required = true, dataType = "ProjectDO") })
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:project:add")
	public R save(ProjectDO project) {
		UserDO loginInfo = super.getLoginUser();
		if (null != loginInfo) {
			project.setCreator(loginInfo.getId());
			project.setCreatorby(loginInfo.getUsername());
			project.setCreatorName(loginInfo.getName());
			project.setCreateDeptid(loginInfo.getDeptId());
			project.setCreateDeptcode(loginInfo.getDeptId());
			project.setCreateDeptname(loginInfo.getDeptName());
			project.setCreateOrgid(null);
			project.setCreateOrgcode(null);
			project.setCreateOrgname(null);
		}
		project.setIsdelete(0);
		project.setCreateTime(new Date());
		projectService.save(project);
		return R.ok();
	}

	/**
	 * 修改保存接口
	 * @param project  ProjectDO
	 * @return
	 */
	@ApiOperation(value = "修改保存接口", notes = "修改保存接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "project", value = "保存实体信息", required = true, dataType = "ProjectDO") })
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:project:edit")
	public R update(ProjectDO project) {
		UserDO loginInfo = super.getLoginUser();
		if (null != loginInfo) {
			project.setUpdator(loginInfo.getId());
			project.setUpdatorby(loginInfo.getUsername());
			project.setUpdatorName(loginInfo.getName());
		}
		project.setIsdelete(0);
		project.setLastTime(new Date());
		projectService.update(project);
		return R.ok();
	}

	/**
	 * 根据主键删除数据接口
	 * @param id String 主键 
	 * @return
	 */
	@ApiOperation(value = "根据主键删除数据接口", notes = "根据主键删除数据接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String") })
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("oa:project:remove")
	public R remove(String id) {
		if (projectService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 项目立项接口
	 * @param id String 主键 
	 * @return
	 */
	@ApiOperation(value = "项目立项接口", notes = "项目立项接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String") })
	@PostMapping("/stand")
	@ResponseBody
	@RequiresPermissions("oa:project:stand")
	public R stand(String id) {
		return R.ok().setData(projectService.stand(id));
	}

	/**
	 * 项目结束接口
	 * @param id String 主键 
	 * @return
	 */
	@ApiOperation(value = "项目结束接口", notes = "项目结束接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String") })
	@PostMapping("/close")
	@ResponseBody
	@RequiresPermissions("oa:project:close")
	public R close(String id) {
		return R.ok().setData(projectService.close(id));
	}

	/**
	 * 批量删除数据接口
	 * @param ids String[] 主键
	 * @return
	 */
	@ApiOperation(value = "批量删除数据接口", notes = "批量删除数据接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ids", value = "主键", required = true, dataType = "String[]") })
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:project:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids) {
		projectService.batchRemove(ids);
		return R.ok();
	}

	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !projectService.exit(params);
	}

	/**
	 * 数据导入保存接口
	 * @param file 上传excel文件
	 * @param request 请求体
	 * @return
	 */
	@ApiOperation(value = "数据导入保存接口", notes = "数据导入保存接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "file", value = "excel文档", required = true, dataType = "MultipartFile") })
	@SuppressWarnings("resource")
	@ResponseBody
	@RequestMapping("/saveImportExcel")
	@RequiresPermissions("oa:project:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<ProjectDO> list = new ArrayList<ProjectDO>();
			if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
				throw new RuntimeException("上传文件格式不正确");
			}
			boolean isExcel2003 = true;
			if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
				isExcel2003 = false;
			}
			InputStream is;

			is = file.getInputStream();

			Workbook wb = null;
			if (isExcel2003) {
				wb = new HSSFWorkbook(is);
			} else {
				wb = new XSSFWorkbook(is);
			}
			Sheet sheet = wb.getSheetAt(0);
			if (sheet != null) {

				String[] heads = new String[] { "专业", "学科简介", "学习门槛", "就业方向", "院校推荐" };
				ProjectDO project;
				boolean hv = true;
				int cellNum = 0;
				for (int r = 0; r <= sheet.getLastRowNum(); r++) {

					Row row = sheet.getRow(r);
					if (row == null) {
						continue;
					}
					if (r == 0) {
						for (int h = 0; h < heads.length; h++) {
							row.getCell(h).setCellType(CellType.STRING);
							hv = StringUtils.equals(row.getCell(h).getStringCellValue(), heads[h]);
						}
					}
					if (!hv) {
						String hs = "";
						for (String str : heads) {
							if (StringUtils.isNotEmpty(hs)) {
								hs += ",";
							}
							hs += str;
						}
						throw new RuntimeException("第一行的为标表头数据，且顺序不可以更改，顺序为:" + hs + "");
					}
					if (r == 0) {
						continue;
					}
					cellNum = 0;
					Cell noNullCell = row.getCell(cellNum++);
					if (null == noNullCell) {
						continue;
					}
					HttpSession session = request.getSession();
					session.setAttribute("upload_msg", "正在校验" + r + "行数据");
					noNullCell.setCellType(CellType.STRING);
					String noNullName = noNullCell.getStringCellValue();

					if (StringUtils.isEmpty(noNullName)) {
						continue;
					}

					/** 项目编号 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String no = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(no)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,项目编号未填写)");
					}

					/** 项目名称 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String name = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(name)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,项目名称未填写)");
					}

					/** 状态(1:未立项，2立项成立，4：结束) */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String state = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(state)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,状态(1:未立项，2立项成立，4：结束)未填写)");
					}

					/** 项目金额 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String amount = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(amount)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,项目金额未填写)");
					}

					/** 已收款金额 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String inAmount = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(inAmount)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,已收款金额未填写)");
					}

					/** 实际已支出成本 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String outAmount = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(outAmount)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,实际已支出成本未填写)");
					}

					/** 预算成本 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String planOutAmount = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(planOutAmount)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,预算成本未填写)");
					}

					/** 进度情况 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String progress = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(progress)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,进度情况未填写)");
					}

					/** 项目计划开始日期 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String planBeginDate = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(planBeginDate)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,项目计划开始日期未填写)");
					}

					/** 项目计划完成日期 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String planEndDate = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(planEndDate)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,项目计划完成日期未填写)");
					}

					/** 项目实际完成日期 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String endDate = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(endDate)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,项目实际完成日期未填写)");
					}

					project = new ProjectDO();
					//project.setName(noNullName);

					project = projectService.findOne(project);
					if (null == project) {
						project = new ProjectDO();
					}

					/**
					* 设置：项目编号
					*/

					project.setNo(no);

					/**
					* 设置：项目名称
					*/

					project.setName(name);

					/**
					* 设置：状态(1:未立项，2立项成立，4：结束)
					*/

					project.setState(Integer.parseInt(state));

					/**
					* 设置：项目金额
					*/

					project.setAmount(new BigDecimal(amount));

					/**
					* 设置：已收款金额
					*/

					project.setInAmount(new BigDecimal(inAmount));

					/**
					* 设置：实际已支出成本
					*/

					project.setOutAmount(new BigDecimal(outAmount));

					/**
					* 设置：预算成本
					*/

					project.setPlanOutAmount(new BigDecimal(planOutAmount));

					/**
					* 设置：进度情况
					*/

					project.setProgress(progress);

					/**
					* 设置：项目计划开始日期
					*/

					project.setPlanBeginDate(DateUtils.stringToDate(planBeginDate));

					/**
					* 设置：项目计划完成日期
					*/

					project.setPlanEndDate(DateUtils.stringToDate(planEndDate));

					/**
					* 设置：项目实际完成日期
					*/

					project.setEndDate(DateUtils.stringToDate(endDate));

					project.setCreateTime(new Date());
					project.setIsdelete(0);

					list.add(project);
				}
				UserDO loginInfo = super.getLoginUser();
				projectService.saveImportExcel(list, loginInfo, request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage());
		}
		return R.ok("导入成功");
	}
}
