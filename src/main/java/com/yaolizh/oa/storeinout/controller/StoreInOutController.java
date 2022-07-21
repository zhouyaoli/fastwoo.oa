package com.yaolizh.oa.storeinout.controller;

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
import com.yaolizh.oa.storeinout.domain.StoreInOutDO;
import com.yaolizh.oa.storeinout.service.StoreInOutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 仓库出入库记录
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-21 21:15:54
 */
@Api(value="仓库出入库记录") 
@Controller
@RequestMapping("/oa/storeInOut")
public class StoreInOutController extends BaseController {
	@Autowired
	private StoreInOutService storeInOutService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:storeInOut:storeInOut")
	String StoreInOut(){
	    return "oa/storeInOut/storeInOut";
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
	@RequiresPermissions("oa:storeInOut:storeInOut")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<StoreInOutDO> storeInOutList = (Page<StoreInOutDO>) storeInOutService.list(query);
		
		PageUtils pageUtils = new PageUtils(storeInOutList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:storeInOut:add")
	String add(){
	    return "oa/storeInOut/add";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:storeInOut:edit")
	String edit(@PathVariable("id") String id,Model model){
		StoreInOutDO storeInOut = storeInOutService.get(id);
		model.addAttribute("storeInOut", storeInOut);
	    return "oa/storeInOut/edit";
	}
	
	/**
	 * 新增保存接口
	 * @param storeInOut  StoreInOutDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeInOut", value = "保存实体信息", required = true, dataType = "StoreInOutDO")
    })
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:storeInOut:add")
	public R save( StoreInOutDO storeInOut){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			storeInOut.setCreator(loginInfo.getId());
			storeInOut.setCreatorby(loginInfo.getUsername());
			storeInOut.setCreatorName(loginInfo.getName());
			storeInOut.setCreateDeptid(loginInfo.getDeptId());
			storeInOut.setCreateDeptcode(loginInfo.getDeptId());
			storeInOut.setCreateDeptname(loginInfo.getDeptName());
			storeInOut.setCreateOrgid(null);
			storeInOut.setCreateOrgcode(null);
			storeInOut.setCreateOrgname(null);
		}
		storeInOut.setIsdelete(0);
		storeInOut.setCreateTime(new Date());
		if(storeInOutService.save(storeInOut)>0){
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
	@RequiresPermissions("oa:storeInOut:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<StoreInOutDO> list = new ArrayList<StoreInOutDO>();
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
				StoreInOutDO storeInOut;
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
					  						
					  						 /** 初期金额 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String beginNum = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(beginNum)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,初期金额未填写)");
						} 
					  						
					  						 /** 出入类型(1:入,2:出) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String inOut = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(inOut)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,出入类型(1:入,2:出)未填写)");
						} 
					  						
					  						 /** 出入时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String inoutTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(inoutTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,出入时间未填写)");
						} 
					  						
					  						 /** 出入数量 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String inoutNum = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(inoutNum)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,出入数量未填写)");
						} 
					  						
					  						 /** 结存数量 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String endNum = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(endNum)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,结存数量未填写)");
						} 
					  					
					 
					storeInOut = new StoreInOutDO();
					//storeInOut.setName(noNullName);

					//storeInOut = storeInOutService.find(storeInOut);
					if (null == storeInOut) {
						storeInOut = new StoreInOutDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：编码
						 */
						 
						 							 storeInOut.setCode(code)  ;
						 						
						 
					  						
					  						/**
						 * 设置：名称
						 */
						 
						 							 storeInOut.setName(name)  ;
						 						
						 
					  						
					  						/**
						 * 设置：规格型号
						 */
						 
						 							 storeInOut.setModel(model)  ;
						 						
						 
					  						
					  						/**
						 * 设置：初期金额
						 */
						 
						 							 storeInOut.setBeginNum(new BigDecimal(beginNum))  ;
						 						
						 
					  						
					  						/**
						 * 设置：出入类型(1:入,2:出)
						 */
						 
						 						 	 storeInOut.setInOut(Integer.parseInt(inOut))  ;
						 						
						 
					  						
					  						/**
						 * 设置：出入时间
						 */
						 
						 							 storeInOut.setInoutTime(new BigDecimal(inoutTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：出入数量
						 */
						 
						 							 storeInOut.setInoutNum(new BigDecimal(inoutNum))  ;
						 						
						 
					  						
					  						/**
						 * 设置：结存数量
						 */
						 
						 							 storeInOut.setEndNum(new BigDecimal(endNum))  ;
						 						
						 
					  					
					storeInOut.setCreateTime(new Date());
					storeInOut.setIsdelete(0);

					list.add(storeInOut);
				}
				UserDO loginInfo = super.getLoginUser();
				storeInOutService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	/**
	 * 修改保存接口
	 * @param storeInOut  StoreInOutDO
	 * @return
	 */
	 @ApiOperation(value="修改保存接口", notes="修改保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeInOut", value = "保存实体信息", required = true, dataType = "StoreInOutDO")
    })
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:storeInOut:edit")
	public R update( StoreInOutDO storeInOut){
	UserDO loginInfo = super.getLoginUser();
		if(null!=loginInfo){
			storeInOut.setUpdator(loginInfo.getId());
			storeInOut.setUpdatorby(loginInfo.getUsername());
			storeInOut.setUpdatorName(loginInfo.getName());
		}
		storeInOut.setIsdelete(0);
		storeInOut.setLastTime(new Date());
		storeInOutService.update(storeInOut);
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
	@RequiresPermissions("oa:storeInOut:remove")
	public R remove( String id){
		if(storeInOutService.remove(id)>0){
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
	@RequiresPermissions("oa:storeInOut:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		storeInOutService.batchRemove(ids);
		return R.ok();
	}
	
}
