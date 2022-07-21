package com.yaolizh.oa.attend.controller;

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
import com.yaolizh.oa.attend.domain.AttendDO;
import com.yaolizh.oa.attend.service.AttendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 考勤打卡信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:53
 */
@Api(value="考勤打卡信息") 
@Controller
@RequestMapping("/oa/attend")
public class AttendController extends BaseController {
	@Autowired
	private AttendService attendService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:attend:attend")
	String Attend(){
	    return "oa/attend/attend";
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
	@RequiresPermissions("oa:attend:attend")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<AttendDO> attendList = (Page<AttendDO>) attendService.list(query);
		
		PageUtils pageUtils = new PageUtils(attendList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:attend:add")
	String add(){
	    return "oa/attend/add";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:attend:edit")
	String edit(@PathVariable("id") String id,Model model){
		AttendDO attend = attendService.get(id);
		model.addAttribute("attend", attend);
	    return "oa/attend/edit";
	}
	
	/**
	 * 新增保存接口
	 * @param attend  AttendDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attend", value = "保存实体信息", required = true, dataType = "AttendDO")
    })
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:attend:add")
	public R save( AttendDO attend){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			attend.setCreator(loginInfo.getId());
			attend.setCreatorby(loginInfo.getUsername());
			attend.setCreatorName(loginInfo.getName());
			attend.setCreateDeptid(loginInfo.getDeptId());
			attend.setCreateDeptcode(loginInfo.getDeptId());
			attend.setCreateDeptname(loginInfo.getDeptName());
			attend.setCreateOrgid(null);
			attend.setCreateOrgcode(null);
			attend.setCreateOrgname(null);
		}
		attend.setIsdelete(0);
		attend.setCreateTime(new Date());
		if(attendService.save(attend)>0){
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
	@RequiresPermissions("oa:attend:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<AttendDO> list = new ArrayList<AttendDO>();
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
				AttendDO attend;
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
					  						
					  						 /** 用户名字 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String userName = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(userName)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,用户名字未填写)");
						} 
					  						
					  						 /** 日期 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String workDay = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(workDay)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,日期未填写)");
						} 
					  						
					  						 /** 状态(1:缺勤,2:正常，4:迟到，8:早退；16:加班,32:上午请假,64:下午请假,128:补卡) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String state = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(state)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,状态(1:缺勤,2:正常，4:迟到，8:早退；16:加班,32:上午请假,64:下午请假,128:补卡)未填写)");
						} 
					  						
					  						 /** 工作时长 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String workHours = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(workHours)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,工作时长未填写)");
						} 
					  						
					  						 /** 上班时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String goWorkTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(goWorkTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,上班时间未填写)");
						} 
					  						
					  						 /** 上班经度 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String goWorkLng = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(goWorkLng)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,上班经度未填写)");
						} 
					  						
					  						 /** 上班纬度 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String goWorkLat = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(goWorkLat)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,上班纬度未填写)");
						} 
					  						
					  						 /** 上班备注 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String goWorkDescript = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(goWorkDescript)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,上班备注未填写)");
						} 
					  						
					  						 /** 下班时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String offWorkTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(offWorkTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,下班时间未填写)");
						} 
					  						
					  						 /** 下班经度 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String offWorkLng = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(offWorkLng)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,下班经度未填写)");
						} 
					  						
					  						 /** 下班纬度 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String offWorkLat = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(offWorkLat)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,下班纬度未填写)");
						} 
					  						
					  						 /** 下班备注 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String offWorkDescript = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(offWorkDescript)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,下班备注未填写)");
						} 
					  					
					 
					attend = new AttendDO();
					//attend.setName(noNullName);

					//attend = attendService.find(attend);
					if (null == attend) {
						attend = new AttendDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：用户id
						 */
						 
						 							 attend.setUserId(userId)  ;
						 						
						 
					  						
					  						/**
						 * 设置：用户名字
						 */
						 
						 							 attend.setUserName(userName)  ;
						 						
						 
					  						
					  						/**
						 * 设置：日期
						 */
						 
						 						 	 attend.setWorkDay(DateUtils.stringToDate(workDay))  ;
						 						
						 
					  						
					  						/**
						 * 设置：状态(1:缺勤,2:正常，4:迟到，8:早退；16:加班,32:上午请假,64:下午请假,128:补卡)
						 */
						 
						 						 	 attend.setState(Integer.parseInt(state))  ;
						 						
						 
					  						
					  						/**
						 * 设置：工作时长
						 */
						 
						 							 attend.setWorkHours(new BigDecimal(workHours))  ;
						 						
						 
					  						
					  						/**
						 * 设置：上班时间
						 */
						 
						 						 	 attend.setGoWorkTime(DateUtils.stringToDate(goWorkTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：上班经度
						 */
						 
						 							 attend.setGoWorkLng(new BigDecimal(goWorkLng))  ;
						 						
						 
					  						
					  						/**
						 * 设置：上班纬度
						 */
						 
						 							 attend.setGoWorkLat(new BigDecimal(goWorkLat))  ;
						 						
						 
					  						
					  						/**
						 * 设置：上班备注
						 */
						 
						 							 attend.setGoWorkDescript(goWorkDescript)  ;
						 						
						 
					  						
					  						/**
						 * 设置：下班时间
						 */
						 
						 						 	 attend.setOffWorkTime(DateUtils.stringToDate(offWorkTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：下班经度
						 */
						 
						 							 attend.setOffWorkLng(new BigDecimal(offWorkLng))  ;
						 						
						 
					  						
					  						/**
						 * 设置：下班纬度
						 */
						 
						 							 attend.setOffWorkLat(new BigDecimal(offWorkLat))  ;
						 						
						 
					  						
					  						/**
						 * 设置：下班备注
						 */
						 
						 							 attend.setOffWorkDescript(offWorkDescript)  ;
						 						
						 
					  					
					attend.setCreateTime(new Date());
					attend.setIsdelete(0);

					list.add(attend);
				}
				UserDO loginInfo = super.getLoginUser();
				attendService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	/**
	 * 修改保存接口
	 * @param attend  AttendDO
	 * @return
	 */
	 @ApiOperation(value="修改保存接口", notes="修改保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attend", value = "保存实体信息", required = true, dataType = "AttendDO")
    })
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:attend:edit")
	public R update( AttendDO attend){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			attend.setUpdator(loginInfo.getId());
			attend.setUpdatorby(loginInfo.getUsername());
			attend.setUpdatorName(loginInfo.getName());
		}
		attend.setIsdelete(0);
		attend.setLastTime(new Date());
		attendService.update(attend);
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
	@RequiresPermissions("oa:attend:remove")
	public R remove( String id){
		if(attendService.remove(id)>0){
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
	@RequiresPermissions("oa:attend:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		attendService.batchRemove(ids);
		return R.ok();
	}
	
}
