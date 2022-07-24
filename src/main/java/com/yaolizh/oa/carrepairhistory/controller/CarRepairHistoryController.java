package com.yaolizh.oa.carrepairhistory.controller;

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
import com.yaolizh.oa.carrepairhistory.domain.CarRepairHistoryDO;
import com.yaolizh.oa.carrepairhistory.service.CarRepairHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 车辆维修维保记录信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:05
 */
@Api(value="车辆维修维保记录信息") 
@Controller
@RequestMapping("/oa/carRepairHistory")
public class CarRepairHistoryController extends BaseController {
	@Autowired
	private CarRepairHistoryService carRepairHistoryService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:carRepairHistory:carRepairHistory")
	String CarRepairHistory(){
	    return "oa/carRepairHistory/carRepairHistory";
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
	@RequiresPermissions("oa:carRepairHistory:carRepairHistory")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<CarRepairHistoryDO> carRepairHistoryList = (Page<CarRepairHistoryDO>) carRepairHistoryService.list(query);
		
		PageUtils pageUtils = new PageUtils(carRepairHistoryList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:carRepairHistory:add")
	String add(Model model){
		CarRepairHistoryDO carRepairHistory = new CarRepairHistoryDO();
		model.addAttribute("carRepairHistory", carRepairHistory);
	    return "oa/carRepairHistory/addOrUpdate";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:carRepairHistory:edit")
	String edit(@PathVariable("id") String id,Model model){
		CarRepairHistoryDO carRepairHistory = carRepairHistoryService.get(id);
		model.addAttribute("carRepairHistory", carRepairHistory);
	    return "oa/carRepairHistory/addOrUpdate";
	}
	
	/**
	 * 新增保存接口
	 * @param carRepairHistory  CarRepairHistoryDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carRepairHistory", value = "保存实体信息", required = true, dataType = "CarRepairHistoryDO")
    })
	@ResponseBody
	@PostMapping("/saveOrUpdate")
	@RequiresPermissions( value={"oa:carRepairHistory:add","oa:carRepairHistory:edit"}, logical=Logical.OR)
	public R saveOrUpdate( CarRepairHistoryDO carRepairHistory){
	UserDO loginInfo = super.getLoginUser();
		carRepairHistoryService.saveOrUpdate(carRepairHistory);
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
	@RequiresPermissions("oa:carRepairHistory:remove")
	public R remove( String id){
		if(carRepairHistoryService.remove(id)>0){
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
	@RequiresPermissions("oa:carRepairHistory:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		carRepairHistoryService.batchRemove(ids);
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
	@RequiresPermissions("oa:carRepairHistory:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<CarRepairHistoryDO> list = new ArrayList<CarRepairHistoryDO>();
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
				CarRepairHistoryDO carRepairHistory;
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

						
					  					  						
					  						 /** 维修/保养(1:维修,2:保养) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String type = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(type)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,维修/保养(1:维修,2:保养)未填写)");
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
					  						
					  						 /** 维修厂家 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String repairShop = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(repairShop)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,维修厂家未填写)");
						} 
					  						
					  						 /** 维修地址 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String repairAddr = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(repairAddr)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,维修地址未填写)");
						} 
					  						
					  						 /** 维修金额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String repairAmount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(repairAmount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,维修金额未填写)");
						} 
					  						
					  						 /** 保险报销金额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String insuranceAmount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(insuranceAmount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,保险报销金额未填写)");
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
					  						
					  					  						
					  					  					
					 
					carRepairHistory = new CarRepairHistoryDO();
					//carRepairHistory.setName(noNullName);

					carRepairHistory = carRepairHistoryService.findOne(carRepairHistory);
					if (null == carRepairHistory) {
						carRepairHistory = new CarRepairHistoryDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：维修/保养(1:维修,2:保养)
						 */
						 
						 						 	 carRepairHistory.setType(Integer.parseInt(type))  ;
						 						
						 
					  						
					  						/**
						 * 设置：维修序号
						 */
						 
						 							 carRepairHistory.setSerialNum(serialNum)  ;
						 						
						 
					  						
					  						/**
						 * 设置：用车序号
						 */
						 
						 							 carRepairHistory.setUseSerialNum(useSerialNum)  ;
						 						
						 
					  						
					  						/**
						 * 设置：车牌号码
						 */
						 
						 							 carRepairHistory.setCarNum(carNum)  ;
						 						
						 
					  						
					  						/**
						 * 设置：变速箱类别（1:手动挡、2：自动档，4：手自一体）
						 */
						 
						 						 	 carRepairHistory.setCarType(Integer.parseInt(carType))  ;
						 						
						 
					  						
					  						/**
						 * 设置：车身颜色
						 */
						 
						 							 carRepairHistory.setCarNumColor(carNumColor)  ;
						 						
						 
					  						
					  						/**
						 * 设置：状态(1:待维修,2:维修中,4:维修完成,8:报废)
						 */
						 
						 						 	 carRepairHistory.setState(Integer.parseInt(state))  ;
						 						
						 
					  						
					  						/**
						 * 设置：维修原因
						 */
						 
						 							 carRepairHistory.setReason(reason)  ;
						 						
						 
					  						
					  						/**
						 * 设置：维修厂家
						 */
						 
						 							 carRepairHistory.setRepairShop(repairShop)  ;
						 						
						 
					  						
					  						/**
						 * 设置：维修地址
						 */
						 
						 							 carRepairHistory.setRepairAddr(repairAddr)  ;
						 						
						 
					  						
					  						/**
						 * 设置：维修金额
						 */
						 
						 							 carRepairHistory.setRepairAmount(new BigDecimal(repairAmount))  ;
						 						
						 
					  						
					  						/**
						 * 设置：保险报销金额
						 */
						 
						 							 carRepairHistory.setInsuranceAmount(new BigDecimal(insuranceAmount))  ;
						 						
						 
					  						
					  						/**
						 * 设置：申请维修人
						 */
						 
						 							 carRepairHistory.setApplyPerson(applyPerson)  ;
						 						
						 
					  						
					  						/**
						 * 设置：申请维修时间
						 */
						 
						 						 	 carRepairHistory.setApplyTime(DateUtils.stringToDate(applyTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：维修时间
						 */
						 
						 						 	 carRepairHistory.setRepairTime(DateUtils.stringToDate(repairTime))  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  						/**
						 * 设置：
						 */
						 
						 							 carRepairHistory.setRemark(remark)  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  					
					carRepairHistory.setCreateTime(new Date());
					carRepairHistory.setIsdelete(0);

					list.add(carRepairHistory);
				}
				UserDO loginInfo = super.getLoginUser();
				carRepairHistoryService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	
}
