package com.yaolizh.oa.fixedresourceusehistory.controller;

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
import com.yaolizh.oa.fixedresourceusehistory.domain.FixedResourceUseHistoryDO;
import com.yaolizh.oa.fixedresourceusehistory.service.FixedResourceUseHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 固定资产借用归还记录
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:58
 */
@Api(value="固定资产借用归还记录") 
@Controller
@RequestMapping("/oa/fixedResourceUseHistory")
public class FixedResourceUseHistoryController extends BaseController {
	@Autowired
	private FixedResourceUseHistoryService fixedResourceUseHistoryService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:fixedResourceUseHistory:fixedResourceUseHistory")
	String FixedResourceUseHistory(){
	    return "oa/fixedResourceUseHistory/fixedResourceUseHistory";
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
	@RequiresPermissions("oa:fixedResourceUseHistory:fixedResourceUseHistory")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<FixedResourceUseHistoryDO> fixedResourceUseHistoryList = (Page<FixedResourceUseHistoryDO>) fixedResourceUseHistoryService.list(query);
		
		PageUtils pageUtils = new PageUtils(fixedResourceUseHistoryList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:fixedResourceUseHistory:add")
	String add(){
	    return "oa/fixedResourceUseHistory/add";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:fixedResourceUseHistory:edit")
	String edit(@PathVariable("id") String id,Model model){
		FixedResourceUseHistoryDO fixedResourceUseHistory = fixedResourceUseHistoryService.get(id);
		model.addAttribute("fixedResourceUseHistory", fixedResourceUseHistory);
	    return "oa/fixedResourceUseHistory/edit";
	}
	
	/**
	 * 新增保存接口
	 * @param fixedResourceUseHistory  FixedResourceUseHistoryDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fixedResourceUseHistory", value = "保存实体信息", required = true, dataType = "FixedResourceUseHistoryDO")
    })
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:fixedResourceUseHistory:add")
	public R save( FixedResourceUseHistoryDO fixedResourceUseHistory){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			fixedResourceUseHistory.setCreator(loginInfo.getId());
			fixedResourceUseHistory.setCreatorby(loginInfo.getUsername());
			fixedResourceUseHistory.setCreatorName(loginInfo.getName());
			fixedResourceUseHistory.setCreateDeptid(loginInfo.getDeptId());
			fixedResourceUseHistory.setCreateDeptcode(loginInfo.getDeptId());
			fixedResourceUseHistory.setCreateDeptname(loginInfo.getDeptName());
			fixedResourceUseHistory.setCreateOrgid(null);
			fixedResourceUseHistory.setCreateOrgcode(null);
			fixedResourceUseHistory.setCreateOrgname(null);
		}
		fixedResourceUseHistory.setIsdelete(0);
		fixedResourceUseHistory.setCreateTime(new Date());
		if(fixedResourceUseHistoryService.save(fixedResourceUseHistory)>0){
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
	@RequiresPermissions("oa:fixedResourceUseHistory:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<FixedResourceUseHistoryDO> list = new ArrayList<FixedResourceUseHistoryDO>();
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
				FixedResourceUseHistoryDO fixedResourceUseHistory;
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

						
					  					  						
					  						 /** 借出序号 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String serialNum = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(serialNum)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,借出序号未填写)");
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
					  						
					  						 /** 是否归还 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String hasReturn = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(hasReturn)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,是否归还未填写)");
						} 
					  						
					  						 /** 使用人 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String usePerson = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(usePerson)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,使用人未填写)");
						} 
					  						
					  						 /** 开始使用时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String beginUseTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(beginUseTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,开始使用时间未填写)");
						} 
					  						
					  						 /** 归还时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String endUseTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(endUseTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,归还时间未填写)");
						} 
					  					
					 
					fixedResourceUseHistory = new FixedResourceUseHistoryDO();
					//fixedResourceUseHistory.setName(noNullName);

					//fixedResourceUseHistory = fixedResourceUseHistoryService.find(fixedResourceUseHistory);
					if (null == fixedResourceUseHistory) {
						fixedResourceUseHistory = new FixedResourceUseHistoryDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：借出序号
						 */
						 
						 							 fixedResourceUseHistory.setSerialNum(serialNum)  ;
						 						
						 
					  						
					  						/**
						 * 设置：编码
						 */
						 
						 							 fixedResourceUseHistory.setCode(code)  ;
						 						
						 
					  						
					  						/**
						 * 设置：名称
						 */
						 
						 							 fixedResourceUseHistory.setName(name)  ;
						 						
						 
					  						
					  						/**
						 * 设置：是否归还
						 */
						 
						 						 	 fixedResourceUseHistory.setHasReturn(Integer.parseInt(hasReturn))  ;
						 						
						 
					  						
					  						/**
						 * 设置：使用人
						 */
						 
						 							 fixedResourceUseHistory.setUsePerson(usePerson)  ;
						 						
						 
					  						
					  						/**
						 * 设置：开始使用时间
						 */
						 
						 						 	 fixedResourceUseHistory.setBeginUseTime(DateUtils.stringToDate(beginUseTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：归还时间
						 */
						 
						 						 	 fixedResourceUseHistory.setEndUseTime(DateUtils.stringToDate(endUseTime))  ;
						 						
						 
					  					
					fixedResourceUseHistory.setCreateTime(new Date());
					fixedResourceUseHistory.setIsdelete(0);

					list.add(fixedResourceUseHistory);
				}
				UserDO loginInfo = super.getLoginUser();
				fixedResourceUseHistoryService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	/**
	 * 修改保存接口
	 * @param fixedResourceUseHistory  FixedResourceUseHistoryDO
	 * @return
	 */
	 @ApiOperation(value="修改保存接口", notes="修改保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fixedResourceUseHistory", value = "保存实体信息", required = true, dataType = "FixedResourceUseHistoryDO")
    })
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:fixedResourceUseHistory:edit")
	public R update( FixedResourceUseHistoryDO fixedResourceUseHistory){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			fixedResourceUseHistory.setUpdator(loginInfo.getId());
			fixedResourceUseHistory.setUpdatorby(loginInfo.getUsername());
			fixedResourceUseHistory.setUpdatorName(loginInfo.getName());
		}
		fixedResourceUseHistory.setIsdelete(0);
		fixedResourceUseHistory.setLastTime(new Date());
		fixedResourceUseHistoryService.update(fixedResourceUseHistory);
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
	@RequiresPermissions("oa:fixedResourceUseHistory:remove")
	public R remove( String id){
		if(fixedResourceUseHistoryService.remove(id)>0){
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
	@RequiresPermissions("oa:fixedResourceUseHistory:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		fixedResourceUseHistoryService.batchRemove(ids);
		return R.ok();
	}
	
}
