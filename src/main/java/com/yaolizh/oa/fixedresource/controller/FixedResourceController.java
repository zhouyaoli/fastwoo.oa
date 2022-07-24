package com.yaolizh.oa.fixedresource.controller;

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
import com.yaolizh.oa.fixedresource.domain.FixedResourceDO;
import com.yaolizh.oa.fixedresource.service.FixedResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 固定资产信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:03
 */
@Api(value="固定资产信息") 
@Controller
@RequestMapping("/oa/fixedResource")
public class FixedResourceController extends BaseController {
	@Autowired
	private FixedResourceService fixedResourceService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:fixedResource:fixedResource")
	String FixedResource(){
	    return "oa/fixedResource/fixedResource";
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
	@RequiresPermissions("oa:fixedResource:fixedResource")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<FixedResourceDO> fixedResourceList = (Page<FixedResourceDO>) fixedResourceService.list(query);
		
		PageUtils pageUtils = new PageUtils(fixedResourceList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:fixedResource:add")
	String add(Model model){
		FixedResourceDO fixedResource = new FixedResourceDO();
		model.addAttribute("fixedResource", fixedResource);
	    return "oa/fixedResource/addOrUpdate";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:fixedResource:edit")
	String edit(@PathVariable("id") String id,Model model){
		FixedResourceDO fixedResource = fixedResourceService.get(id);
		model.addAttribute("fixedResource", fixedResource);
	    return "oa/fixedResource/addOrUpdate";
	}
	
	/**
	 * 新增保存接口
	 * @param fixedResource  FixedResourceDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fixedResource", value = "保存实体信息", required = true, dataType = "FixedResourceDO")
    })
	@ResponseBody
	@PostMapping("/saveOrUpdate")
	@RequiresPermissions( value={"oa:fixedResource:add","oa:fixedResource:edit"}, logical=Logical.OR)
	public R saveOrUpdate( FixedResourceDO fixedResource){
	UserDO loginInfo = super.getLoginUser();
		fixedResourceService.saveOrUpdate(fixedResource);
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
	@RequiresPermissions("oa:fixedResource:remove")
	public R remove( String id){
		if(fixedResourceService.remove(id)>0){
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
	@RequiresPermissions("oa:fixedResource:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		fixedResourceService.batchRemove(ids);
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
	@RequiresPermissions("oa:fixedResource:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<FixedResourceDO> list = new ArrayList<FixedResourceDO>();
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
				FixedResourceDO fixedResource;
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
					  						
					  						 /** 规格型号 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String model = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(model)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,规格型号未填写)");
						} 
					  						
					  						 /** 购买日期 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String buyDate = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(buyDate)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,购买日期未填写)");
						} 
					  						
					  						 /** 购买金额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String buyAmount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(buyAmount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,购买金额未填写)");
						} 
					  						
					  						 /** 状态(1:正常,2:出借,4:保修,8:报废) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String state = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(state)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,状态(1:正常,2:出借,4:保修,8:报废)未填写)");
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
					  						
					  						 /** 报废批次 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String crapSerialNum = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(crapSerialNum)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,报废批次未填写)");
						} 
					  						
					  						 /** 报废人 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String crapPerson = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(crapPerson)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,报废人未填写)");
						} 
					  						
					  						 /** 报废时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String crapTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(crapTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,报废时间未填写)");
						} 
					  						
					  						 /** 报废时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String crapReason = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(crapReason)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,报废时间未填写)");
						} 
					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  						 /**  */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String remark = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(remark)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,未填写)");
						} 
					  						
					  					  						
					  					  					
					 
					fixedResource = new FixedResourceDO();
					//fixedResource.setName(noNullName);

					fixedResource = fixedResourceService.findOne(fixedResource);
					if (null == fixedResource) {
						fixedResource = new FixedResourceDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：编码
						 */
						 
						 							 fixedResource.setCode(code)  ;
						 						
						 
					  						
					  						/**
						 * 设置：名称
						 */
						 
						 							 fixedResource.setName(name)  ;
						 						
						 
					  						
					  						/**
						 * 设置：规格型号
						 */
						 
						 							 fixedResource.setModel(model)  ;
						 						
						 
					  						
					  						/**
						 * 设置：购买日期
						 */
						 
						 						 	 fixedResource.setBuyDate(DateUtils.stringToDate(buyDate))  ;
						 						
						 
					  						
					  						/**
						 * 设置：购买金额
						 */
						 
						 							 fixedResource.setBuyAmount(new BigDecimal(buyAmount))  ;
						 						
						 
					  						
					  						/**
						 * 设置：状态(1:正常,2:出借,4:保修,8:报废)
						 */
						 
						 							 fixedResource.setState(state)  ;
						 						
						 
					  						
					  						/**
						 * 设置：使用人
						 */
						 
						 							 fixedResource.setUsePerson(usePerson)  ;
						 						
						 
					  						
					  						/**
						 * 设置：开始使用时间
						 */
						 
						 						 	 fixedResource.setBeginUseTime(DateUtils.stringToDate(beginUseTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：报废批次
						 */
						 
						 							 fixedResource.setCrapSerialNum(crapSerialNum)  ;
						 						
						 
					  						
					  						/**
						 * 设置：报废人
						 */
						 
						 							 fixedResource.setCrapPerson(crapPerson)  ;
						 						
						 
					  						
					  						/**
						 * 设置：报废时间
						 */
						 
						 						 	 fixedResource.setCrapTime(DateUtils.stringToDate(crapTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：报废时间
						 */
						 
						 							 fixedResource.setCrapReason(crapReason)  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  						/**
						 * 设置：
						 */
						 
						 							 fixedResource.setRemark(remark)  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  					
					fixedResource.setCreateTime(new Date());
					fixedResource.setIsdelete(0);

					list.add(fixedResource);
				}
				UserDO loginInfo = super.getLoginUser();
				fixedResourceService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	
}
