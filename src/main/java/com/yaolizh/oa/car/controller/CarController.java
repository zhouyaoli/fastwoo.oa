package com.yaolizh.oa.car.controller;

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
import com.yaolizh.oa.car.domain.CarDO;
import com.yaolizh.oa.car.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 车辆信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:28:59
 */
@Api(value="车辆信息") 
@Controller
@RequestMapping("/oa/car")
public class CarController extends BaseController {
	@Autowired
	private CarService carService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:car:car")
	String Car(){
	    return "oa/car/car";
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
	@RequiresPermissions("oa:car:car")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<CarDO> carList = (Page<CarDO>) carService.list(query);
		
		PageUtils pageUtils = new PageUtils(carList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:car:add")
	String add(Model model){
		CarDO car = new CarDO();
		model.addAttribute("car", car);
	    return "oa/car/addOrUpdate";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:car:edit")
	String edit(@PathVariable("id") String id,Model model){
		CarDO car = carService.get(id);
		model.addAttribute("car", car);
	    return "oa/car/addOrUpdate";
	}
	
	/**
	 * 新增保存接口
	 * @param car  CarDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "car", value = "保存实体信息", required = true, dataType = "CarDO")
    })
	@ResponseBody
	@PostMapping("/saveOrUpdate")
	@RequiresPermissions( value={"oa:car:add","oa:car:edit"}, logical=Logical.OR)
	public R saveOrUpdate( CarDO car){
	UserDO loginInfo = super.getLoginUser();
		carService.saveOrUpdate(car);
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
	@RequiresPermissions("oa:car:remove")
	public R remove( String id){
		if(carService.remove(id)>0){
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
	@RequiresPermissions("oa:car:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		carService.batchRemove(ids);
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
	@RequiresPermissions("oa:car:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<CarDO> list = new ArrayList<CarDO>();
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
				CarDO car;
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

						
					  					  						
					  						 /** 状态(1:可以使用,2:使用中,4:保修,8:报废) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String state = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(state)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,状态(1:可以使用,2:使用中,4:保修,8:报废)未填写)");
						} 
					  						
					  						 /** 车牌号码 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String carNum = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(carNum)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,车牌号码未填写)");
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
					  						
					  						 /** 号牌种类(1:小型汽车号牌，2:大型汽车号牌，4:普通摩托车号牌，8:轻便摩托车号牌，16:低速车号牌，32:挂车号牌) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String carNumType = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(carNumType)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,号牌种类(1:小型汽车号牌，2:大型汽车号牌，4:普通摩托车号牌，8:轻便摩托车号牌，16:低速车号牌，32:挂车号牌)未填写)");
						} 
					  						
					  						 /** 变速箱类别（1:手动挡、2：自动档，4：手自一体） */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String carType = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(carType)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,变速箱类别（1:手动挡、2：自动档，4：手自一体）未填写)");
						} 
					  						
					  						 /** 车身颜色(1:黑,2:白,4:红,8:蓝) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String carNumColor = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(carNumColor)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,车身颜色(1:黑,2:白,4:红,8:蓝)未填写)");
						} 
					  						
					  						 /** 车身颜色(1:黑,2:白,4:红,8:蓝) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String bodyColor = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(bodyColor)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,车身颜色(1:黑,2:白,4:红,8:蓝)未填写)");
						} 
					  						
					  						 /** 车架号 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String vin = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(vin)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,车架号未填写)");
						} 
					  						
					  						 /** 厂牌型号 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String factoryModel = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(factoryModel)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,厂牌型号未填写)");
						} 
					  						
					  						 /** 购买时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String buyDate = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(buyDate)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,购买时间未填写)");
						} 
					  						
					  						 /** 购买金额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String buyAmount = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(buyAmount)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,购买金额未填写)");
						} 
					  						
					  						 /** 是否新车 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String hasNew = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(hasNew)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,是否新车未填写)");
						} 
					  						
					  						 /** 发动机号 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String engineNo = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(engineNo)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,发动机号未填写)");
						} 
					  						
					  						 /** 发动机型号 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String engineModel = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(engineModel)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,发动机型号未填写)");
						} 
					  						
					  						 /** 制动方式（1液压制动2气压制动） */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String brakeMode = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(brakeMode)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,制动方式（1液压制动2气压制动）未填写)");
						} 
					  						
					  						 /** 载人数 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String manned = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(manned)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,载人数未填写)");
						} 
					  						
					  						 /** 是否乘用（1乘用2非乘用） */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String hasManned = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(hasManned)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,是否乘用（1乘用2非乘用）未填写)");
						} 
					  						
					  						 /** 里程表读数 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String mileage = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(mileage)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,里程表读数未填写)");
						} 
					  						
					  						 /** 燃油种类(1:92,2:95,4:柴油:,8:纯电,16:油电混动) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String oilType = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(oilType)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,燃油种类(1:92,2:95,4:柴油:,8:纯电,16:油电混动)未填写)");
						} 
					  						
					  						 /** 整车长 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String carLen = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(carLen)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,整车长未填写)");
						} 
					  						
					  						 /** 整车宽 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String carWidth = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(carWidth)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,整车宽未填写)");
						} 
					  						
					  						 /** 整车高 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String carHeight = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(carHeight)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,整车高未填写)");
						} 
					  						
					  						 /** 出厂日期 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String productDate = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(productDate)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,出厂日期未填写)");
						} 
					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  						 /**  */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String remark = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(remark)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,未填写)");
						} 
					  						
					  					  						
					  					  					
					 
					car = new CarDO();
					//car.setName(noNullName);

					car = carService.findOne(car);
					if (null == car) {
						car = new CarDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：状态(1:可以使用,2:使用中,4:保修,8:报废)
						 */
						 
						 						 	 car.setState(Integer.parseInt(state))  ;
						 						
						 
					  						
					  						/**
						 * 设置：车牌号码
						 */
						 
						 							 car.setCarNum(carNum)  ;
						 						
						 
					  						
					  						/**
						 * 设置：使用人
						 */
						 
						 							 car.setUsePerson(usePerson)  ;
						 						
						 
					  						
					  						/**
						 * 设置：开始使用时间
						 */
						 
						 						 	 car.setBeginUseTime(DateUtils.stringToDate(beginUseTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：号牌种类(1:小型汽车号牌，2:大型汽车号牌，4:普通摩托车号牌，8:轻便摩托车号牌，16:低速车号牌，32:挂车号牌)
						 */
						 
						 						 	 car.setCarNumType(Integer.parseInt(carNumType))  ;
						 						
						 
					  						
					  						/**
						 * 设置：变速箱类别（1:手动挡、2：自动档，4：手自一体）
						 */
						 
						 						 	 car.setCarType(Integer.parseInt(carType))  ;
						 						
						 
					  						
					  						/**
						 * 设置：车身颜色(1:黑,2:白,4:红,8:蓝)
						 */
						 
						 							 car.setCarNumColor(carNumColor)  ;
						 						
						 
					  						
					  						/**
						 * 设置：车身颜色(1:黑,2:白,4:红,8:蓝)
						 */
						 
						 							 car.setBodyColor(bodyColor)  ;
						 						
						 
					  						
					  						/**
						 * 设置：车架号
						 */
						 
						 							 car.setVin(vin)  ;
						 						
						 
					  						
					  						/**
						 * 设置：厂牌型号
						 */
						 
						 							 car.setFactoryModel(factoryModel)  ;
						 						
						 
					  						
					  						/**
						 * 设置：购买时间
						 */
						 
						 						 	 car.setBuyDate(DateUtils.stringToDate(buyDate))  ;
						 						
						 
					  						
					  						/**
						 * 设置：购买金额
						 */
						 
						 							 car.setBuyAmount(new BigDecimal(buyAmount))  ;
						 						
						 
					  						
					  						/**
						 * 设置：是否新车
						 */
						 
						 						 	 car.setHasNew(Integer.parseInt(hasNew))  ;
						 						
						 
					  						
					  						/**
						 * 设置：发动机号
						 */
						 
						 							 car.setEngineNo(engineNo)  ;
						 						
						 
					  						
					  						/**
						 * 设置：发动机型号
						 */
						 
						 							 car.setEngineModel(engineModel)  ;
						 						
						 
					  						
					  						/**
						 * 设置：制动方式（1液压制动2气压制动）
						 */
						 
						 						 	 car.setBrakeMode(Integer.parseInt(brakeMode))  ;
						 						
						 
					  						
					  						/**
						 * 设置：载人数
						 */
						 
						 						 	 car.setManned(Integer.parseInt(manned))  ;
						 						
						 
					  						
					  						/**
						 * 设置：是否乘用（1乘用2非乘用）
						 */
						 
						 						 	 car.setHasManned(Integer.parseInt(hasManned))  ;
						 						
						 
					  						
					  						/**
						 * 设置：里程表读数
						 */
						 
						 						 	 car.setMileage(Integer.parseInt(mileage))  ;
						 						
						 
					  						
					  						/**
						 * 设置：燃油种类(1:92,2:95,4:柴油:,8:纯电,16:油电混动)
						 */
						 
						 						 	 car.setOilType(Integer.parseInt(oilType))  ;
						 						
						 
					  						
					  						/**
						 * 设置：整车长
						 */
						 
						 							 car.setCarLen(new BigDecimal(carLen))  ;
						 						
						 
					  						
					  						/**
						 * 设置：整车宽
						 */
						 
						 							 car.setCarWidth(new BigDecimal(carWidth))  ;
						 						
						 
					  						
					  						/**
						 * 设置：整车高
						 */
						 
						 							 car.setCarHeight(new BigDecimal(carHeight))  ;
						 						
						 
					  						
					  						/**
						 * 设置：出厂日期
						 */
						 
						 						 	 car.setProductDate(DateUtils.stringToDate(productDate))  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  						/**
						 * 设置：
						 */
						 
						 							 car.setRemark(remark)  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  					
					car.setCreateTime(new Date());
					car.setIsdelete(0);

					list.add(car);
				}
				UserDO loginInfo = super.getLoginUser();
				carService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	
}
