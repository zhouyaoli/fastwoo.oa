package com.yaolizh.oa.emp.controller;

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
import com.yaolizh.oa.emp.domain.EmpDO;
import com.yaolizh.oa.emp.service.EmpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 职工档案信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:57
 */
@Api(value="职工档案信息") 
@Controller
@RequestMapping("/oa/emp")
public class EmpController extends BaseController {
	@Autowired
	private EmpService empService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:emp:emp")
	String Emp(){
	    return "oa/emp/emp";
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
	@RequiresPermissions("oa:emp:emp")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<EmpDO> empList = (Page<EmpDO>) empService.list(query);
		
		PageUtils pageUtils = new PageUtils(empList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:emp:add")
	String add(){
	    return "oa/emp/add";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:emp:edit")
	String edit(@PathVariable("id") String id,Model model){
		EmpDO emp = empService.get(id);
		model.addAttribute("emp", emp);
	    return "oa/emp/edit";
	}
	
	/**
	 * 新增保存接口
	 * @param emp  EmpDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "emp", value = "保存实体信息", required = true, dataType = "EmpDO")
    })
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:emp:add")
	public R save( EmpDO emp){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			emp.setCreator(loginInfo.getId());
			emp.setCreatorby(loginInfo.getUsername());
			emp.setCreatorName(loginInfo.getName());
			emp.setCreateDeptid(loginInfo.getDeptId());
			emp.setCreateDeptcode(loginInfo.getDeptId());
			emp.setCreateDeptname(loginInfo.getDeptName());
			emp.setCreateOrgid(null);
			emp.setCreateOrgcode(null);
			emp.setCreateOrgname(null);
		}
		emp.setIsdelete(0);
		emp.setCreateTime(new Date());
		if(empService.save(emp)>0){
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
	@RequiresPermissions("oa:emp:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<EmpDO> list = new ArrayList<EmpDO>();
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
				EmpDO emp;
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
					  						
					  						 /** 部门id */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String deptId = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(deptId)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,部门id未填写)");
						} 
					  						
					  						 /** 部门 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String deptName = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(deptName)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,部门未填写)");
						} 
					  						
					  						 /** 年假天数 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String yearRestNum = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(yearRestNum)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,年假天数未填写)");
						} 
					  						
					  						 /** 已休息年假天数 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String hasRestYearRestNum = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(hasRestYearRestNum)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,已休息年假天数未填写)");
						} 
					  						
					  						 /** 工资卡 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String wageCard = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(wageCard)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,工资卡未填写)");
						} 
					  						
					  						 /** 福利卡 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String freecaCard = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(freecaCard)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,福利卡未填写)");
						} 
					  						
					  						 /** 职务 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String post = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(post)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,职务未填写)");
						} 
					  						
					  						 /** 员工状态(1在职，2:离职) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String state = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(state)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,员工状态(1在职，2:离职)未填写)");
						} 
					  						
					  						 /** 电话 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String phone = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(phone)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,电话未填写)");
						} 
					  						
					  						 /** 入职日期 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String inDate = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(inDate)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,入职日期未填写)");
						} 
					  						
					  						 /** 转正日期 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String formalDate = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(formalDate)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,转正日期未填写)");
						} 
					  						
					  						 /** 工作时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String workDate = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(workDate)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,工作时间未填写)");
						} 
					  						
					  						 /** qq */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String qq = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(qq)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,qq未填写)");
						} 
					  						
					  						 /** 性别 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String sex = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(sex)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,性别未填写)");
						} 
					  						
					  						 /** 生日 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String birthdayDay = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(birthdayDay)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,生日未填写)");
						} 
					  						
					  						 /** 是否结婚 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String hasMarry = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(hasMarry)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,是否结婚未填写)");
						} 
					  						
					  						 /** 政治面貌 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String policeFace = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(policeFace)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,政治面貌未填写)");
						} 
					  						
					  						 /** 户口性质 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String houseType = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(houseType)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,户口性质未填写)");
						} 
					  						
					  						 /** 学校 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String school = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(school)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,学校未填写)");
						} 
					  						
					  						 /** 学历 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String educate = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(educate)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,学历未填写)");
						} 
					  						
					  						 /** 专业 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String major = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(major)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,专业未填写)");
						} 
					  						
					  						 /** 名族 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String nation = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(nation)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,名族未填写)");
						} 
					  						
					  						 /** 身份证地址 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String idCardAddr = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(idCardAddr)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,身份证地址未填写)");
						} 
					  						
					  						 /** 联系地址 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String contactAddr = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(contactAddr)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,联系地址未填写)");
						} 
					  						
					  						 /** 紧急联系人 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String urgentName = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(urgentName)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,紧急联系人未填写)");
						} 
					  						
					  						 /** 紧急联系电话 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String urgentPhone = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(urgentPhone)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,紧急联系电话未填写)");
						} 
					  					
					 
					emp = new EmpDO();
					//emp.setName(noNullName);

					//emp = empService.find(emp);
					if (null == emp) {
						emp = new EmpDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：用户id
						 */
						 
						 							 emp.setUserId(userId)  ;
						 						
						 
					  						
					  						/**
						 * 设置：员工编号
						 */
						 
						 							 emp.setNo(no)  ;
						 						
						 
					  						
					  						/**
						 * 设置：名字
						 */
						 
						 							 emp.setName(name)  ;
						 						
						 
					  						
					  						/**
						 * 设置：身份证
						 */
						 
						 							 emp.setIdCard(idCard)  ;
						 						
						 
					  						
					  						/**
						 * 设置：部门id
						 */
						 
						 							 emp.setDeptId(deptId)  ;
						 						
						 
					  						
					  						/**
						 * 设置：部门
						 */
						 
						 							 emp.setDeptName(deptName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：年假天数
						 */
						 
						 						 	 emp.setYearRestNum(Integer.parseInt(yearRestNum))  ;
						 						
						 
					  						
					  						/**
						 * 设置：已休息年假天数
						 */
						 
						 						 	 emp.setHasRestYearRestNum(Integer.parseInt(hasRestYearRestNum))  ;
						 						
						 
					  						
					  						/**
						 * 设置：工资卡
						 */
						 
						 							 emp.setWageCard(wageCard)  ;
						 						
						 
					  						
					  						/**
						 * 设置：福利卡
						 */
						 
						 							 emp.setFreecaCard(freecaCard)  ;
						 						
						 
					  						
					  						/**
						 * 设置：职务
						 */
						 
						 							 emp.setPost(post)  ;
						 						
						 
					  						
					  						/**
						 * 设置：员工状态(1在职，2:离职)
						 */
						 
						 						 	 emp.setState(Integer.parseInt(state))  ;
						 						
						 
					  						
					  						/**
						 * 设置：电话
						 */
						 
						 							 emp.setPhone(phone)  ;
						 						
						 
					  						
					  						/**
						 * 设置：入职日期
						 */
						 
						 						 	 emp.setInDate(DateUtils.stringToDate(inDate))  ;
						 						
						 
					  						
					  						/**
						 * 设置：转正日期
						 */
						 
						 						 	 emp.setFormalDate(DateUtils.stringToDate(formalDate))  ;
						 						
						 
					  						
					  						/**
						 * 设置：工作时间
						 */
						 
						 						 	 emp.setWorkDate(DateUtils.stringToDate(workDate))  ;
						 						
						 
					  						
					  						/**
						 * 设置：qq
						 */
						 
						 							 emp.setQq(qq)  ;
						 						
						 
					  						
					  						/**
						 * 设置：性别
						 */
						 
						 						 	 emp.setSex(Integer.parseInt(sex))  ;
						 						
						 
					  						
					  						/**
						 * 设置：生日
						 */
						 
						 						 	 emp.setBirthdayDay(DateUtils.stringToDate(birthdayDay))  ;
						 						
						 
					  						
					  						/**
						 * 设置：是否结婚
						 */
						 
						 						 	 emp.setHasMarry(Integer.parseInt(hasMarry))  ;
						 						
						 
					  						
					  						/**
						 * 设置：政治面貌
						 */
						 
						 							 emp.setPoliceFace(policeFace)  ;
						 						
						 
					  						
					  						/**
						 * 设置：户口性质
						 */
						 
						 							 emp.setHouseType(houseType)  ;
						 						
						 
					  						
					  						/**
						 * 设置：学校
						 */
						 
						 							 emp.setSchool(school)  ;
						 						
						 
					  						
					  						/**
						 * 设置：学历
						 */
						 
						 							 emp.setEducate(educate)  ;
						 						
						 
					  						
					  						/**
						 * 设置：专业
						 */
						 
						 							 emp.setMajor(major)  ;
						 						
						 
					  						
					  						/**
						 * 设置：名族
						 */
						 
						 							 emp.setNation(nation)  ;
						 						
						 
					  						
					  						/**
						 * 设置：身份证地址
						 */
						 
						 							 emp.setIdCardAddr(idCardAddr)  ;
						 						
						 
					  						
					  						/**
						 * 设置：联系地址
						 */
						 
						 							 emp.setContactAddr(contactAddr)  ;
						 						
						 
					  						
					  						/**
						 * 设置：紧急联系人
						 */
						 
						 							 emp.setUrgentName(urgentName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：紧急联系电话
						 */
						 
						 							 emp.setUrgentPhone(urgentPhone)  ;
						 						
						 
					  					
					emp.setCreateTime(new Date());
					emp.setIsdelete(0);

					list.add(emp);
				}
				UserDO loginInfo = super.getLoginUser();
				empService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	/**
	 * 修改保存接口
	 * @param emp  EmpDO
	 * @return
	 */
	 @ApiOperation(value="修改保存接口", notes="修改保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "emp", value = "保存实体信息", required = true, dataType = "EmpDO")
    })
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:emp:edit")
	public R update( EmpDO emp){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			emp.setUpdator(loginInfo.getId());
			emp.setUpdatorby(loginInfo.getUsername());
			emp.setUpdatorName(loginInfo.getName());
		}
		emp.setIsdelete(0);
		emp.setLastTime(new Date());
		empService.update(emp);
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
	@RequiresPermissions("oa:emp:remove")
	public R remove( String id){
		if(empService.remove(id)>0){
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
	@RequiresPermissions("oa:emp:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		empService.batchRemove(ids);
		return R.ok();
	}
	
}
