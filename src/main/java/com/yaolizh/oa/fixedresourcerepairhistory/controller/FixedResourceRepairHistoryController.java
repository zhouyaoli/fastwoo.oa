package com.yaolizh.oa.fixedresourcerepairhistory.controller;

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
import com.yaolizh.oa.fixedresourcerepairhistory.domain.FixedResourceRepairHistoryDO;
import com.yaolizh.oa.fixedresourcerepairhistory.service.FixedResourceRepairHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 固定资产维修记录
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:03
 */
@Api(value="固定资产维修记录") 
@Controller
@RequestMapping("/oa/fixedResourceRepairHistory")
public class FixedResourceRepairHistoryController extends BaseController {
	@Autowired
	private FixedResourceRepairHistoryService fixedResourceRepairHistoryService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:fixedResourceRepairHistory:fixedResourceRepairHistory")
	String FixedResourceRepairHistory(){
	    return "oa/fixedResourceRepairHistory/fixedResourceRepairHistory";
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
	@RequiresPermissions("oa:fixedResourceRepairHistory:fixedResourceRepairHistory")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<FixedResourceRepairHistoryDO> fixedResourceRepairHistoryList = (Page<FixedResourceRepairHistoryDO>) fixedResourceRepairHistoryService.list(query);
		
		PageUtils pageUtils = new PageUtils(fixedResourceRepairHistoryList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:fixedResourceRepairHistory:add")
	String add(Model model){
		FixedResourceRepairHistoryDO fixedResourceRepairHistory = new FixedResourceRepairHistoryDO();
		model.addAttribute("fixedResourceRepairHistory", fixedResourceRepairHistory);
	    return "oa/fixedResourceRepairHistory/addOrUpdate";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:fixedResourceRepairHistory:edit")
	String edit(@PathVariable("id") String id,Model model){
		FixedResourceRepairHistoryDO fixedResourceRepairHistory = fixedResourceRepairHistoryService.get(id);
		model.addAttribute("fixedResourceRepairHistory", fixedResourceRepairHistory);
	    return "oa/fixedResourceRepairHistory/addOrUpdate";
	}
	
	/**
	 * 新增保存接口
	 * @param fixedResourceRepairHistory  FixedResourceRepairHistoryDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fixedResourceRepairHistory", value = "保存实体信息", required = true, dataType = "FixedResourceRepairHistoryDO")
    })
	@ResponseBody
	@PostMapping("/saveOrUpdate")
	@RequiresPermissions( value={"oa:fixedResourceRepairHistory:add","oa:fixedResourceRepairHistory:edit"}, logical=Logical.OR)
	public R saveOrUpdate( FixedResourceRepairHistoryDO fixedResourceRepairHistory){
	UserDO loginInfo = super.getLoginUser();
		fixedResourceRepairHistoryService.saveOrUpdate(fixedResourceRepairHistory);
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
	@RequiresPermissions("oa:fixedResourceRepairHistory:remove")
	public R remove( String id){
		if(fixedResourceRepairHistoryService.remove(id)>0){
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
	@RequiresPermissions("oa:fixedResourceRepairHistory:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		fixedResourceRepairHistoryService.batchRemove(ids);
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
	@RequiresPermissions("oa:fixedResourceRepairHistory:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<FixedResourceRepairHistoryDO> list = new ArrayList<FixedResourceRepairHistoryDO>();
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
				FixedResourceRepairHistoryDO fixedResourceRepairHistory;
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

						
					  					  						
					  						 /** 维修序号 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String serialNum = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(serialNum)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,维修序号未填写)");
						} 
					  						
					  						 /** 编码 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String code = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(code)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,编码未填写)");
						} 
					  						
					  						 /** 名称 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String name = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(name)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,名称未填写)");
						} 
					  						
					  						 /** 状态(1:待维修,2:维修中,4:维修完成,8:报废) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String state = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(state)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,状态(1:待维修,2:维修中,4:维修完成,8:报废)未填写)");
						} 
					  						
					  						 /** 维修原因 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String reason = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(reason)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,维修原因未填写)");
						} 
					  						
					  						 /** 申请维修人 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String applyPerson = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(applyPerson)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,申请维修人未填写)");
						} 
					  						
					  						 /** 申请维修时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String applyTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(applyTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,申请维修时间未填写)");
						} 
					  						
					  						 /** 维修时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String repairTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(repairTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,维修时间未填写)");
						} 
					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  						 /**  */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String remark = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(remark)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,未填写)");
						} 
					  						
					  					  						
					  					  					
					 
					fixedResourceRepairHistory = new FixedResourceRepairHistoryDO();
					//fixedResourceRepairHistory.setName(noNullName);

					fixedResourceRepairHistory = fixedResourceRepairHistoryService.findOne(fixedResourceRepairHistory);
					if (null == fixedResourceRepairHistory) {
						fixedResourceRepairHistory = new FixedResourceRepairHistoryDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：维修序号
						 */
						 
						 							 fixedResourceRepairHistory.setSerialNum(serialNum)  ;
						 						
						 
					  						
					  						/**
						 * 设置：编码
						 */
						 
						 							 fixedResourceRepairHistory.setCode(code)  ;
						 						
						 
					  						
					  						/**
						 * 设置：名称
						 */
						 
						 							 fixedResourceRepairHistory.setName(name)  ;
						 						
						 
					  						
					  						/**
						 * 设置：状态(1:待维修,2:维修中,4:维修完成,8:报废)
						 */
						 
						 						 	 fixedResourceRepairHistory.setState(Integer.parseInt(state))  ;
						 						
						 
					  						
					  						/**
						 * 设置：维修原因
						 */
						 
						 							 fixedResourceRepairHistory.setReason(reason)  ;
						 						
						 
					  						
					  						/**
						 * 设置：申请维修人
						 */
						 
						 							 fixedResourceRepairHistory.setApplyPerson(applyPerson)  ;
						 						
						 
					  						
					  						/**
						 * 设置：申请维修时间
						 */
						 
						 						 	 fixedResourceRepairHistory.setApplyTime(DateUtils.stringToDate(applyTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：维修时间
						 */
						 
						 						 	 fixedResourceRepairHistory.setRepairTime(DateUtils.stringToDate(repairTime))  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  						/**
						 * 设置：
						 */
						 
						 							 fixedResourceRepairHistory.setRemark(remark)  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  					
					fixedResourceRepairHistory.setCreateTime(new Date());
					fixedResourceRepairHistory.setIsdelete(0);

					list.add(fixedResourceRepairHistory);
				}
				UserDO loginInfo = super.getLoginUser();
				fixedResourceRepairHistoryService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	
}
