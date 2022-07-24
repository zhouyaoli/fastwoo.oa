package com.yaolizh.oa.projectcontract.controller;

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
import org.springframework.web.multipart.MultipartFile;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.common.utils.DateUtils;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.yaolizh.fastwoo.common.utils.PageUtils;
import com.yaolizh.fastwoo.common.controller.BaseController;
import com.yaolizh.fastwoo.common.utils.Query;
import com.yaolizh.fastwoo.common.utils.R;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.constant.BusinessConstant.BillType;
import com.yaolizh.oa.constant.BusinessConstant.ContractPayWay;
import com.yaolizh.oa.constant.BusinessConstant.ContractType;
import com.yaolizh.oa.constant.BusinessConstant.ProjectState;
import com.yaolizh.oa.projectcontract.domain.ProjectContractDO;
import com.yaolizh.oa.projectcontract.service.ProjectContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;

/**
 * 合同信息（附件：中标通知书）
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 17:13:51
 */
@Api(value = "合同信息（附件：中标通知书）")
@Controller
@RequestMapping("/oa/projectContract")
public class ProjectContractController extends BaseController {
	@Autowired
	private ProjectContractService projectContractService;

	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value = "进入列表页面", notes = "进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:projectContract:projectContract")
	String ProjectContract() {
		return "oa/projectContract/projectContract";
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
	@RequiresPermissions("oa:projectContract:projectContract")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
		Page<ProjectContractDO> projectContractList = (Page<ProjectContractDO>) projectContractService.list(query);

		PageUtils pageUtils = new PageUtils(projectContractList);
		return pageUtils;
	}

	/**
	 * 去新增数据页面
	 * @return
	 */
	@ApiOperation(value = "去新增数据页面", notes = "去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:projectContract:add")
	String add(Model model) {
		ProjectContractDO projectContract = new ProjectContractDO();
		projectContract.setAmount(BigDecimal.ZERO);
		projectContract.setPayAmount(BigDecimal.ZERO);
		projectContract.setBillAmount(BigDecimal.ZERO);
		projectContract.setContractNum(1);
		projectContract.setBillType(BillType.normal.getKey());
		projectContract.setPayWay(ContractPayWay.full.getKey());
		projectContract.setType(ContractType.inPay.getKey());
		projectContract.setState(ProjectState.stand.getKey());
		model.addAttribute("states", ProjectState.values());
		model.addAttribute("billTypes", BillType.values());
		model.addAttribute("payWays", ContractPayWay.values());
		model.addAttribute("types", ContractType.values());
		model.addAttribute("projectContract", projectContract);
		return "oa/projectContract/addOrUpdate";
	}

	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	@ApiOperation(value = "去修改数据页面", notes = "去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:projectContract:edit")
	String edit(@PathVariable("id") String id, Model model) {
		ProjectContractDO projectContract = projectContractService.get(id);
		model.addAttribute("states", ProjectState.values());
		model.addAttribute("billTypes", BillType.values());
		model.addAttribute("payWays", ContractPayWay.values());
		model.addAttribute("types", ContractType.values());
		model.addAttribute("projectContract", projectContract);
		return "oa/projectContract/addOrUpdate";
	}

	/**
	 * 新增保存接口
	 * @param projectContract  ProjectContractDO
	 * @return
	 */
	@ApiOperation(value = " 保存接口", notes = " 保存接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectContract", value = "保存实体信息", required = true, dataType = "ProjectContractDO") })
	@ResponseBody
	@PostMapping("/saveOrUpdate")
	@RequiresPermissions(value = { "oa:projectContract:add", "oa:projectContract:edit" }, logical = Logical.OR)
	public R saveOrUpdate(ProjectContractDO projectContract) {
		projectContractService.saveOrUpdate(projectContract);
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
	@RequiresPermissions("oa:projectContract:remove")
	public R remove(String id) {
		if (projectContractService.remove(id) > 0) {
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
	@RequiresPermissions("oa:projectContract:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids) {
		projectContractService.batchRemove(ids);
		return R.ok();
	}

	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !projectContractService.exit(params);
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
	@RequiresPermissions("oa:projectContract:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<ProjectContractDO> list = new ArrayList<ProjectContractDO>();
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
				ProjectContractDO projectContract;
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

					/** 合同编号 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String no = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(no)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,合同编号未填写)");
					}

					/** 合同名称 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String name = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(name)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,合同名称未填写)");
					}

					/** 项目id */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String projectId = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(projectId)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,项目id未填写)");
					}

					/** 项目名称 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String projectName = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(projectName)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,项目名称未填写)");
					}

					/** 销售人员 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String person = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(person)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,销售人员未填写)");
					}

					/** 甲方公司 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String oneCompany = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(oneCompany)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,甲方公司未填写)");
					}

					/** 甲方负责人 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String oneLeader = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(oneLeader)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,甲方负责人未填写)");
					}

					/** 甲方联系人 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String onePhone = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(onePhone)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,甲方联系人未填写)");
					}

					/** 乙方名称（） */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String twoCompany = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(twoCompany)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,乙方名称（）未填写)");
					}

					/** 乙方负责人 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String twoLeader = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(twoLeader)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,乙方负责人未填写)");
					}

					/** 乙方联系电话 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String twoPhone = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(twoPhone)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,乙方联系电话未填写)");
					}

					/** 合同状态 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String state = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(state)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,合同状态未填写)");
					}

					/** 付款方式(1：阶段付款；2：比例付款；3：全额付款) */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String payWay = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(payWay)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,付款方式(1：阶段付款；2：比例付款；3：全额付款)未填写)");
					}

					/** 合同金额 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String amount = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(amount)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,合同金额未填写)");
					}

					/** 已(收/付)款金额 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String payAmount = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(payAmount)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,已(收/付)款金额未填写)");
					}

					/** 已开票金额 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String billAmount = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(billAmount)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,已开票金额未填写)");
					}

					/** 开票类型（1：增值税发票，2：普通发票） */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String billType = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(billType)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,开票类型（1：增值税发票，2：普通发票）未填写)");
					}

					/** 合同签署日期 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String signDate = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(signDate)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,合同签署日期未填写)");
					}

					/** 合同生效日期 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String validDate = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(validDate)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,合同生效日期未填写)");
					}

					/** 合同截止日期 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String limitDate = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(limitDate)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,合同截止日期未填写)");
					}

					/** 合同类型(1:收入合同，2:支出合同) */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String type = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(type)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,合同类型(1:收入合同，2:支出合同)未填写)");
					}

					/** 合同数 */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String contractNum = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(contractNum)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,合同数未填写)");
					}

					/**  */
					row.getCell(cellNum++).setCellType(CellType.STRING);
					String remark = row.getCell(cellNum - 1).getStringCellValue();
					if (StringUtils.isEmpty(remark)) {
						throw new RuntimeException("导入失败(第" + (r + 1) + "行,未填写)");
					}

					projectContract = new ProjectContractDO();
					//projectContract.setName(noNullName);

					projectContract = projectContractService.findOne(projectContract);
					if (null == projectContract) {
						projectContract = new ProjectContractDO();
					}

					/**
					* 设置：合同编号
					*/

					projectContract.setNo(no);

					/**
					* 设置：合同名称
					*/

					projectContract.setName(name);

					/**
					* 设置：项目id
					*/

					projectContract.setProjectId(projectId);

					/**
					* 设置：项目名称
					*/

					projectContract.setProjectName(projectName);

					/**
					* 设置：销售人员
					*/

					projectContract.setPerson(person);

					/**
					* 设置：甲方公司
					*/

					projectContract.setOneCompany(oneCompany);

					/**
					* 设置：甲方负责人
					*/

					projectContract.setOneLeader(oneLeader);

					/**
					* 设置：甲方联系人
					*/

					projectContract.setOnePhone(onePhone);

					/**
					* 设置：乙方名称（）
					*/

					projectContract.setTwoCompany(twoCompany);

					/**
					* 设置：乙方负责人
					*/

					projectContract.setTwoLeader(twoLeader);

					/**
					* 设置：乙方联系电话
					*/

					projectContract.setTwoPhone(twoPhone);

					/**
					* 设置：合同状态
					*/

					projectContract.setState(Integer.parseInt(state));

					/**
					* 设置：付款方式(1：阶段付款；2：比例付款；3：全额付款)
					*/

					projectContract.setPayWay(Integer.parseInt(payWay));

					/**
					* 设置：合同金额
					*/

					projectContract.setAmount(new BigDecimal(amount));

					/**
					* 设置：已(收/付)款金额
					*/

					projectContract.setPayAmount(new BigDecimal(payAmount));

					/**
					* 设置：已开票金额
					*/

					projectContract.setBillAmount(new BigDecimal(billAmount));

					/**
					* 设置：开票类型（1：增值税发票，2：普通发票）
					*/

					projectContract.setBillType(Integer.parseInt(billType));

					/**
					* 设置：合同签署日期
					*/

					projectContract.setSignDate(DateUtils.stringToDate(signDate));

					/**
					* 设置：合同生效日期
					*/

					projectContract.setValidDate(DateUtils.stringToDate(validDate));

					/**
					* 设置：合同截止日期
					*/

					projectContract.setLimitDate(DateUtils.stringToDate(limitDate));

					/**
					* 设置：合同类型(1:收入合同，2:支出合同)
					*/

					projectContract.setType(Integer.parseInt(type));

					/**
					* 设置：合同数
					*/

					projectContract.setContractNum(Integer.parseInt(contractNum));

					/**
					* 设置：
					*/

					projectContract.setRemark(remark);

					projectContract.setCreateTime(new Date());
					projectContract.setIsdelete(0);

					list.add(projectContract);
				}
				UserDO loginInfo = super.getLoginUser();
				projectContractService.saveImportExcel(list, loginInfo, request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage());
		}
		return R.ok("导入成功");
	}

}
