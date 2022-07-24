package com.yaolizh.oa.learnresource.controller;

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
import com.yaolizh.oa.learnresource.domain.LearnResourceDO;
import com.yaolizh.oa.learnresource.service.LearnResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
/**
 * 学习平台资源信息
 * 
 * @author zyl
 * @email 2602614680@qq.com
 * @date 2022-07-24 18:29:02
 */
@Api(value="学习平台资源信息") 
@Controller
@RequestMapping("/oa/learnResource")
public class LearnResourceController extends BaseController {
	@Autowired
	private LearnResourceService learnResourceService;
	/**
	 * 进入列表页面
	 * @return 
	 */
	@ApiOperation(value="进入列表页面", notes="进入列表页面")
	@GetMapping()
	@RequiresPermissions("oa:learnResource:learnResource")
	String LearnResource(){
	    return "oa/learnResource/learnResource";
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
	@RequiresPermissions("oa:learnResource:learnResource")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		Page<LearnResourceDO> learnResourceList = (Page<LearnResourceDO>) learnResourceService.list(query);
		
		PageUtils pageUtils = new PageUtils(learnResourceList);
		return pageUtils;
	}
	/**
	 * 去新增数据页面
	 * @return
	 */
	  @ApiOperation(value="去新增数据页面", notes="去新增数据页面")
	@GetMapping("/add")
	@RequiresPermissions("oa:learnResource:add")
	String add(Model model){
		LearnResourceDO learnResource = new LearnResourceDO();
		model.addAttribute("learnResource", learnResource);
	    return "oa/learnResource/addOrUpdate";
	}
	/**
	 * 去修改数据页面
	 * @param id String 修改数据主键
	 * @param model Model 用于前段交互数据
	 * @return
	 */
	  @ApiOperation(value="去修改数据页面", notes="去修改数据页面")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:learnResource:edit")
	String edit(@PathVariable("id") String id,Model model){
		LearnResourceDO learnResource = learnResourceService.get(id);
		model.addAttribute("learnResource", learnResource);
	    return "oa/learnResource/addOrUpdate";
	}
	
	/**
	 * 新增保存接口
	 * @param learnResource  LearnResourceDO
	 * @return
	 */
	  @ApiOperation(value="新增保存接口", notes="新增保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "learnResource", value = "保存实体信息", required = true, dataType = "LearnResourceDO")
    })
	@ResponseBody
	@PostMapping("/saveOrUpdate")
	@RequiresPermissions( value={"oa:learnResource:add","oa:learnResource:edit"}, logical=Logical.OR)
	public R saveOrUpdate( LearnResourceDO learnResource){
	UserDO loginInfo = super.getLoginUser();
		learnResourceService.saveOrUpdate(learnResource);
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
	@RequiresPermissions("oa:learnResource:remove")
	public R remove( String id){
		if(learnResourceService.remove(id)>0){
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
	@RequiresPermissions("oa:learnResource:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		learnResourceService.batchRemove(ids);
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
	@RequiresPermissions("oa:learnResource:importExcel")
	public R saveImportExcel(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		try {
			String fileName = file.getOriginalFilename();
			List<LearnResourceDO> list = new ArrayList<LearnResourceDO>();
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
				LearnResourceDO learnResource;
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

						
					  					  						
					  						 /** 标题 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String title = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(title)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,标题未填写)");
						} 
					  						
					  						 /** 状态(1:新增,2:发布,4:撤销) */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String state = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(state)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,状态(1:新增,2:发布,4:撤销)未填写)");
						} 
					  						
					  						 /** 使用人 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String usePerson = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(usePerson)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,使用人未填写)");
						} 
					  						
					  						 /** 开始使用时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String beginTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(beginTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,开始使用时间未填写)");
						} 
					  						
					  						 /** 结束使用时间 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String endTime = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(endTime)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,结束使用时间未填写)");
						} 
					  						
					  						 /** 描述 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String descript = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(descript)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,描述未填写)");
						} 
					  						
					  						 /** 外部连接 */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String uri = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(uri)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,外部连接未填写)");
						} 
					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  					  						
					  						 /**  */
						row.getCell(cellNum++).setCellType(CellType.STRING);
						String remark = row.getCell(cellNum-1).getStringCellValue();
						if (StringUtils.isEmpty(remark)) {
							throw new RuntimeException("导入失败(第" + (r + 1) + "行,未填写)");
						} 
					  						
					  					  						
					  					  					
					 
					learnResource = new LearnResourceDO();
					//learnResource.setName(noNullName);

					learnResource = learnResourceService.findOne(learnResource);
					if (null == learnResource) {
						learnResource = new LearnResourceDO();
					}
						
					  							 
					  						
					  						/**
						 * 设置：标题
						 */
						 
						 							 learnResource.setTitle(title)  ;
						 						
						 
					  						
					  						/**
						 * 设置：状态(1:新增,2:发布,4:撤销)
						 */
						 
						 							 learnResource.setState(state)  ;
						 						
						 
					  						
					  						/**
						 * 设置：使用人
						 */
						 
						 							 learnResource.setUsePerson(usePerson)  ;
						 						
						 
					  						
					  						/**
						 * 设置：开始使用时间
						 */
						 
						 						 	 learnResource.setBeginTime(DateUtils.stringToDate(beginTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：结束使用时间
						 */
						 
						 						 	 learnResource.setEndTime(DateUtils.stringToDate(endTime))  ;
						 						
						 
					  						
					  						/**
						 * 设置：描述
						 */
						 
						 							 learnResource.setDescript(descript)  ;
						 						
						 
					  						
					  						/**
						 * 设置：外部连接
						 */
						 
						 							 learnResource.setUri(uri)  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  							 
					  						
					  						/**
						 * 设置：
						 */
						 
						 							 learnResource.setRemark(remark)  ;
						 						
						 
					  						
					  							 
					  						
					  							 
					  					
					learnResource.setCreateTime(new Date());
					learnResource.setIsdelete(0);

					list.add(learnResource);
				}
				UserDO loginInfo = super.getLoginUser();
				learnResourceService.saveImportExcel(list, loginInfo,request);
			}
		} catch (IOException e) {
			return R.error("导入失败：" + e.getMessage() );
		}
		  return R.ok("导入成功");
	}
	
}
