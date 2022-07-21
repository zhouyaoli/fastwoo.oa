package com.yaolizh.oa.storeinfo.controller;

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
import com.yaolizh.oa.storeinfo.domain.StoreInfoDO;
import com.yaolizh.oa.storeinfo.service.StoreInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 仓库情况
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:55
 */
@Api(value="仓库情况") 
@Controller
@RequestMapping("/oa/storeInfo")
public class StoreInfoController extends BaseController {
	@Autowired
	private StoreInfoService storeInfoService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:storeInfo:storeInfo")
	String StoreInfo(){
	    return "oa/storeInfo/storeInfo";
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
	@RequiresPermissions("oa:storeInfo:storeInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<StoreInfoDO> storeInfoList = (Page<StoreInfoDO>) storeInfoService.list(query);
		
		PageUtils pageUtils = new PageUtils(storeInfoList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:storeInfo:add")
	String add(){
	    return "oa/storeInfo/add";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:storeInfo:edit")
	String edit(@PathVariable("id") String id,Model model){
		StoreInfoDO storeInfo = storeInfoService.get(id);
		model.addAttribute("storeInfo", storeInfo);
	    return "oa/storeInfo/edit";
	}
	
	/**
	 * 新增保存接口
	 * @param storeInfo  StoreInfoDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeInfo", value = "保存实体信息", required = true, dataType = "StoreInfoDO")
    })
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:storeInfo:add")
	public R save( StoreInfoDO storeInfo){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			storeInfo.setCreator(loginInfo.getId());
			storeInfo.setCreatorby(loginInfo.getUsername());
			storeInfo.setCreatorName(loginInfo.getName());
			storeInfo.setCreateDeptid(loginInfo.getDeptId());
			storeInfo.setCreateDeptcode(loginInfo.getDeptId());
			storeInfo.setCreateDeptname(loginInfo.getDeptName());
			storeInfo.setCreateOrgid(null);
			storeInfo.setCreateOrgcode(null);
			storeInfo.setCreateOrgname(null);
		}
		storeInfo.setIsdelete(0);
		storeInfo.setCreateTime(new Date());
		if(storeInfoService.save(storeInfo)>0){
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
	@RequiresPermissions("oa:storeInfo:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<StoreInfoDO> list = new ArrayList<StoreInfoDO>();
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
				StoreInfoDO storeInfo;
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
					  						
					  						 /** 结存数量 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String num = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(num)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,结存数量未填写)");
						} 
					  					
					 
					storeInfo = new StoreInfoDO();
					//storeInfo.setName(noNullName);

					//storeInfo = storeInfoService.find(storeInfo);
					if (null == storeInfo) {
						storeInfo = new StoreInfoDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：编码
						 */
						 
						 							 storeInfo.setCode(code)  ;
						 						
						 
					  						
					  						/**
						 * 设置：名称
						 */
						 
						 							 storeInfo.setName(name)  ;
						 						
						 
					  						
					  						/**
						 * 设置：规格型号
						 */
						 
						 							 storeInfo.setModel(model)  ;
						 						
						 
					  						
					  						/**
						 * 设置：结存数量
						 */
						 
						 							 storeInfo.setNum(new BigDecimal(num))  ;
						 						
						 
					  					
					storeInfo.setCreateTime(new Date());
					storeInfo.setIsdelete(0);

					list.add(storeInfo);
				}
				UserDO loginInfo = super.getLoginUser();
				storeInfoService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	/**
	 * 修改保存接口
	 * @param storeInfo  StoreInfoDO
	 * @return
	 */
	 @ApiOperation(value="修改保存接口", notes="修改保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeInfo", value = "保存实体信息", required = true, dataType = "StoreInfoDO")
    })
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:storeInfo:edit")
	public R update( StoreInfoDO storeInfo){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			storeInfo.setUpdator(loginInfo.getId());
			storeInfo.setUpdatorby(loginInfo.getUsername());
			storeInfo.setUpdatorName(loginInfo.getName());
		}
		storeInfo.setIsdelete(0);
		storeInfo.setLastTime(new Date());
		storeInfoService.update(storeInfo);
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
	@RequiresPermissions("oa:storeInfo:remove")
	public R remove( String id){
		if(storeInfoService.remove(id)>0){
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
	@RequiresPermissions("oa:storeInfo:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		storeInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
