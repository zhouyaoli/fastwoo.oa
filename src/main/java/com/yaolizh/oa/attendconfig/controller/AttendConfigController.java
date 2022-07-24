package com.yaolizh.oa.attendconfig.controller;

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
import com.yaolizh.oa.attendconfig.domain.AttendConfigDO;
import com.yaolizh.oa.attendconfig.service.AttendConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 考勤配置信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:16:00
 */
@Api(value = "考勤配置信息")
@Controller
@RequestMapping("/oa/attendConfig")
public class AttendConfigController extends BaseController {
	@Autowired
	private AttendConfigService attendConfigService;

	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value = "进入列表页面", notes = "进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:attendConfig:attendConfig")
	String AttendConfig() {
		return "oa/attendConfig/attendConfig";
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
	@RequiresPermissions("oa:attendConfig:attendConfig")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
		Page<AttendConfigDO> attendConfigList = (Page<AttendConfigDO>) attendConfigService.list(query);

		PageUtils pageUtils = new PageUtils(attendConfigList);
		return pageUtils;
	}

	/**
	 * 去新增数据页面
	 * @return
	 */
	@ApiOperation(value = "去新增数据页面", notes = "去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:attendConfig:add")
	String add() {
		return "oa/attendConfig/add";
	}

	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	@ApiOperation(value = "去修改数据页面", notes = "去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:attendConfig:edit")
	String edit(@PathVariable("id") String id, Model model) {
		AttendConfigDO attendConfig = attendConfigService.get(id);
		model.addAttribute("attendConfig", attendConfig);
		return "oa/attendConfig/edit";
	}

	/**
	 * 新增保存接口
	 * @param attendConfig  AttendConfigDO
	 * @return
	 */
	@ApiOperation(value = "新增保存接口", notes = "新增保存接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "attendConfig", value = "保存实体信息", required = true, dataType = "AttendConfigDO") })
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:attendConfig:add")
	public R save(AttendConfigDO attendConfig) {
		UserDO loginInfo = super.getLoginUser();
		if (null != loginInfo) {
			attendConfig.setCreator(loginInfo.getId());
			attendConfig.setCreatorby(loginInfo.getUsername());
			attendConfig.setCreatorName(loginInfo.getName());
			attendConfig.setCreateDeptid(loginInfo.getDeptId());
			attendConfig.setCreateDeptcode(loginInfo.getDeptId());
			attendConfig.setCreateDeptname(loginInfo.getDeptName());
			attendConfig.setCreateOrgid(null);
			attendConfig.setCreateOrgcode(null);
			attendConfig.setCreateOrgname(null);
		}
		attendConfig.setIsdelete(0);
		attendConfig.setCreateTime(new Date());
		if (attendConfigService.save(attendConfig) > 0) {
			return R.ok();
		}
		return R.error();
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
	@RequiresPermissions("oa:attendConfig:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<AttendConfigDO> list = new ArrayList<AttendConfigDO>();
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
				AttendConfigDO attendConfig;
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

					/** 上班经度 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String workLng = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(workLng)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,上班经度未填写)");
					}

					/** 上班纬度 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String workLat = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(workLat)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,上班纬度未填写)");
					}

					/** 上班时间 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String goWorkTime = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(goWorkTime)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,上班时间未填写)");
					}

					/** 最迟上班时间 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String goWorkLimitTime = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(goWorkLimitTime)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,最迟上班时间未填写)");
					}

					/** 下班时间 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String offWorkTime = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(offWorkTime)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,下班时间未填写)");
					}

					attendConfig = new AttendConfigDO();
					//attendConfig.setName(noNullName);

					//attendConfig = attendConfigService.find(attendConfig);
					if (null == attendConfig) {
						attendConfig = new AttendConfigDO();
					}

					/**
					* 设置：上班经度
					*/

					attendConfig.setWorkLng(new BigDecimal(workLng));

					/**
					* 设置：上班纬度
					*/

					attendConfig.setWorkLat(new BigDecimal(workLat));

					/**
					* 设置：上班时间
					*/

					attendConfig.setGoWorkTime(DateUtils.stringToDate(goWorkTime));

					/**
					* 设置：最迟上班时间
					*/

					attendConfig.setGoWorkLimitTime(DateUtils.stringToDate(goWorkLimitTime));

					/**
					* 设置：下班时间
					*/

					attendConfig.setOffWorkTime(DateUtils.stringToDate(offWorkTime));

					attendConfig.setCreateTime(new Date());
					attendConfig.setIsdelete(0);

					list.add(attendConfig);
				}
				UserDO loginInfo = super.getLoginUser();
				attendConfigService.saveImportExcel(list, loginInfo, request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage());
		}
		return R.ok("导入成功");
	}

	/**
	 * 修改保存接口
	 * @param attendConfig  AttendConfigDO
	 * @return
	 */
	@ApiOperation(value = "修改保存接口", notes = "修改保存接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "attendConfig", value = "保存实体信息", required = true, dataType = "AttendConfigDO") })
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:attendConfig:edit")
	public R update(AttendConfigDO attendConfig) {
		UserDO loginInfo = super.getLoginUser();
		if (null != loginInfo) {
			attendConfig.setUpdator(loginInfo.getId());
			attendConfig.setUpdatorby(loginInfo.getUsername());
			attendConfig.setUpdatorName(loginInfo.getName());
		}
		attendConfig.setIsdelete(0);
		attendConfig.setLastTime(new Date());
		attendConfigService.update(attendConfig);
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
	@RequiresPermissions("oa:attendConfig:remove")
	public R remove(String id) {
		if (attendConfigService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
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
	@RequiresPermissions("oa:attendConfig:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids) {
		attendConfigService.batchRemove(ids);
		return R.ok();
	}

}
