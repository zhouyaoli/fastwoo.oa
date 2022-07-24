package com.yaolizh.oa.empcontract.controller;

import java.io.IOException;
import java.io.InputStream;
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
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.yaolizh.fastwoo.common.controller.BaseController;
import com.yaolizh.fastwoo.common.utils.DateUtils;
import com.yaolizh.fastwoo.common.utils.PageUtils;
import com.yaolizh.fastwoo.common.utils.Query;
import com.yaolizh.fastwoo.common.utils.R;
import com.yaolizh.fastwoo.common.utils.StringUtils;
import com.yaolizh.fastwoo.system.domain.UserDO;
import com.yaolizh.oa.empcontract.domain.EmpContractDO;
import com.yaolizh.oa.empcontract.service.EmpContractService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * 职工合同信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:57
 */
@Api(value="职工合同信息") 
@Controller
@RequestMapping("/oa/empContract")
public class EmpContractController extends BaseController {
	@Autowired
	private EmpContractService empContractService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:empContract:empContract")
	String EmpContract(){
	    return "oa/empContract/empContract";
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
	@RequiresPermissions("oa:empContract:empContract")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<EmpContractDO> empContractList = (Page<EmpContractDO>) empContractService.list(query);
		
		PageUtils pageUtils = new PageUtils(empContractList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:empContract:add")
	String add(){
	    return "oa/empContract/add";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:empContract:edit")
	String edit(@PathVariable("id") String id,Model model){
		EmpContractDO empContract = empContractService.get(id);
		model.addAttribute("empContract", empContract);
	    return "oa/empContract/edit";
	}
	
	/**
	 * 新增保存接口
	 * @param empContract  EmpContractDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "empContract", value = "保存实体信息", required = true, dataType = "EmpContractDO")
    })
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:empContract:add")
	public R save( EmpContractDO empContract){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			empContract.setCreator(loginInfo.getId());
			empContract.setCreatorby(loginInfo.getUsername());
			empContract.setCreatorName(loginInfo.getName());
			empContract.setCreateDeptid(loginInfo.getDeptId());
			empContract.setCreateDeptcode(loginInfo.getDeptId());
			empContract.setCreateDeptname(loginInfo.getDeptName());
			empContract.setCreateOrgid(null);
			empContract.setCreateOrgcode(null);
			empContract.setCreateOrgname(null);
		}
		empContract.setIsdelete(0);
		empContract.setCreateTime(new Date());
		if(empContractService.save(empContract)>0){
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
	@RequiresPermissions("oa:empContract:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<EmpContractDO> list = new ArrayList<EmpContractDO>();
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
				EmpContractDO empContract;
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
					  						
					  						 /** 身份证号码 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String idCard = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(idCard)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,身份证号码未填写)");
						} 
					  						
					  						 /** 电话 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String phone = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(phone)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,电话未填写)");
						} 
					  						
					  						 /** 签订日期 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String signDate = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(signDate)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,签订日期未填写)");
						} 
					  						
					  						 /** 开始时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String beginDate = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(beginDate)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,开始时间未填写)");
						} 
					  						
					  						 /** 结束时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String endTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(endTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,结束时间未填写)");
						} 
					  					
					 
					empContract = new EmpContractDO();
					//empContract.setName(noNullName);

					//empContract = empContractService.find(empContract);
					if (null == empContract) {
						empContract = new EmpContractDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：用户id
						 */
						 
						 							 empContract.setUserId(userId)  ;
						 						
						 
					  						
					  						/**
						 * 设置：员工编号
						 */
						 
						 							 empContract.setNo(no)  ;
						 						
						 
					  						
					  						/**
						 * 设置：名字
						 */
						 
						 							 empContract.setName(name)  ;
						 						
						 
					  						
					  						/**
						 * 设置：身份证号码
						 */
						 
						 							 empContract.setIdCard(idCard)  ;
						 						
						 
					  						
					  						/**
						 * 设置：电话
						 */
						 
						 							 empContract.setPhone(phone)  ;
						 						
						 
					  						
					  						/**
						 * 设置：签订日期
						 */
						 
						 						 	 empContract.setSignDate(DateUtils.stringToDate(signDate))  ;
						 						
						 
					  						
					  						/**
						 * 设置：开始时间
						 */
						 
						 						 	 empContract.setBeginDate(DateUtils.stringToDate(beginDate))  ;
						 						
						 
					  						
					  						/**
						 * 设置：结束时间
						 */
						 
						 						 	 empContract.setEndTime(DateUtils.stringToDate(endTime))  ;
						 						
						 
					  					
					empContract.setCreateTime(new Date());
					empContract.setIsdelete(0);

					list.add(empContract);
				}
				UserDO loginInfo = super.getLoginUser();
				empContractService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	/**
	 * 修改保存接口
	 * @param empContract  EmpContractDO
	 * @return
	 */
	 @ApiOperation(value="修改保存接口", notes="修改保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "empContract", value = "保存实体信息", required = true, dataType = "EmpContractDO")
    })
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:empContract:edit")
	public R update( EmpContractDO empContract){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			empContract.setUpdator(loginInfo.getId());
			empContract.setUpdatorby(loginInfo.getUsername());
			empContract.setUpdatorName(loginInfo.getName());
		}
		empContract.setIsdelete(0);
		empContract.setLastTime(new Date());
		empContractService.update(empContract);
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
	@RequiresPermissions("oa:empContract:remove")
	public R remove( String id){
		if(empContractService.remove(id)>0){
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
	@RequiresPermissions("oa:empContract:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		empContractService.batchRemove(ids);
		return R.ok();
	}
	
}
