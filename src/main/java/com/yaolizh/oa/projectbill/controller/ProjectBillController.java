package com.yaolizh.oa.projectbill.controller;

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
import com.yaolizh.oa.projectbill.domain.ProjectBillDO;
import com.yaolizh.oa.projectbill.service.ProjectBillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 开票情况信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:56
 */
@Api(value="开票情况信息") 
@Controller
@RequestMapping("/oa/projectBill")
public class ProjectBillController extends BaseController {
	@Autowired
	private ProjectBillService projectBillService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:projectBill:projectBill")
	String ProjectBill(){
	    return "oa/projectBill/projectBill";
	}
	/**
	 * 列表数据查询接口
	 * @param params Map<String, Object> 查询参数
	 * @return
	 */
	 @ApiOperation(value="列表数据查询接口", notes="列表数据查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "查询参数", required = false, dataType = "Map<String, Object>")
    })
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:projectBill:projectBill")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<ProjectBillDO> projectBillList = (Page<ProjectBillDO>) projectBillService.list(query);
		
		PageUtils pageUtils = new PageUtils(projectBillList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:projectBill:add")
	String add(){
	    return "oa/projectBill/add";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:projectBill:edit")
	String edit(@PathVariable("id") String id,Model model){
		ProjectBillDO projectBill = projectBillService.get(id);
		model.addAttribute("projectBill", projectBill);
	    return "oa/projectBill/edit";
	}
	
	/**
	 * 新增保存接口
	 * @param projectBill  ProjectBillDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectBill", value = "保存实体信息", required = true, dataType = "ProjectBillDO")
    })
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:projectBill:add")
	public R save( ProjectBillDO projectBill){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			projectBill.setCreator(loginInfo.getId());
			projectBill.setCreatorby(loginInfo.getUsername());
			projectBill.setCreatorName(loginInfo.getName());
			projectBill.setCreateDeptid(loginInfo.getDeptId());
			projectBill.setCreateDeptcode(loginInfo.getDeptId());
			projectBill.setCreateDeptname(loginInfo.getDeptName());
			projectBill.setCreateOrgid(null);
			projectBill.setCreateOrgcode(null);
			projectBill.setCreateOrgname(null);
		}
		projectBill.setIsdelete(0);
		projectBill.setCreateTime(new Date());
		if(projectBillService.save(projectBill)>0){
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
	@ApiOperation(value="数据导入保存接口", notes="数据导入保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "excel文档", required = true, dataType = "MultipartFile")
    })
	@SuppressWarnings("resource")
	@ResponseBody
	@RequestMapping("/saveImportExcel")
	@RequiresPermissions("oa:projectBill:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<ProjectBillDO> list = new ArrayList<ProjectBillDO>();
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
				ProjectBillDO projectBill;
				boolean hv = true;
				int cellNum=0;
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
					cellNum=0;
					Cell noNullCell = row.getCell(cellNum++);
					if (null == noNullCell) {
						continue;
					}
					HttpSession session = request.getSession();
					session.setAttribute("upload_msg","正在校验"+r+"行数据");
					noNullCell.setCellType(CellType.STRING);
					String noNullName = noNullCell.getStringCellValue();

					if (StringUtils.isEmpty(noNullName)) {
						continue;
					}

						
					  					  						
					  						 /** 开票时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String billTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(billTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,开票时间未填写)");
						} 
					  						
					  						 /** 项目id */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String projectId = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(projectId)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,项目id未填写)");
						} 
					  						
					  						 /** 项目名称 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String projectName = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(projectName)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,项目名称未填写)");
						} 
					  						
					  						 /** 客户名称 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String clientName = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(clientName)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,客户名称未填写)");
						} 
					  						
					  						 /** 客户识别号 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String identifierNo = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(identifierNo)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,客户识别号未填写)");
						} 
					  						
					  						 /** 发票代码 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String billCode = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(billCode)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,发票代码未填写)");
						} 
					  						
					  						 /** 发票号码 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String billNo = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(billNo)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,发票号码未填写)");
						} 
					  						
					  						 /** 开票内容 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String content = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(content)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,开票内容未填写)");
						} 
					  						
					  						 /** 不含税金额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String noTaxAmount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(noTaxAmount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,不含税金额未填写)");
						} 
					  						
					  						 /** 税额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String taxAmount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(taxAmount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,税额未填写)");
						} 
					  						
					  						 /** 价税合计 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String totalAmount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(totalAmount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,价税合计未填写)");
						} 
					  						
					  						 /** 税率 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String taxRate = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(taxRate)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,税率未填写)");
						} 
					  						
					  						 /** 发票类型 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String type = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(type)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,发票类型未填写)");
						} 
					  						
					  						 /** 开票人 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String billPerson = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(billPerson)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,开票人未填写)");
						} 
					  						
					  						 /** 状态(1新开，2作废) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String state = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(state)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,状态(1新开，2作废)未填写)");
						} 
					  						
					  						 /** 作废人 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String crapPerson = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(crapPerson)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,作废人未填写)");
						} 
					  						
					  						 /** 作废时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String crapTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(crapTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,作废时间未填写)");
						} 
					  						
					  						 /** 作废原因 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String crapReason = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(crapReason)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,作废原因未填写)");
						} 
					  					
					 
					projectBill = new ProjectBillDO();
					//projectBill.setName(noNullName);

					//projectBill = projectBillService.find(projectBill);
					if (null == projectBill) {
						projectBill = new ProjectBillDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：开票时间
						 */
						 
						 						 	 projectBill.setBillTime(DateUtils.stringToDate(billTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：项目id
						 */
						 
						 							 projectBill.setProjectId(projectId)  ;
						 						
						 
					  						
					  						/**
						 * 设置：项目名称
						 */
						 
						 							 projectBill.setProjectName(projectName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：客户名称
						 */
						 
						 							 projectBill.setClientName(clientName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：客户识别号
						 */
						 
						 							 projectBill.setIdentifierNo(identifierNo)  ;
						 						
						 
					  						
					  						/**
						 * 设置：发票代码
						 */
						 
						 							 projectBill.setBillCode(billCode)  ;
						 						
						 
					  						
					  						/**
						 * 设置：发票号码
						 */
						 
						 							 projectBill.setBillNo(billNo)  ;
						 						
						 
					  						
					  						/**
						 * 设置：开票内容
						 */
						 
						 							 projectBill.setContent(content)  ;
						 						
						 
					  						
					  						/**
						 * 设置：不含税金额
						 */
						 
						 							 projectBill.setNoTaxAmount(new BigDecimal(noTaxAmount))  ;
						 						
						 
					  						
					  						/**
						 * 设置：税额
						 */
						 
						 							 projectBill.setTaxAmount(new BigDecimal(taxAmount))  ;
						 						
						 
					  						
					  						/**
						 * 设置：价税合计
						 */
						 
						 							 projectBill.setTotalAmount(new BigDecimal(totalAmount))  ;
						 						
						 
					  						
					  						/**
						 * 设置：税率
						 */
						 
						 							 projectBill.setTaxRate(new BigDecimal(taxRate))  ;
						 						
						 
					  						
					  						/**
						 * 设置：发票类型
						 */
						 
						 							 projectBill.setType(type)  ;
						 						
						 
					  						
					  						/**
						 * 设置：开票人
						 */
						 
						 							 projectBill.setBillPerson(billPerson)  ;
						 						
						 
					  						
					  						/**
						 * 设置：状态(1新开，2作废)
						 */
						 
						 						 	 projectBill.setState(Integer.parseInt(state))  ;
						 						
						 
					  						
					  						/**
						 * 设置：作废人
						 */
						 
						 							 projectBill.setCrapPerson(crapPerson)  ;
						 						
						 
					  						
					  						/**
						 * 设置：作废时间
						 */
						 
						 						 	 projectBill.setCrapTime(DateUtils.stringToDate(crapTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：作废原因
						 */
						 
						 							 projectBill.setCrapReason(crapReason)  ;
						 						
						 
					  					
					projectBill.setCreateTime(new Date());
					projectBill.setIsdelete(0);

					list.add(projectBill);
				}
				UserDO loginInfo = super.getLoginUser();
				projectBillService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	/**
	 * 修改保存接口
	 * @param projectBill  ProjectBillDO
	 * @return
	 */
	 @ApiOperation(value="修改保存接口", notes="修改保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectBill", value = "保存实体信息", required = true, dataType = "ProjectBillDO")
    })
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:projectBill:edit")
	public R update( ProjectBillDO projectBill){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			projectBill.setUpdator(loginInfo.getId());
			projectBill.setUpdatorby(loginInfo.getUsername());
			projectBill.setUpdatorName(loginInfo.getName());
		}
		projectBill.setIsdelete(0);
		projectBill.setLastTime(new Date());
		projectBillService.update(projectBill);
		return R.ok();
	}
	
	/**
	 * 根据主键删除数据接口
	 * @param id String 主键 
	 * @return
	 */
	  @ApiOperation(value="根据主键删除数据接口", notes="根据主键删除数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "String")
    })
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("oa:projectBill:remove")
	public R remove( String id){
		if(projectBillService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 批量删除数据接口
	 * @param ids String[] 主键
	 * @return
	 */
	@ApiOperation(value="批量删除数据接口", notes="批量删除数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键", required = true, dataType = "String[]")
    })
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:projectBill:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		projectBillService.batchRemove(ids);
		return R.ok();
	}
	
}
