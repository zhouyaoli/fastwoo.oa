package com.yaolizh.oa.empyearcheck.controller;

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
import com.yaolizh.oa.empyearcheck.domain.EmpYearCheckDO;
import com.yaolizh.oa.empyearcheck.service.EmpYearCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 职工年度考核报告
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:04
 */
@Api(value="职工年度考核报告") 
@Controller
@RequestMapping("/oa/empYearCheck")
public class EmpYearCheckController extends BaseController {
	@Autowired
	private EmpYearCheckService empYearCheckService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:empYearCheck:empYearCheck")
	String EmpYearCheck(){
	    return "oa/empYearCheck/empYearCheck";
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
	@RequiresPermissions("oa:empYearCheck:empYearCheck")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<EmpYearCheckDO> empYearCheckList = (Page<EmpYearCheckDO>) empYearCheckService.list(query);
		
		PageUtils pageUtils = new PageUtils(empYearCheckList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:empYearCheck:add")
	String add(Model model){
		EmpYearCheckDO empYearCheck = new EmpYearCheckDO();
		model.addAttribute("empYearCheck", empYearCheck);
	    return "oa/empYearCheck/addOrUpdate";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:empYearCheck:edit")
	String edit(@PathVariable("id") String id,Model model){
		EmpYearCheckDO empYearCheck = empYearCheckService.get(id);
		model.addAttribute("empYearCheck", empYearCheck);
	    return "oa/empYearCheck/addOrUpdate";
	}
	
	/**
	 * 新增保存接口
	 * @param empYearCheck  EmpYearCheckDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "empYearCheck", value = "保存实体信息", required = true, dataType = "EmpYearCheckDO")
    })
	@ResponseBody
	@PostMapping("/saveOrUpdate")
	@RequiresPermissions( value={"oa:empYearCheck:add","oa:empYearCheck:edit"}, logical=Logical.OR)
	public R saveOrUpdate( EmpYearCheckDO empYearCheck){
	UserDO loginInfo = super.getLoginUser();
		empYearCheckService.saveOrUpdate(empYearCheck);
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
	@RequiresPermissions("oa:empYearCheck:remove")
	public R remove( String id){
		if(empYearCheckService.remove(id)>0){
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
	@RequiresPermissions("oa:empYearCheck:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		empYearCheckService.batchRemove(ids);
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
	@RequiresPermissions("oa:empYearCheck:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<EmpYearCheckDO> list = new ArrayList<EmpYearCheckDO>();
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
				EmpYearCheckDO empYearCheck;
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
					  						
					  						 /** 年度 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String examineYear = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(examineYear)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,年度未填写)");
						} 
					  						
					  						 /** 描述 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String descript = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(descript)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,描述未填写)");
						} 
					  						
					  						 /** 附件路劲 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String filePath = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(filePath)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,附件路劲未填写)");
						} 
					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  						 /**  */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String remark = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(remark)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,未填写)");
						} 
					  						
					  					  						
					  					  					
					 
					empYearCheck = new EmpYearCheckDO();
					//empYearCheck.setName(noNullName);

					empYearCheck = empYearCheckService.findOne(empYearCheck);
					if (null == empYearCheck) {
						empYearCheck = new EmpYearCheckDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：用户id
						 */
						 
						 							 empYearCheck.setUserId(userId)  ;
						 						
						 
					  						
					  						/**
						 * 设置：员工编号
						 */
						 
						 							 empYearCheck.setNo(no)  ;
						 						
						 
					  						
					  						/**
						 * 设置：名字
						 */
						 
						 							 empYearCheck.setName(name)  ;
						 						
						 
					  						
					  						/**
						 * 设置：身份证
						 */
						 
						 							 empYearCheck.setIdCard(idCard)  ;
						 						
						 
					  						
					  						/**
						 * 设置：年度
						 */
						 
						 						 	 empYearCheck.setExamineYear(Integer.parseInt(examineYear))  ;
						 						
						 
					  						
					  						/**
						 * 设置：描述
						 */
						 
						 							 empYearCheck.setDescript(descript)  ;
						 						
						 
					  						
					  						/**
						 * 设置：附件路劲
						 */
						 
						 						 	 empYearCheck.setFilePath(Integer.parseInt(filePath))  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  						/**
						 * 设置：
						 */
						 
						 							 empYearCheck.setRemark(remark)  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  					
					empYearCheck.setCreateTime(new Date());
					empYearCheck.setIsdelete(0);

					list.add(empYearCheck);
				}
				UserDO loginInfo = super.getLoginUser();
				empYearCheckService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	
}
