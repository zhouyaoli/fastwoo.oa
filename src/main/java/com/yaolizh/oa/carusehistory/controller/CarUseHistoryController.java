package com.yaolizh.oa.carusehistory.controller;

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
import com.yaolizh.oa.carusehistory.domain.CarUseHistoryDO;
import com.yaolizh.oa.carusehistory.service.CarUseHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 车辆使用记录信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:05
 */
@Api(value="车辆使用记录信息") 
@Controller
@RequestMapping("/oa/carUseHistory")
public class CarUseHistoryController extends BaseController {
	@Autowired
	private CarUseHistoryService carUseHistoryService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:carUseHistory:carUseHistory")
	String CarUseHistory(){
	    return "oa/carUseHistory/carUseHistory";
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
	@RequiresPermissions("oa:carUseHistory:carUseHistory")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<CarUseHistoryDO> carUseHistoryList = (Page<CarUseHistoryDO>) carUseHistoryService.list(query);
		
		PageUtils pageUtils = new PageUtils(carUseHistoryList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:carUseHistory:add")
	String add(Model model){
		CarUseHistoryDO carUseHistory = new CarUseHistoryDO();
		model.addAttribute("carUseHistory", carUseHistory);
	    return "oa/carUseHistory/addOrUpdate";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:carUseHistory:edit")
	String edit(@PathVariable("id") String id,Model model){
		CarUseHistoryDO carUseHistory = carUseHistoryService.get(id);
		model.addAttribute("carUseHistory", carUseHistory);
	    return "oa/carUseHistory/addOrUpdate";
	}
	
	/**
	 * 新增保存接口
	 * @param carUseHistory  CarUseHistoryDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carUseHistory", value = "保存实体信息", required = true, dataType = "CarUseHistoryDO")
    })
	@ResponseBody
	@PostMapping("/saveOrUpdate")
	@RequiresPermissions( value={"oa:carUseHistory:add","oa:carUseHistory:edit"}, logical=Logical.OR)
	public R saveOrUpdate( CarUseHistoryDO carUseHistory){
	UserDO loginInfo = super.getLoginUser();
		carUseHistoryService.saveOrUpdate(carUseHistory);
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
	@RequiresPermissions("oa:carUseHistory:remove")
	public R remove( String id){
		if(carUseHistoryService.remove(id)>0){
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
	@RequiresPermissions("oa:carUseHistory:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		carUseHistoryService.batchRemove(ids);
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
	@RequiresPermissions("oa:carUseHistory:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<CarUseHistoryDO> list = new ArrayList<CarUseHistoryDO>();
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
				CarUseHistoryDO carUseHistory;
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
					  						
					  						 /** 车牌号码 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String carNum = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(carNum)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,车牌号码未填写)");
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
					  						
					  						 /** 用车原因 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String useReason = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(useReason)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,用车原因未填写)");
						} 
					  						
					  						 /** 目的地 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String destination = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(destination)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,目的地未填写)");
						} 
					  						
					  						 /** 开始里程数 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String beginMileage = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(beginMileage)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,开始里程数未填写)");
						} 
					  						
					  						 /** 结束里程数 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String endMileage = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(endMileage)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,结束里程数未填写)");
						} 
					  						
					  						 /** 开始油量 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String beginOil = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(beginOil)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,开始油量未填写)");
						} 
					  						
					  						 /** 结束油量 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String endOil = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(endOil)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,结束油量未填写)");
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
					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  						 /**  */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String remark = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(remark)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,未填写)");
						} 
					  						
					  					  						
					  					  					
					 
					carUseHistory = new CarUseHistoryDO();
					//carUseHistory.setName(noNullName);

					carUseHistory = carUseHistoryService.findOne(carUseHistory);
					if (null == carUseHistory) {
						carUseHistory = new CarUseHistoryDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：借出序号
						 */
						 
						 							 carUseHistory.setSerialNum(serialNum)  ;
						 						
						 
					  						
					  						/**
						 * 设置：车牌号码
						 */
						 
						 							 carUseHistory.setCarNum(carNum)  ;
						 						
						 
					  						
					  						/**
						 * 设置：是否归还
						 */
						 
						 						 	 carUseHistory.setHasReturn(Integer.parseInt(hasReturn))  ;
						 						
						 
					  						
					  						/**
						 * 设置：使用人
						 */
						 
						 							 carUseHistory.setUsePerson(usePerson)  ;
						 						
						 
					  						
					  						/**
						 * 设置：用车原因
						 */
						 
						 							 carUseHistory.setUseReason(useReason)  ;
						 						
						 
					  						
					  						/**
						 * 设置：目的地
						 */
						 
						 							 carUseHistory.setDestination(destination)  ;
						 						
						 
					  						
					  						/**
						 * 设置：开始里程数
						 */
						 
						 						 	 carUseHistory.setBeginMileage(Integer.parseInt(beginMileage))  ;
						 						
						 
					  						
					  						/**
						 * 设置：结束里程数
						 */
						 
						 						 	 carUseHistory.setEndMileage(Integer.parseInt(endMileage))  ;
						 						
						 
					  						
					  						/**
						 * 设置：开始油量
						 */
						 
						 							 carUseHistory.setBeginOil(beginOil)  ;
						 						
						 
					  						
					  						/**
						 * 设置：结束油量
						 */
						 
						 							 carUseHistory.setEndOil(endOil)  ;
						 						
						 
					  						
					  						/**
						 * 设置：开始使用时间
						 */
						 
						 						 	 carUseHistory.setBeginUseTime(DateUtils.stringToDate(beginUseTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：归还时间
						 */
						 
						 						 	 carUseHistory.setEndUseTime(DateUtils.stringToDate(endUseTime))  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  						/**
						 * 设置：
						 */
						 
						 							 carUseHistory.setRemark(remark)  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  					
					carUseHistory.setCreateTime(new Date());
					carUseHistory.setIsdelete(0);

					list.add(carUseHistory);
				}
				UserDO loginInfo = super.getLoginUser();
				carUseHistoryService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	
}
