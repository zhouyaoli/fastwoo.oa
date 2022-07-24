package com.yaolizh.oa.empresthistory.controller;

import java.io.IOException;
import java.io.InputStream;
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
import com.yaolizh.fastwoo.common.utils.PageUtils;
import com.yaolizh.fastwoo.common.utils.Query;
import com.yaolizh.fastwoo.common.utils.R;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.empresthistory.domain.EmpRestHistoryDO;
import com.yaolizh.oa.empresthistory.service.EmpRestHistoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 职工请假记录
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:55
 */
@Api(value="职工请假记录") 
@Controller
@RequestMapping("/oa/empRestHistory")
public class EmpRestHistoryController extends BaseController {
	@Autowired
	private EmpRestHistoryService empRestHistoryService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:empRestHistory:empRestHistory")
	String EmpRestHistory(){
	    return "oa/empRestHistory/empRestHistory";
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
	@RequiresPermissions("oa:empRestHistory:empRestHistory")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<EmpRestHistoryDO> empRestHistoryList = (Page<EmpRestHistoryDO>) empRestHistoryService.list(query);
		
		PageUtils pageUtils = new PageUtils(empRestHistoryList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:empRestHistory:add")
	String add(){
	    return "oa/empRestHistory/add";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:empRestHistory:edit")
	String edit(@PathVariable("id") String id,Model model){
		EmpRestHistoryDO empRestHistory = empRestHistoryService.get(id);
		model.addAttribute("empRestHistory", empRestHistory);
	    return "oa/empRestHistory/edit";
	}
	
	/**
	 * 新增保存接口
	 * @param empRestHistory  EmpRestHistoryDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "empRestHistory", value = "保存实体信息", required = true, dataType = "EmpRestHistoryDO")
    })
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:empRestHistory:add")
	public R save( EmpRestHistoryDO empRestHistory){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			empRestHistory.setCreator(loginInfo.getId());
			empRestHistory.setCreatorby(loginInfo.getUsername());
			empRestHistory.setCreatorName(loginInfo.getName());
			empRestHistory.setCreateDeptid(loginInfo.getDeptId());
			empRestHistory.setCreateDeptcode(loginInfo.getDeptId());
			empRestHistory.setCreateDeptname(loginInfo.getDeptName());
			empRestHistory.setCreateOrgid(null);
			empRestHistory.setCreateOrgcode(null);
			empRestHistory.setCreateOrgname(null);
		}
		empRestHistory.setIsdelete(0);
		empRestHistory.setCreateTime(new Date());
		if(empRestHistoryService.save(empRestHistory)>0){
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
	@RequiresPermissions("oa:empRestHistory:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<EmpRestHistoryDO> list = new ArrayList<EmpRestHistoryDO>();
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
				EmpRestHistoryDO empRestHistory;
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

						
					  					  						
					  						 /** 用户id */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String userId = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(userId)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,用户id未填写)");
						} 
					  						
					  						 /** 员工编号 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String no = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(no)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,员工编号未填写)");
						} 
					  						
					  						 /** 名字 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String name = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(name)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,名字未填写)");
						} 
					  						
					  						 /** 身份证 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String idCard = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(idCard)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,身份证未填写)");
						} 
					  						
					  						 /** 年假、育儿假、产假、婚嫁、陪产假、丧假、病假、事假 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String type = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(type)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,年假、育儿假、产假、婚嫁、陪产假、丧假、病假、事假未填写)");
						} 
					  						
					  						 /** 休息天数 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String restDays = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(restDays)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,休息天数未填写)");
						} 
					  						
					  						 /** 开始休息时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String beginTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(beginTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,开始休息时间未填写)");
						} 
					  						
					  						 /** 结束休息时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String endTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(endTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,结束休息时间未填写)");
						} 
					  						
					  						 /** 请假原因 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String reason = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(reason)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,请假原因未填写)");
						} 
					  						
					  						 /** 状态(1：新建，2：审核通过，4：审核不通过,8：撤销，16：完成) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String state = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(state)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,状态(1：新建，2：审核通过，4：审核不通过,8：撤销，16：完成)未填写)");
						} 
					  						
					  						 /** 流程id */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String processInstanceId = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(processInstanceId)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,流程id未填写)");
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
					  					
					 
					empRestHistory = new EmpRestHistoryDO();
					//empRestHistory.setName(noNullName);

