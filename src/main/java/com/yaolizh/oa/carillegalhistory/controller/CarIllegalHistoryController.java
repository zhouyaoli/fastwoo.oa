package com.yaolizh.oa.carillegalhistory.controller;

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
import com.yaolizh.oa.carillegalhistory.domain.CarIllegalHistoryDO;
import com.yaolizh.oa.carillegalhistory.service.CarIllegalHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 车辆违章记录信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:59
 */
@Api(value="车辆违章记录信息") 
@Controller
@RequestMapping("/oa/carIllegalHistory")
public class CarIllegalHistoryController extends BaseController {
	@Autowired
	private CarIllegalHistoryService carIllegalHistoryService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:carIllegalHistory:carIllegalHistory")
	String CarIllegalHistory(){
	    return "oa/carIllegalHistory/carIllegalHistory";
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
	@RequiresPermissions("oa:carIllegalHistory:carIllegalHistory")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<CarIllegalHistoryDO> carIllegalHistoryList = (Page<CarIllegalHistoryDO>) carIllegalHistoryService.list(query);
		
		PageUtils pageUtils = new PageUtils(carIllegalHistoryList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:carIllegalHistory:add")
	String add(){
	    return "oa/carIllegalHistory/add";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:carIllegalHistory:edit")
	String edit(@PathVariable("id") String id,Model model){
		CarIllegalHistoryDO carIllegalHistory = carIllegalHistoryService.get(id);
		model.addAttribute("carIllegalHistory", carIllegalHistory);
	    return "oa/carIllegalHistory/edit";
	}
	
	/**
	 * 新增保存接口
	 * @param carIllegalHistory  CarIllegalHistoryDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carIllegalHistory", value = "保存实体信息", required = true, dataType = "CarIllegalHistoryDO")
    })
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:carIllegalHistory:add")
	public R save( CarIllegalHistoryDO carIllegalHistory){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			carIllegalHistory.setCreator(loginInfo.getId());
			carIllegalHistory.setCreatorby(loginInfo.getUsername());
			carIllegalHistory.setCreatorName(loginInfo.getName());
			carIllegalHistory.setCreateDeptid(loginInfo.getDeptId());
			carIllegalHistory.setCreateDeptcode(loginInfo.getDeptId());
			carIllegalHistory.setCreateDeptname(loginInfo.getDeptName());
			carIllegalHistory.setCreateOrgid(null);
			carIllegalHistory.setCreateOrgcode(null);
			carIllegalHistory.setCreateOrgname(null);
		}
		carIllegalHistory.setIsdelete(0);
		carIllegalHistory.setCreateTime(new Date());
		if(carIllegalHistoryService.save(carIllegalHistory)>0){
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
	@RequiresPermissions("oa:carIllegalHistory:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<CarIllegalHistoryDO> list = new ArrayList<CarIllegalHistoryDO>();
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
				CarIllegalHistoryDO carIllegalHistory;
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

						
					  					  						
					  						 /** 违章序号 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String serialNum = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(serialNum)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,违章序号未填写)");
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
					  						
					  						 /** 违章描述 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String descript = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(descript)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,违章描述未填写)");
						} 
					  						
					  						 /** 申请违章人 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String faultPerson = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(faultPerson)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,申请违章人未填写)");
						} 
					  						
					  						 /** 违章时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String faultTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(faultTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,违章时间未填写)");
						} 
					  						
					  						 /** 违章地址 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String faultAddr = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(faultAddr)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,违章地址未填写)");
						} 
					  						
					  						 /** 扣分 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String penaltyScore = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(penaltyScore)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,扣分未填写)");
						} 
					  						
					  						 /** 罚款金额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String penaltyAmount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(penaltyAmount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,罚款金额未填写)");
						} 
					  					
					 
					carIllegalHistory = new CarIllegalHistoryDO();
					//carIllegalHistory.setName(noNullName);

					//carIllegalHistory = carIllegalHistoryService.find(carIllegalHistory);
					if (null == carIllegalHistory) {
						carIllegalHistory = new CarIllegalHistoryDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：违章序号
						 */
						 
						 							 carIllegalHistory.setSerialNum(serialNum)  ;
						 						
						 
					  						
					  						/**
						 * 设置：用车序号
						 */
						 
						 							 carIllegalHistory.setUseSerialNum(useSerialNum)  ;
						 						
						 
					  						
					  						/**
						 * 设置：车牌号码
						 */
						 
						 							 carIllegalHistory.setCarNum(carNum)  ;
						 						
						 
					  						
					  						/**
						 * 设置：变速箱类别（1:手动挡、2：自动档，4：手自一体）
						 */
						 
						 						 	 carIllegalHistory.setCarType(Integer.parseInt(carType))  ;
						 						
						 
					  						
					  						/**
						 * 设置：车身颜色
						 */
						 
						 							 carIllegalHistory.setCarNumColor(carNumColor)  ;
						 						
						 
					  						
					  						/**
						 * 设置：违章描述
						 */
						 
						 							 carIllegalHistory.setDescript(descript)  ;
						 						
						 
					  						
					  						/**
						 * 设置：申请违章人
						 */
						 
						 							 carIllegalHistory.setFaultPerson(faultPerson)  ;
						 						
						 
					  						
					  						/**
						 * 设置：违章时间
						 */
						 
						 						 	 carIllegalHistory.setFaultTime(DateUtils.stringToDate(faultTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：违章地址
						 */
						 
						 							 carIllegalHistory.setFaultAddr(faultAddr)  ;
						 						
						 
					  						
					  						/**
						 * 设置：扣分
						 */
						 
						 							 carIllegalHistory.setPenaltyScore(penaltyScore)  ;
						 						
						 
					  						
					  						/**
						 * 设置：罚款金额
						 */
						 
						 							 carIllegalHistory.setPenaltyAmount(penaltyAmount)  ;
						 						
						 
					  					
					carIllegalHistory.setCreateTime(new Date());
					carIllegalHistory.setIsdelete(0);

					list.add(carIllegalHistory);
				}
				UserDO loginInfo = super.getLoginUser();
				carIllegalHistoryService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	/**
	 * 修改保存接口
	 * @param carIllegalHistory  CarIllegalHistoryDO
	 * @return
	 */
	 @ApiOperation(value="修改保存接口", notes="修改保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carIllegalHistory", value = "保存实体信息", required = true, dataType = "CarIllegalHistoryDO")
    })
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:carIllegalHistory:edit")
	public R update( CarIllegalHistoryDO carIllegalHistory){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			carIllegalHistory.setUpdator(loginInfo.getId());
			carIllegalHistory.setUpdatorby(loginInfo.getUsername());
			carIllegalHistory.setUpdatorName(loginInfo.getName());
		}
		carIllegalHistory.setIsdelete(0);
		carIllegalHistory.setLastTime(new Date());
		carIllegalHistoryService.update(carIllegalHistory);
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
	@RequiresPermissions("oa:carIllegalHistory:remove")
	public R remove( String id){
		if(carIllegalHistoryService.remove(id)>0){
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
	@RequiresPermissions("oa:carIllegalHistory:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		carIllegalHistoryService.batchRemove(ids);
		return R.ok();
	}
	
}
