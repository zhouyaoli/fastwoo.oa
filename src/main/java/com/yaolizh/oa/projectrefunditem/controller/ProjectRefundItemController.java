package com.yaolizh.oa.projectrefunditem.controller;

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
import com.yaolizh.oa.projectrefunditem.domain.ProjectRefundItemDO;
import com.yaolizh.oa.projectrefunditem.service.ProjectRefundItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 报销子项表
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:54
 */
@Api(value="报销子项表") 
@Controller
@RequestMapping("/oa/projectRefundItem")
public class ProjectRefundItemController extends BaseController {
	@Autowired
	private ProjectRefundItemService projectRefundItemService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:projectRefundItem:projectRefundItem")
	String ProjectRefundItem(){
	    return "oa/projectRefundItem/projectRefundItem";
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
	@RequiresPermissions("oa:projectRefundItem:projectRefundItem")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<ProjectRefundItemDO> projectRefundItemList = (Page<ProjectRefundItemDO>) projectRefundItemService.list(query);
		
		PageUtils pageUtils = new PageUtils(projectRefundItemList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:projectRefundItem:add")
	String add(){
	    return "oa/projectRefundItem/add";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:projectRefundItem:edit")
	String edit(@PathVariable("id") String id,Model model){
		ProjectRefundItemDO projectRefundItem = projectRefundItemService.get(id);
		model.addAttribute("projectRefundItem", projectRefundItem);
	    return "oa/projectRefundItem/edit";
	}
	
	/**
	 * 新增保存接口
	 * @param projectRefundItem  ProjectRefundItemDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectRefundItem", value = "保存实体信息", required = true, dataType = "ProjectRefundItemDO")
    })
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:projectRefundItem:add")
	public R save( ProjectRefundItemDO projectRefundItem){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			projectRefundItem.setCreator(loginInfo.getId());
			projectRefundItem.setCreatorby(loginInfo.getUsername());
			projectRefundItem.setCreatorName(loginInfo.getName());
			projectRefundItem.setCreateDeptid(loginInfo.getDeptId());
			projectRefundItem.setCreateDeptcode(loginInfo.getDeptId());
			projectRefundItem.setCreateDeptname(loginInfo.getDeptName());
			projectRefundItem.setCreateOrgid(null);
			projectRefundItem.setCreateOrgcode(null);
			projectRefundItem.setCreateOrgname(null);
		}
		projectRefundItem.setIsdelete(0);
		projectRefundItem.setCreateTime(new Date());
		if(projectRefundItemService.save(projectRefundItem)>0){
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
	@RequiresPermissions("oa:projectRefundItem:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<ProjectRefundItemDO> list = new ArrayList<ProjectRefundItemDO>();
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
				ProjectRefundItemDO projectRefundItem;
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

						
					  					  						
					  						 /** 报销ID */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String refundId = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(refundId)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,报销ID未填写)");
						} 
					  						
					  						 /** 报销类型(字典类型) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String type = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(type)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,报销类型(字典类型)未填写)");
						} 
					  						
					  						 /** 开票单位 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String billName = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(billName)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,开票单位未填写)");
						} 
					  						
					  						 /** 开票日期 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String billDate = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(billDate)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,开票日期未填写)");
						} 
					  						
					  						 /** 开票内容 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String billContent = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(billContent)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,开票内容未填写)");
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
					  						
					  						 /** 开票金额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String billAmount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(billAmount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,开票金额未填写)");
						} 
					  						
					  						 /** 物品名称 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String goodsName = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(goodsName)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,物品名称未填写)");
						} 
					  						
					  						 /** 报销金额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String amount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(amount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,报销金额未填写)");
						} 
					  					
					 
					projectRefundItem = new ProjectRefundItemDO();
					//projectRefundItem.setName(noNullName);

					//projectRefundItem = projectRefundItemService.find(projectRefundItem);
					if (null == projectRefundItem) {
						projectRefundItem = new ProjectRefundItemDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：报销ID
						 */
						 
						 							 projectRefundItem.setRefundId(refundId)  ;
						 						
						 
					  						
					  						/**
						 * 设置：报销类型(字典类型)
						 */
						 
						 							 projectRefundItem.setType(type)  ;
						 						
						 
					  						
					  						/**
						 * 设置：开票单位
						 */
						 
						 							 projectRefundItem.setBillName(billName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：开票日期
						 */
						 
						 						 	 projectRefundItem.setBillDate(DateUtils.stringToDate(billDate))  ;
						 						
						 
					  						
					  						/**
						 * 设置：开票内容
						 */
						 
						 							 projectRefundItem.setBillContent(billContent)  ;
						 						
						 
					  						
					  						/**
						 * 设置：客户识别号
						 */
						 
						 							 projectRefundItem.setIdentifierNo(identifierNo)  ;
						 						
						 
					  						
					  						/**
						 * 设置：发票代码
						 */
						 
						 							 projectRefundItem.setBillCode(billCode)  ;
						 						
						 
					  						
					  						/**
						 * 设置：发票号码
						 */
						 
						 							 projectRefundItem.setBillNo(billNo)  ;
						 						
						 
					  						
					  						/**
						 * 设置：开票金额
						 */
						 
						 							 projectRefundItem.setBillAmount(new BigDecimal(billAmount))  ;
						 						
						 
					  						
					  						/**
						 * 设置：物品名称
						 */
						 
						 							 projectRefundItem.setGoodsName(goodsName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：报销金额
						 */
						 
						 							 projectRefundItem.setAmount(new BigDecimal(amount))  ;
						 						
						 
					  					
					projectRefundItem.setCreateTime(new Date());
					projectRefundItem.setIsdelete(0);

					list.add(projectRefundItem);
				}
				UserDO loginInfo = super.getLoginUser();
				projectRefundItemService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	/**
	 * 修改保存接口
	 * @param projectRefundItem  ProjectRefundItemDO
	 * @return
	 */
	 @ApiOperation(value="修改保存接口", notes="修改保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectRefundItem", value = "保存实体信息", required = true, dataType = "ProjectRefundItemDO")
    })
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:projectRefundItem:edit")
	public R update( ProjectRefundItemDO projectRefundItem){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			projectRefundItem.setUpdator(loginInfo.getId());
			projectRefundItem.setUpdatorby(loginInfo.getUsername());
			projectRefundItem.setUpdatorName(loginInfo.getName());
		}
		projectRefundItem.setIsdelete(0);
		projectRefundItem.setLastTime(new Date());
		projectRefundItemService.update(projectRefundItem);
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
	@RequiresPermissions("oa:projectRefundItem:remove")
	public R remove( String id){
		if(projectRefundItemService.remove(id)>0){
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
	@RequiresPermissions("oa:projectRefundItem:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		projectRefundItemService.batchRemove(ids);
		return R.ok();
	}
	
}
