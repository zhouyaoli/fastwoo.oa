package com.yaolizh.oa.carinsurehistory.controller;

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
import com.yaolizh.oa.carinsurehistory.domain.CarInsureHistoryDO;
import com.yaolizh.oa.carinsurehistory.service.CarInsureHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 车辆保险记录信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:59
 */
@Api(value="车辆保险记录信息") 
@Controller
@RequestMapping("/oa/carInsureHistory")
public class CarInsureHistoryController extends BaseController {
	@Autowired
	private CarInsureHistoryService carInsureHistoryService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:carInsureHistory:carInsureHistory")
	String CarInsureHistory(){
	    return "oa/carInsureHistory/carInsureHistory";
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
	@RequiresPermissions("oa:carInsureHistory:carInsureHistory")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<CarInsureHistoryDO> carInsureHistoryList = (Page<CarInsureHistoryDO>) carInsureHistoryService.list(query);
		
		PageUtils pageUtils = new PageUtils(carInsureHistoryList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:carInsureHistory:add")
	String add(){
	    return "oa/carInsureHistory/add";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:carInsureHistory:edit")
	String edit(@PathVariable("id") String id,Model model){
		CarInsureHistoryDO carInsureHistory = carInsureHistoryService.get(id);
		model.addAttribute("carInsureHistory", carInsureHistory);
	    return "oa/carInsureHistory/edit";
	}
	
	/**
	 * 新增保存接口
	 * @param carInsureHistory  CarInsureHistoryDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carInsureHistory", value = "保存实体信息", required = true, dataType = "CarInsureHistoryDO")
    })
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:carInsureHistory:add")
	public R save( CarInsureHistoryDO carInsureHistory){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			carInsureHistory.setCreator(loginInfo.getId());
			carInsureHistory.setCreatorby(loginInfo.getUsername());
			carInsureHistory.setCreatorName(loginInfo.getName());
			carInsureHistory.setCreateDeptid(loginInfo.getDeptId());
			carInsureHistory.setCreateDeptcode(loginInfo.getDeptId());
			carInsureHistory.setCreateDeptname(loginInfo.getDeptName());
			carInsureHistory.setCreateOrgid(null);
			carInsureHistory.setCreateOrgcode(null);
			carInsureHistory.setCreateOrgname(null);
		}
		carInsureHistory.setIsdelete(0);
		carInsureHistory.setCreateTime(new Date());
		if(carInsureHistoryService.save(carInsureHistory)>0){
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
	@RequiresPermissions("oa:carInsureHistory:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<CarInsureHistoryDO> list = new ArrayList<CarInsureHistoryDO>();
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
				CarInsureHistoryDO carInsureHistory;
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
					  						
					  						 /** 用车序号 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String useSerialNum = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(useSerialNum)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,用车序号未填写)");
						} 
					  						
					  						 /** 车牌号码 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String carNum = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(carNum)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,车牌号码未填写)");
						} 
					  						
					  						 /** 变速箱类别（1:手动挡、2：自动档，4：手自一体） */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String carType = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(carType)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,变速箱类别（1:手动挡、2：自动档，4：手自一体）未填写)");
						} 
					  						
					  						 /** 车身颜色 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String carNumColor = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(carNumColor)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,车身颜色未填写)");
						} 
					  						
					  						 /** 保险公司 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String company = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(company)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,保险公司未填写)");
						} 
					  						
					  						 /** 投保时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String insureDate = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(insureDate)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,投保时间未填写)");
						} 
					  						
					  						 /** 投保金额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String amount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(amount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,投保金额未填写)");
						} 
					  						
					  						 /** 保险开始时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String beginDate = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(beginDate)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,保险开始时间未填写)");
						} 
					  						
					  						 /** 保险结束时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String endDate = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(endDate)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,保险结束时间未填写)");
						} 
					  						
					  						 /** 联系电话 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String phone = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(phone)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,联系电话未填写)");
						} 
					  					
					 
					carInsureHistory = new CarInsureHistoryDO();
					//carInsureHistory.setName(noNullName);

					//carInsureHistory = carInsureHistoryService.find(carInsureHistory);
					if (null == carInsureHistory) {
						carInsureHistory = new CarInsureHistoryDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：维修序号
						 */
						 
						 							 carInsureHistory.setSerialNum(serialNum)  ;
						 						
						 
					  						
					  						/**
						 * 设置：用车序号
						 */
						 
						 							 carInsureHistory.setUseSerialNum(useSerialNum)  ;
						 						
						 
					  						
					  						/**
						 * 设置：车牌号码
						 */
						 
						 							 carInsureHistory.setCarNum(carNum)  ;
						 						
						 
					  						
					  						/**
						 * 设置：变速箱类别（1:手动挡、2：自动档，4：手自一体）
						 */
						 
						 						 	 carInsureHistory.setCarType(Integer.parseInt(carType))  ;
						 						
						 
					  						
					  						/**
						 * 设置：车身颜色
						 */
						 
						 							 carInsureHistory.setCarNumColor(carNumColor)  ;
						 						
						 
					  						
					  						/**
						 * 设置：保险公司
						 */
						 
						 							 carInsureHistory.setCompany(company)  ;
						 						
						 
					  						
					  						/**
						 * 设置：投保时间
						 */
						 
						 						 	 carInsureHistory.setInsureDate(DateUtils.stringToDate(insureDate))  ;
						 						
						 
					  						
					  						/**
						 * 设置：投保金额
						 */
						 
						 							 carInsureHistory.setAmount(new BigDecimal(amount))  ;
						 						
						 
					  						
					  						/**
						 * 设置：保险开始时间
						 */
						 
						 						 	 carInsureHistory.setBeginDate(DateUtils.stringToDate(beginDate))  ;
						 						
						 
					  						
					  						/**
						 * 设置：保险结束时间
						 */
						 
						 						 	 carInsureHistory.setEndDate(DateUtils.stringToDate(endDate))  ;
						 						
						 
					  						
					  						/**
						 * 设置：联系电话
						 */
						 
						 							 carInsureHistory.setPhone(phone)  ;
						 						
						 
					  					
					carInsureHistory.setCreateTime(new Date());
					carInsureHistory.setIsdelete(0);

					list.add(carInsureHistory);
				}
				UserDO loginInfo = super.getLoginUser();
				carInsureHistoryService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	/**
	 * 修改保存接口
	 * @param carInsureHistory  CarInsureHistoryDO
	 * @return
	 */
	 @ApiOperation(value="修改保存接口", notes="修改保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carInsureHistory", value = "保存实体信息", required = true, dataType = "CarInsureHistoryDO")
    })
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:carInsureHistory:edit")
	public R update( CarInsureHistoryDO carInsureHistory){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			carInsureHistory.setUpdator(loginInfo.getId());
			carInsureHistory.setUpdatorby(loginInfo.getUsername());
			carInsureHistory.setUpdatorName(loginInfo.getName());
		}
		carInsureHistory.setIsdelete(0);
		carInsureHistory.setLastTime(new Date());
		carInsureHistoryService.update(carInsureHistory);
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
	@RequiresPermissions("oa:carInsureHistory:remove")
	public R remove( String id){
		if(carInsureHistoryService.remove(id)>0){
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
	@RequiresPermissions("oa:carInsureHistory:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		carInsureHistoryService.batchRemove(ids);
		return R.ok();
	}
	
}
