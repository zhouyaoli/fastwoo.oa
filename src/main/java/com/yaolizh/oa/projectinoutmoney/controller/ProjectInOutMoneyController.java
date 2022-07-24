package com.yaolizh.oa.projectinoutmoney.controller;

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
import com.yaolizh.oa.projectinoutmoney.domain.ProjectInOutMoneyDO;
import com.yaolizh.oa.projectinoutmoney.service.ProjectInOutMoneyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 收支明细
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:00
 */
@Api(value="收支明细") 
@Controller
@RequestMapping("/oa/projectInOutMoney")
public class ProjectInOutMoneyController extends BaseController {
	@Autowired
	private ProjectInOutMoneyService projectInOutMoneyService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:projectInOutMoney:projectInOutMoney")
	String ProjectInOutMoney(){
	    return "oa/projectInOutMoney/projectInOutMoney";
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
	@RequiresPermissions("oa:projectInOutMoney:projectInOutMoney")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<ProjectInOutMoneyDO> projectInOutMoneyList = (Page<ProjectInOutMoneyDO>) projectInOutMoneyService.list(query);
		
		PageUtils pageUtils = new PageUtils(projectInOutMoneyList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:projectInOutMoney:add")
	String add(Model model){
		ProjectInOutMoneyDO projectInOutMoney = new ProjectInOutMoneyDO();
		model.addAttribute("projectInOutMoney", projectInOutMoney);
	    return "oa/projectInOutMoney/addOrUpdate";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:projectInOutMoney:edit")
	String edit(@PathVariable("id") String id,Model model){
		ProjectInOutMoneyDO projectInOutMoney = projectInOutMoneyService.get(id);
		model.addAttribute("projectInOutMoney", projectInOutMoney);
	    return "oa/projectInOutMoney/addOrUpdate";
	}
	
	/**
	 * 新增保存接口
	 * @param projectInOutMoney  ProjectInOutMoneyDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectInOutMoney", value = "保存实体信息", required = true, dataType = "ProjectInOutMoneyDO")
    })
	@ResponseBody
	@PostMapping("/saveOrUpdate")
	@RequiresPermissions( value={"oa:projectInOutMoney:add","oa:projectInOutMoney:edit"}, logical=Logical.OR)
	public R saveOrUpdate( ProjectInOutMoneyDO projectInOutMoney){
	UserDO loginInfo = super.getLoginUser();
		projectInOutMoneyService.saveOrUpdate(projectInOutMoney);
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
	@RequiresPermissions("oa:projectInOutMoney:remove")
	public R remove( String id){
		if(projectInOutMoneyService.remove(id)>0){
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
	@RequiresPermissions("oa:projectInOutMoney:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		projectInOutMoneyService.batchRemove(ids);
		return R.ok();
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
	@RequiresPermissions("oa:projectInOutMoney:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<ProjectInOutMoneyDO> list = new ArrayList<ProjectInOutMoneyDO>();
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
				ProjectInOutMoneyDO projectInOutMoney;
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

						
					  					  						
					  						 /** 交易类型(1：现金记账,2:银行记账,4:工会工行记账,8:工会贵州银行记账) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String type = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(type)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,交易类型(1：现金记账,2:银行记账,4:工会工行记账,8:工会贵州银行记账)未填写)");
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
					  						
					  						 /** 合同id */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String contractId = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(contractId)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,合同id未填写)");
						} 
					  						
					  						 /** 合同名称 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String contractName = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(contractName)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,合同名称未填写)");
						} 
					  						
					  						 /** 对方名称 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String clientName = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(clientName)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,对方名称未填写)");
						} 
					  						
					  						 /** 明细科目 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String subjectItem = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(subjectItem)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,明细科目未填写)");
						} 
					  						
					  						 /** 描述 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String descript = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(descript)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,描述未填写)");
						} 
					  						
					  						 /** 初期金额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String beginAmount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(beginAmount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,初期金额未填写)");
						} 
					  						
					  						 /** 收支类型(1:收入,2:支出) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String inOut = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(inOut)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,收支类型(1:收入,2:支出)未填写)");
						} 
					  						
					  						 /** 交易时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String happenTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(happenTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,交易时间未填写)");
						} 
					  						
					  						 /** 收入金额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String inAmount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(inAmount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,收入金额未填写)");
						} 
					  						
					  						 /** 支出金额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String outAmount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(outAmount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,支出金额未填写)");
						} 
					  						
					  						 /** 结存金额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String endAmount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(endAmount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,结存金额未填写)");
						} 
					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  						 /**  */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String remark = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(remark)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,未填写)");
						} 
					  						
					  					  						
					  					  						
					  						 /**  */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String payAmount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(payAmount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,未填写)");
						} 
					  					
					 
					projectInOutMoney = new ProjectInOutMoneyDO();
					//projectInOutMoney.setName(noNullName);

					projectInOutMoney = projectInOutMoneyService.findOne(projectInOutMoney);
					if (null == projectInOutMoney) {
						projectInOutMoney = new ProjectInOutMoneyDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：交易类型(1：现金记账,2:银行记账,4:工会工行记账,8:工会贵州银行记账)
						 */
						 
						 						 	 projectInOutMoney.setType(Integer.parseInt(type))  ;
						 						
						 
					  						
					  						/**
						 * 设置：项目id
						 */
						 
						 							 projectInOutMoney.setProjectId(projectId)  ;
						 						
						 
					  						
					  						/**
						 * 设置：项目名称
						 */
						 
						 							 projectInOutMoney.setProjectName(projectName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：合同id
						 */
						 
						 							 projectInOutMoney.setContractId(contractId)  ;
						 						
						 
					  						
					  						/**
						 * 设置：合同名称
						 */
						 
						 							 projectInOutMoney.setContractName(contractName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：对方名称
						 */
						 
						 							 projectInOutMoney.setClientName(clientName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：明细科目
						 */
						 
						 							 projectInOutMoney.setSubjectItem(subjectItem)  ;
						 						
						 
					  						
					  						/**
						 * 设置：描述
						 */
						 
						 							 projectInOutMoney.setDescript(descript)  ;
						 						
						 
					  						
					  						/**
						 * 设置：初期金额
						 */
						 
						 							 projectInOutMoney.setBeginAmount(new BigDecimal(beginAmount))  ;
						 						
						 
					  						
					  						/**
						 * 设置：收支类型(1:收入,2:支出)
						 */
						 
						 						 	 projectInOutMoney.setInOut(Integer.parseInt(inOut))  ;
						 						
						 
					  						
					  						/**
						 * 设置：交易时间
						 */
						 
						 						 	 projectInOutMoney.setHappenTime(DateUtils.stringToDate(happenTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：收入金额
						 */
						 
						 							 projectInOutMoney.setInAmount(new BigDecimal(inAmount))  ;
						 						
						 
					  						
					  						/**
						 * 设置：支出金额
						 */
						 
						 							 projectInOutMoney.setOutAmount(outAmount)  ;
						 						
						 
					  						
					  						/**
						 * 设置：结存金额
						 */
						 
						 							 projectInOutMoney.setEndAmount(new BigDecimal(endAmount))  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  						/**
						 * 设置：
						 */
						 
						 							 projectInOutMoney.setRemark(remark)  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  						
					  						/**
						 * 设置：
						 */
						 
						 							 projectInOutMoney.setPayAmount(new BigDecimal(payAmount))  ;
						 						
						 
					  					
					projectInOutMoney.setCreateTime(new Date());
					projectInOutMoney.setIsdelete(0);

					list.add(projectInOutMoney);
				}
				UserDO loginInfo = super.getLoginUser();
				projectInOutMoneyService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	
}
