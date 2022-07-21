package com.yaolizh.oa.projectrefund.controller;

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
import com.yaolizh.oa.projectrefund.domain.ProjectRefundDO;
import com.yaolizh.oa.projectrefund.service.ProjectRefundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 报销信息表
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:55
 */
@Api(value="报销信息表") 
@Controller
@RequestMapping("/oa/projectRefund")
public class ProjectRefundController extends BaseController {
	@Autowired
	private ProjectRefundService projectRefundService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:projectRefund:projectRefund")
	String ProjectRefund(){
	    return "oa/projectRefund/projectRefund";
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
	@RequiresPermissions("oa:projectRefund:projectRefund")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<ProjectRefundDO> projectRefundList = (Page<ProjectRefundDO>) projectRefundService.list(query);
		
		PageUtils pageUtils = new PageUtils(projectRefundList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:projectRefund:add")
	String add(){
	    return "oa/projectRefund/add";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:projectRefund:edit")
	String edit(@PathVariable("id") String id,Model model){
		ProjectRefundDO projectRefund = projectRefundService.get(id);
		model.addAttribute("projectRefund", projectRefund);
	    return "oa/projectRefund/edit";
	}
	
	/**
	 * 新增保存接口
	 * @param projectRefund  ProjectRefundDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectRefund", value = "保存实体信息", required = true, dataType = "ProjectRefundDO")
    })
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:projectRefund:add")
	public R save( ProjectRefundDO projectRefund){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			projectRefund.setCreator(loginInfo.getId());
			projectRefund.setCreatorby(loginInfo.getUsername());
			projectRefund.setCreatorName(loginInfo.getName());
			projectRefund.setCreateDeptid(loginInfo.getDeptId());
			projectRefund.setCreateDeptcode(loginInfo.getDeptId());
			projectRefund.setCreateDeptname(loginInfo.getDeptName());
			projectRefund.setCreateOrgid(null);
			projectRefund.setCreateOrgcode(null);
			projectRefund.setCreateOrgname(null);
		}
		projectRefund.setIsdelete(0);
		projectRefund.setCreateTime(new Date());
		if(projectRefundService.save(projectRefund)>0){
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
	@RequiresPermissions("oa:projectRefund:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<ProjectRefundDO> list = new ArrayList<ProjectRefundDO>();
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
				ProjectRefundDO projectRefund;
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

						
					  					  						
					  						 /** 项目ID */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String projectId = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(projectId)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,项目ID未填写)");
						} 
					  						
					  						 /** 用户 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String userId = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(userId)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,用户未填写)");
						} 
					  						
					  						 /** 用户名字 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String userName = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(userName)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,用户名字未填写)");
						} 
					  						
					  						 /** `项目名称` */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String projectName = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(projectName)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,`项目名称`未填写)");
						} 
					  						
					  						 /** 报销日期 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String refundTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(refundTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,报销日期未填写)");
						} 
					  						
					  						 /** 报销金额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String amount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(amount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,报销金额未填写)");
						} 
					  						
					  						 /** 报销说明 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String reason = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(reason)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,报销说明未填写)");
						} 
					  						
					  						 /** 流程id */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String processInstanceId = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(processInstanceId)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,流程id未填写)");
						} 
					  						
					  						 /** 状态(1：新建，2：审核通过，4：审核不通过,8：撤销，16：完成) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String state = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(state)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,状态(1：新建，2：审核通过，4：审核不通过,8：撤销，16：完成)未填写)");
						} 
					  						
					  						 /** 当前环节 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String curNodeName = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(curNodeName)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,当前环节未填写)");
						} 
					  						
					  						 /** 当前操作人 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String curCheckName = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(curCheckName)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,当前操作人未填写)");
						} 
					  						
					  						 /** 当前操作意见 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String curCheckComment = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(curCheckComment)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,当前操作意见未填写)");
						} 
					  						
					  						 /** 选择的值 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String chooseCode = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(chooseCode)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,选择的值未填写)");
						} 
					  						
					  						 /** 选择的字符串 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String chooseText = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(chooseText)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,选择的字符串未填写)");
						} 
					  					
					 
					projectRefund = new ProjectRefundDO();
					//projectRefund.setName(noNullName);

					//projectRefund = projectRefundService.find(projectRefund);
					if (null == projectRefund) {
						projectRefund = new ProjectRefundDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：项目ID
						 */
						 
						 							 projectRefund.setProjectId(projectId)  ;
						 						
						 
					  						
					  						/**
						 * 设置：用户
						 */
						 
						 							 projectRefund.setUserId(userId)  ;
						 						
						 
					  						
					  						/**
						 * 设置：用户名字
						 */
						 
						 							 projectRefund.setUserName(userName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：`项目名称`
						 */
						 
						 							 projectRefund.setProjectName(projectName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：报销日期
						 */
						 
						 						 	 projectRefund.setRefundTime(DateUtils.stringToDate(refundTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：报销金额
						 */
						 
						 							 projectRefund.setAmount(new BigDecimal(amount))  ;
						 						
						 
					  						
					  						/**
						 * 设置：报销说明
						 */
						 
						 							 projectRefund.setReason(reason)  ;
						 						
						 
					  						
					  						/**
						 * 设置：流程id
						 */
						 
						 							 projectRefund.setProcessInstanceId(processInstanceId)  ;
						 						
						 
					  						
					  						/**
						 * 设置：状态(1：新建，2：审核通过，4：审核不通过,8：撤销，16：完成)
						 */
						 
						 						 	 projectRefund.setState(Integer.parseInt(state))  ;
						 						
						 
					  						
					  						/**
						 * 设置：当前环节
						 */
						 
						 							 projectRefund.setCurNodeName(curNodeName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：当前操作人
						 */
						 
						 							 projectRefund.setCurCheckName(curCheckName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：当前操作意见
						 */
						 
						 							 projectRefund.setCurCheckComment(curCheckComment)  ;
						 						
						 
					  						
					  						/**
						 * 设置：选择的值
						 */
						 
						 							 projectRefund.setChooseCode(chooseCode)  ;
						 						
						 
					  						
					  						/**
						 * 设置：选择的字符串
						 */
						 
						 							 projectRefund.setChooseText(chooseText)  ;
						 						
						 
					  					
					projectRefund.setCreateTime(new Date());
					projectRefund.setIsdelete(0);

					list.add(projectRefund);
				}
				UserDO loginInfo = super.getLoginUser();
				projectRefundService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	/**
	 * 修改保存接口
	 * @param projectRefund  ProjectRefundDO
	 * @return
	 */
	 @ApiOperation(value="修改保存接口", notes="修改保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectRefund", value = "保存实体信息", required = true, dataType = "ProjectRefundDO")
    })
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:projectRefund:edit")
	public R update( ProjectRefundDO projectRefund){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			projectRefund.setUpdator(loginInfo.getId());
			projectRefund.setUpdatorby(loginInfo.getUsername());
			projectRefund.setUpdatorName(loginInfo.getName());
		}
		projectRefund.setIsdelete(0);
		projectRefund.setLastTime(new Date());
		projectRefundService.update(projectRefund);
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
	@RequiresPermissions("oa:projectRefund:remove")
	public R remove( String id){
		if(projectRefundService.remove(id)>0){
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
	@RequiresPermissions("oa:projectRefund:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		projectRefundService.batchRemove(ids);
		return R.ok();
	}
	
}