					//empRestHistory = empRestHistoryService.find(empRestHistory);
					if (null == empRestHistory) {
						empRestHistory = new EmpRestHistoryDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：用户id
						 */
						 
						 							 empRestHistory.setUserId(userId)  ;
						 						
						 
					  						
					  						/**
						 * 设置：员工编号
						 */
						 
						 							 empRestHistory.setNo(no)  ;
						 						
						 
					  						
					  						/**
						 * 设置：名字
						 */
						 
						 							 empRestHistory.setName(name)  ;
						 						
						 
					  						
					  						/**
						 * 设置：身份证
						 */
						 
						 							 empRestHistory.setIdCard(idCard)  ;
						 						
						 
					  						
					  						/**
						 * 设置：年假、育儿假、产假、婚嫁、陪产假、丧假、病假、事假
						 */
						 
						 						 	 empRestHistory.setType(Integer.parseInt(type))  ;
						 						
						 
					  						
					  						/**
						 * 设置：休息天数
						 */
						 
						 							 empRestHistory.setRestDays(restDays)  ;
						 						
						 
					  						
					  						/**
						 * 设置：开始休息时间
						 */
						 
						 						 	 empRestHistory.setBeginTime(Integer.parseInt(beginTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：结束休息时间
						 */
						 
						 						 	 empRestHistory.setEndTime(Integer.parseInt(endTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：请假原因
						 */
						 
						 							 empRestHistory.setReason(reason)  ;
						 						
						 
					  						
					  						/**
						 * 设置：状态(1：新建，2：审核通过，4：审核不通过,8：撤销，16：完成)
						 */
						 
						 						 	 empRestHistory.setState(Integer.parseInt(state))  ;
						 						
						 
					  						
					  						/**
						 * 设置：流程id
						 */
						 
						 							 empRestHistory.setProcessInstanceId(processInstanceId)  ;
						 						
						 
					  						
					  						/**
						 * 设置：当前环节
						 */
						 
						 							 empRestHistory.setCurNodeName(curNodeName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：当前操作人
						 */
						 
						 							 empRestHistory.setCurCheckName(curCheckName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：当前操作意见
						 */
						 
						 							 empRestHistory.setCurCheckComment(curCheckComment)  ;
						 						
						 
					  						
					  						/**
						 * 设置：选择的值
						 */
						 
						 							 empRestHistory.setChooseCode(chooseCode)  ;
						 						
						 
					  						
					  						/**
						 * 设置：选择的字符串
						 */
						 
						 							 empRestHistory.setChooseText(chooseText)  ;
						 						
						 
					  					
					empRestHistory.setCreateTime(new Date());
					empRestHistory.setIsdelete(0);

					list.add(empRestHistory);
				}
				UserDO loginInfo = super.getLoginUser();
				empRestHistoryService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	/**
	 * 修改保存接口
	 * @param empRestHistory  EmpRestHistoryDO
	 * @return
	 */
	 @ApiOperation(value="修改保存接口", notes="修改保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "empRestHistory", value = "保存实体信息", required = true, dataType = "EmpRestHistoryDO")
    })
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:empRestHistory:edit")
	public R update( EmpRestHistoryDO empRestHistory){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			empRestHistory.setUpdator(loginInfo.getId());
			empRestHistory.setUpdatorby(loginInfo.getUsername());
			empRestHistory.setUpdatorName(loginInfo.getName());
		}
		empRestHistory.setIsdelete(0);
		empRestHistory.setLastTime(new Date());
		empRestHistoryService.update(empRestHistory);
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
	@RequiresPermissions("oa:empRestHistory:remove")
	public R remove( String id){
		if(empRestHistoryService.remove(id)>0){
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
	@RequiresPermissions("oa:empRestHistory:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		empRestHistoryService.batchRemove(ids);
		return R.ok();
	}
	
}
